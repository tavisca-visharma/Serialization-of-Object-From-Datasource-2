package com.app.entity;

import java.util.Arrays;

public class DepartmentEmployeeCSV {
	private int deptId;

	private String deptName;
	
	private int empId;

	private String firstName;

	private String lastName;

	private String[] hobbies;

	public DepartmentEmployeeCSV() {

	}
	
	public DepartmentEmployeeCSV(int deptId, String deptName, int empId, String firstName, String lastName,
			String[] hobbies) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hobbies = hobbies;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String[] getHobbies() {
		return hobbies;
	}

	public void setHobbies(String[] hobbies) {
		this.hobbies = hobbies;
	}

	@Override
	public String toString() {
		return "DepartmentEmployeeCSV [deptId=" + deptId + ", deptName=" + deptName + ", empId=" + empId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", hobbies=" + Arrays.toString(hobbies) + "]";
	}
	
	
	
	
}