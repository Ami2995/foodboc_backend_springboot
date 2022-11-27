package com.lockers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

class Locker {

	static File folder = new File("private");
	
	static Scanner scan = new Scanner(System.in);
	
	public static void retreiveFiles() {
		int count = 0;
		File[] files = folder.listFiles();
	 
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			System.out.println("Something went wrong.");
		}
		for(File file:files) {
			count++;
			System.out.println(count+"  "+file.getName()+"   "+file.getAbsolutePath());
		}
		System.out.println();
	}
	
	public static void createNewFile() {
	  System.out.println("Enter file name.");
	  String fileName = scan.nextLine()+".txt";
	  
	  File file = new File(folder, fileName);
	  PrintWriter writer = null;
	  try {
		  writer = new PrintWriter(file);
		  System.out.println("Enter data.");
		  writer.println(scan.nextLine());
		  try {
			  Thread.sleep(1000);
			  System.out.println("File created successfully.\n");
		  }catch(InterruptedException e) {
			  System.out.println("Something went wrong.\n");
		  }
	  }catch(IOException e) {
		  System.out.println("File Not Found.\n");
	  }finally {
		  writer.close();
	  }
	}
	
	public static void addExistingFile() {
		System.out.println("Enter source file path.");
		String sourcePath = scan.nextLine();
		
		File sourceFile = new File(sourcePath);
		File destinationFile = new File(folder,sourceFile.getName());
		if(folder.exists()) {
			try {
				Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				try {
					Thread.sleep(1000);
					System.out.println("File copied successfully.\n");
				}catch (InterruptedException e) {
				  System.out.println("Something went wrong.\n");
				}
			} catch (IOException e) {
				System.out.println("Please enter valid file path.\n");
			}
		}else {
			System.out.println("Please enter valid path.\n");
		}
	}
	
	public static void searchFile() {
		System.out.println("Enter file name.");
		String fileName = scan.nextLine();
		
		File file = new File(folder, fileName);
		if(file.exists()) {
			System.out.println(file.getName()+"   "+file.getAbsolutePath()+"\n");
		}else {
			System.out.println("File not exists.\n");
		}
	}
	
	public static void deleteFile() {
		System.out.println("Enter file name to delete.");
		String fileName = scan.nextLine();
		
		File file = new File(folder, fileName);
		if(file.exists()) {
			System.out.println("File deleted successfully.\n");
		}else {
			System.out.println("File not available.\n");
		}
	}
}
