package com.app.entity;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

@XmlRootElement(name = "departments")
@XmlAccessorType(XmlAccessType.FIELD)
public class Departments {

	@XmlAttribute(name = "date")
	Date date = Date.from(Instant.now());

	@XmlElement(name = "department")
	@JsonUnwrapped
	private List<DepartmentsWithEmployees> departments;

	public List<DepartmentsWithEmployees> getDepartmentsWithEmployees() {
		return departments;
	}

	public void setDepartmentsWithEmployees(List<DepartmentsWithEmployees> departments) {
		this.departments = departments;
	}

	@Override
	public String toString() {
		return "Departments [departments=" + departments + "]";
	}

}
