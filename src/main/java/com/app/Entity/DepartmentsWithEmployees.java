package com.app.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class DepartmentsWithEmployees {

	@XmlAttribute(name = "Dept Id")
	private int deptId;

	@XmlAttribute(name = "Dept Name")
	private String deptName;

	@JsonUnwrapped
	private Employees employees = null;

	public DepartmentsWithEmployees() {

	}

	public DepartmentsWithEmployees(Department department) {
		deptId = department.getId();
		deptName = department.getName();
	}

	public DepartmentsWithEmployees(Department department, Employees employees) {
		super();
		this.deptId = department.getId();
		this.deptName = department.getName();
		this.employees = employees;
	}

	public Employees getEmployees() {
		return employees;
	}

	@XmlTransient
	public int getDeptId() {
		return deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	@XmlTransient
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setEmployees(Employees employees) {
		this.employees = employees;
	}


}
