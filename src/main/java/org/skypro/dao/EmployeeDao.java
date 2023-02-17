package org.skypro.dao;

import org.skypro.model.Employee;

import java.util.List;

public interface EmployeeDao {
	Employee create(Employee employee);

	Employee readById(int id);

	List<Employee> readAll();

	void updateAgeById(int id, int age);

	void deleteById(int id);
}

