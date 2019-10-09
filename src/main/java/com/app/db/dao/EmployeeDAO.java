package com.app.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.db.DBConnection;
import com.app.entity.Department;
import com.app.entity.Employee;

public class EmployeeDAO {

	private List<Employee> employees = null;
	private Connection connection;
	
	public EmployeeDAO() throws SQLException {
		connection = DBConnection.getConnection();
	}

	public Employee findById(int id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where empid = ?");
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return new Employee(
				rs.getInt("empid"),
				rs.getString("firstname"),
				rs.getString("lastname"),
				extractHobbies(rs.getString("hobbies")),
				findDepartment(rs.getInt("deptid"))
				);
	}

	public List<Employee> findAll() {
		employees = new ArrayList<Employee>();
		try {
			Statement statement = connection.createStatement();
			String sqlQuery = "select * from employee";
			ResultSet rs = statement.executeQuery(sqlQuery);
			while (rs.next()) {
				Employee employee = new Employee(
						rs.getInt("empid"),
						rs.getString("firstname"),
						rs.getString("lastname"),
						extractHobbies(rs.getString("hobbies")),
						findDepartment(rs.getInt("deptid"))
						);

				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}
	
	private String[] extractHobbies(String hobbies) {
		return hobbies.split(",");
	}
	
	private Department findDepartment(int id) throws SQLException {
		return new DepartmentDAO().findById(id);
	}

}
