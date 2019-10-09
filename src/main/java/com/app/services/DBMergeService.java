package com.app.services;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.Department;
import com.app.entity.DepartmentsWithEmployees;
import com.app.entity.Employee;
import com.app.entity.Employees;

public class DBMergeService {

	public List<DepartmentsWithEmployees> innerJoin(List<Employee> employees, List<Department> departments) {
		List<DepartmentsWithEmployees> departmentsWithEmployees = new ArrayList<DepartmentsWithEmployees>();
		for (int i = 0; i < departments.size(); i++) {
			List<Employee> employeesInDepartment = new ArrayList<Employee>();
			for (int j = 0; j < employees.size(); j++) {
				if (departments.get(i).getId() == employees.get(j).getDeptId()) {
					employeesInDepartment.add(employees.get(j));
				}

			}
			Employees emps = new Employees();
			emps.setEmployees(employeesInDepartment);
			departmentsWithEmployees.add(new DepartmentsWithEmployees(departments.get(i), emps));
		}

		return departmentsWithEmployees;
	}

}
