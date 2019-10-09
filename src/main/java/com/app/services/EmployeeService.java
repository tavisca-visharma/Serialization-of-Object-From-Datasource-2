package com.app.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.app.db.dao.EmployeeDAO;
import com.app.entity.Employee;
import com.app.entity.Employees;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

public class EmployeeService {

	private static final String DIRECTORY_PATH = "C:\\Users\\vlsharma\\eclipse-workspace\\SerializationAndDeserializationFromDataSource\\Resources";
	private static final String XML_FILE = "employees.xml";
	private static final String JSON_FILE = "employees.json";
	private static final String CSV_FILE = "employees.csv";

	private Employees employees = null;

	public EmployeeService() throws SQLException {
		EmployeeDAO employeeDAO = new EmployeeDAO();
		employees = new Employees();
		employees.setEmployees(employeeDAO.findAll());
	}

	public List<Employee> getEmployees() {
		return employees.getEmployees();
	}

	public void setEmployees(List<Employee> employees) {
		this.employees.setEmployees(employees);
	}

	public boolean convertAndStoreInFile(int ch) throws JsonGenerationException, JsonMappingException, IOException {

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

	private void convertToCSV(Employees employees) throws JsonGenerationException, JsonMappingException, IOException {

		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = csvMapper.schemaFor(Employee.class).withHeader();
		csvMapper.writer(schema)
		.writeValue(new File(DIRECTORY_PATH + "\\" + CSV_FILE),
				employees.getEmployees());

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
