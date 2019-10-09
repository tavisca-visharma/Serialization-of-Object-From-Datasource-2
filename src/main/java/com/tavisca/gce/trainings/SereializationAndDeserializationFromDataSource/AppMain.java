package com.tavisca.gce.trainings.SereializationAndDeserializationFromDataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.app.services.EmployeeService;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class AppMain {
	public static void main(String[] args)
			throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		Scanner scanner = new Scanner(System.in);
		int ch;
		while (true) {
			System.out.println("\t============= Menu =============");
			System.out.println("1. XML");
			System.out.println("2. JSON");
			System.out.println("3. CSV");
			System.out.println("4. Exit");
			System.out.println("Choose the conversion file format : ");
			ch = scanner.nextInt();
			System.out.println();
			if (ch == 4) {
				System.out.println("\tThanks. Visit Again...");
				break;
			}

			EmployeeService employeeService = new EmployeeService();

			boolean status = employeeService.convertAndStoreInFile(ch);
			if (status)
				System.out.println("File Written Successfully.");
			else
				System.out.println("Some Error Occured.");
		}
		scanner.close();
	}

}
