package com.tavisca.gce.trainings.SereializationAndDeserializationFromDataSource;

import java.util.Scanner;

import com.app.services.EmployeeService;

/**
 * Hello world!
 *
 */
public class AppMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\t============= Menu =============");
		System.out.println("1. XML");
		System.out.println("2. JSON");
		System.out.println("3. CSV");
		System.out.println("Choose the conversion file format : ");
		int ch = scanner.nextInt();
		System.out.println();

		EmployeeService employeeService = new EmployeeService();

		boolean status = employeeService.convertAndStoreInFile(ch);
		if (status)
			System.out.println("File Written Successfully.");
		else
			System.out.println("Some Error Occured.");

		scanner.close();
	}

}
