package com.app.entity;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({ "Emp Id", "First Name", "Last Name", "Hobbies", "Dept ID" })
@XmlType(propOrder = { "id", "firstName", "lastName", "hobbies", "deptId" })
public class Employee {

	@XmlElement(name = "empId")
	@JsonProperty("Emp Id")
	private int id;

	@XmlElement(name = "firstName")
	@JsonProperty("First Name")
	private String firstName;

	@XmlElement(name = "lastName")
	@JsonProperty("Last Name")
	private String lastName;

	@XmlElementWrapper(name = "hobbies")
	@XmlElement(name = "hobby")
	@JsonProperty("Hobbies")
	private String[] hobbies;

	@JsonProperty("Dept ID")
	private int deptId;

	public Employee() {

	}

	public Employee(int id) {
		this.id = id;
	}

	public Employee(int id, String firstName, String lastName, String[] hobbies, int deptId) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hobbies = hobbies;
		this.deptId = deptId;
	}

	public int getId() {
		return id;
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

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hobbies="
				+ Arrays.toString(hobbies) + ", deptId=" + deptId + "]";
	}

}
