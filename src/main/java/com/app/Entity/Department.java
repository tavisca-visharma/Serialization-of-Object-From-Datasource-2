package com.app.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "department")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonPropertyOrder({"Dept Id", "Dept Name"})
public class Department {

	@XmlElement(name = "deptId")
	@JsonProperty("Dept Id")
	private int id;

	@XmlElement(name = "deptName")
	@JsonProperty("Dept Name")
	private String name;

	public Department() {

	}

	public Department(int id) {
		super();
		this.id = id;
	}

	public Department(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}

}
