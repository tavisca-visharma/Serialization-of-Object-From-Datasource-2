package com.app.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.app.Entity.Employee;
import com.app.Entity.Employees;
import com.app.dao.EmployeeDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class EmployeeService {

	private static final String DIRECTORY_PATH = "C:\\Users\\vlsharma\\eclipse-workspace\\SerializationAndDeserializationFromDataSource\\Resources";
	private static final String XML_FILE = "employees.xml";
	private static final String JSON_FILE = "employees.json";
	private static final String CSV_FILE = "employees.csv";

	private Employees employees = null;

	public EmployeeService() {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employees = new Employees();
		employees.setEmployees(employeeDAO.getAllEmployees());
	}

	public List<Employee> getEmployees() {
		return employees.getEmployees();
	}

	public void setEmployees(List<Employee> employees) {
		this.employees.setEmployees(employees);
	}

	public boolean convertAndStoreInFile(int ch) {

		switch (ch) {
		case 1:
			System.out.println("Converting to XML ...");
			return convertToXML(this.employees);

		case 2:
			System.out.println("Converting to JSON ...");
			convertToJSON(this.employees);
			return true;

		case 3:
			System.out.println("Converting to CSV ...");
			convertToCSV(this.employees);
			return true;
		default:

			System.out.println("Wrong Choice! Aborting ...");
		}

		return false;
	}

	private void convertToCSV(Employees employees) {
		StringJoiner headers = new StringJoiner(",");
		headers.add("EMP ID").add("FIRSTNAME").add("LASTNAME").add("HOBBIES").add("DEPT ID").add("DEPTNAME");
		String csvString = headers.toString() + "\n";
		for(Employee employee : employees.getEmployees()) {
			String hobbies = Stream.of(employee.getHobbies()).collect(Collectors.joining(" &"));
			StringJoiner csvLine = new StringJoiner(",");
			csvLine
					.add(employee.getId() + "")
					.add(employee.getFirstName())
					.add(employee.getLastName())
					.add(hobbies)
					.add(employee.getDepartment().getId() + "")
					.add(employee.getDepartment().getName());
			csvString += csvLine.toString() + "\n";
		}
		System.out.println(csvString);
		try {
			FileWriter fileWriter = new FileWriter(DIRECTORY_PATH + "\\" + CSV_FILE);
			fileWriter.write(csvString);
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void convertToJSON(Employees employees) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			Writer writer = new FileWriter(DIRECTORY_PATH + "\\" + JSON_FILE);
			gson.toJson(employees, writer);
			writer.flush();
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	private boolean convertToXML(Employees employees) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(employees, new File(DIRECTORY_PATH + "\\" + XML_FILE));
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
