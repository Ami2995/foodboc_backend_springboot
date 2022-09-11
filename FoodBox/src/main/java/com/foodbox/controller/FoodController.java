package com.foodbox.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.foodbox.exception.ResourceNotFoundException;
import com.foodbox.model.FoodItems;
import com.foodbox.repository.FoodRepository;
import com.foodbox.service.FoodService;
import com.foodbox.util.FileUtils;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/food")
public class FoodController {
	
	@Autowired
	private FoodService foodService;

	@Autowired
	private FoodRepository foodRepo;

	@Secured("ROLE_ADMIN")
	@PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FoodItems addFoodItems(@RequestPart("foodData") FoodItems food,
			@RequestPart("imageFile") MultipartFile files) throws IOException {
		String path = FileUtils.folderPath;
		
        String fileName = files.getOriginalFilename();
		
		String randomId = UUID.randomUUID().toString();
		String newFileName = randomId.concat(fileName.substring(fileName.lastIndexOf(".")));
		
		String filePath = path + File.separator + newFileName;
		
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(files.getInputStream(), Paths.get(filePath));
		
		food.setFoodImage(filePath);
		return foodService.addFoodItems(food);
	}


	@GetMapping("/")
	public List<FoodItems> getAllFoodItems() {
		return foodService.getAllFoodItems();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FoodItems> getFoodItemById(@PathVariable Long id) {
		FoodItems food = foodRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Food item not available with existing id: " + id));
		return ResponseEntity.ok(food);
	}
    
	@Secured("ROLE_ADMIN")
	@PutMapping("/")
	public ResponseEntity<FoodItems> updateFoodItems(@RequestBody FoodItems food){
		return ResponseEntity.ok(foodService.updateFoodItems(food));
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteFoodItemById(@PathVariable Long id) {
		FoodItems food = foodRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Food item not available with existing id: " + id));
		foodRepo.delete(food);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/download/{fileName}")
	public void downloadFile(@PathVariable String fileName, HttpServletResponse response) throws IOException {
		InputStream image = foodService.downloadImage(fileName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(image, response.getOutputStream());
	}
}
