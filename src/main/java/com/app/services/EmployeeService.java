package com.app.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.app.db.dao.DepartmentDAO;
import com.app.db.dao.EmployeeDAO;
import com.app.entity.DepartmentEmployeeCSV;
import com.app.entity.Departments;
import com.app.entity.DepartmentsWithEmployees;
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

	private Departments departments = null;

	public EmployeeService() throws SQLException {
		departments = new Departments();
		DBMergeService dbMergeService = new DBMergeService();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		DepartmentDAO departmentDAO = new DepartmentDAO();
		List<DepartmentsWithEmployees> departmentsWithEmployees = dbMergeService.innerJoin(employeeDAO.findAll(),
				departmentDAO.findAll());
		departments.setDepartmentsWithEmployees(departmentsWithEmployees);

	}

	public boolean convertAndStoreInFile(int ch) throws JsonGenerationException, JsonMappingException, IOException {

		switch (ch) {
		case 1:
			System.out.println("Converting to XML ...");
			return convertToXML(this.departments);

		case 2:
			System.out.println("Converting to JSON ...");
			convertToJSON(this.departments);
			return true;

		case 3:
			System.out.println("Converting to CSV ...");
			convertToCSV(this.departments);
			return true;
		default:

			System.out.println("Wrong Choice! Aborting ...");
		}

		return false;
	}

	private void convertToCSV(Departments departments)
			throws JsonGenerationException, JsonMappingException, IOException {
		
		List<DepartmentEmployeeCSV> csvList = new ArrayList<DepartmentEmployeeCSV>();

		for(DepartmentsWithEmployees de : departments.getDepartmentsWithEmployees()) {
			int deptId = de.getDeptId();
			String deptName = de.getDeptName();
			for(Employee employee : de.getEmployees().getAllEmployees()) {
				
				DepartmentEmployeeCSV departmentEmployeeCSV = new DepartmentEmployeeCSV(
						deptId,
						deptName,
						employee.getId(), 
						employee.getFirstName(),
						employee.getLastName(),
						employee.getHobbies());
				csvList.add(departmentEmployeeCSV);
			}
		}

		
		CsvMapper csvMapper = new CsvMapper();
		CsvSchema schema = csvMapper.schemaFor(DepartmentEmployeeCSV.class);
		csvMapper.writer(schema).writeValue(new File(DIRECTORY_PATH + "\\" + CSV_FILE),
				csvList);

	}

	private void convertToJSON(Departments departments) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			Writer writer = new FileWriter(DIRECTORY_PATH + "\\" + JSON_FILE);
			gson.toJson(departments, writer);
			writer.flush();
			writer.close();
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	private boolean convertToXML(Departments departments) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Departments.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(departments, new File(DIRECTORY_PATH + "\\" + XML_FILE));
		} catch (JAXBException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
