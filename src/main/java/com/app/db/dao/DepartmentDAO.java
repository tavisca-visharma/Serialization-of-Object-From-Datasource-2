package com.app.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.app.db.DBConnection;
import com.app.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {

	private List<Department> departments = null;
	private Connection connection;

	public DepartmentDAO() throws SQLException {
		connection = DBConnection.getConnection();
	}

	public Department findById(int id) throws SQLException {
		String sqlQuery = "select * from department where deptid = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, id);
		ResultSet rs = preparedStatement.executeQuery();
		rs.next();
		return new Department(id, rs.getString("deptname"));
	}

	public List<Department> findAll() throws SQLException {
		departments = new ArrayList<Department>();
		String sqlQuery = "select * from department";
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sqlQuery);
		while (rs.next()) {
			Department department = new Department(
						rs.getInt("deptid"),
						rs.getString("deptname")
					);
			departments.add(department);
		}
		return departments;
	}

}
