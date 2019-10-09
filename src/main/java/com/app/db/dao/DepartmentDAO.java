package com.app.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.db.DBConnection;
import com.app.entity.Department;

public class DepartmentDAO {

	Connection connection;

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

}
