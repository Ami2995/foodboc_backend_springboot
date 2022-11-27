package com.lockers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TestLocker {
	
	
	public static void main(String[] args) {
		
		Locker.folder.mkdir();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("                 Welcome                        ");
		System.out.println("************LockedMe Application****************");
		System.out.println();
		
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			System.out.println("Something went wrong.");
		}
		
		int choice = 0;
		
		do {
			System.out.println("1. Retreive Files");
			System.out.println("2. Create File");
			System.out.println("3. Copy File");
	        System.out.println("4. Search File");
			System.out.println("5. Delete File");
			System.out.println("6. Exit\n");
			
			System.out.println("Select an option.");
			
			try {
				 choice = scan.nextInt();
				 System.out.println();
			}catch(InputMismatchException e) {
				System.out.println("Please enter valid option.");
			}
			
			switch(choice) {
			case 1:
				Locker.retreiveFiles();
				break;
				
			case 2:
				Locker.createNewFile();
				break;
				
			case 3:
				Locker.addExistingFile();
				break;
				
			case 4:
				Locker.searchFile();
				break;
				
			case 5:
				Locker.deleteFile();
				break;
				
			case 6:
				System.out.println("Thanks for using....");
				System.exit(0);
				break;
				
			default:
				System.out.println("Please select valid option");
				break;
			}
		} while(choice!=6);
		scan.close();
	}

}
