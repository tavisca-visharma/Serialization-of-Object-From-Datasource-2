package com.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.Entity.Department;
import com.app.Entity.Employee;

public class EmployeeDAO {

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1/vishaldb";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private List<Employee> employees = null;
	private Connection connection = null;

	public List<Employee> getAllEmployees() {
		employees = new ArrayList<Employee>();
		try {
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			Statement statement = connection.createStatement();
			String sqlQuery = "select * from employee e,department d where e.deptId = d.deptId";
			ResultSet rs = statement.executeQuery(sqlQuery);
			while (rs.next()) {
				Employee employee = new Employee(rs.getInt("empid"));
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				String hobbies[] = rs.getString("hobbies").split(",");
				employee.setHobbies(hobbies);
				Department department = new Department(rs.getInt("deptid"));
				department.setName(rs.getString("deptname"));
				employee.setDepartment(department);

				employees.add(employee);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

}
