package org.skypro.dao;

import org.skypro.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDao {
	Optional<Employee> create(Employee employee);

	Optional<Employee> readById(int id);

	List<Employee> readAll();

	Optional<Employee> updateById(Employee employee);

	Optional<Employee> deleteById(int id);
}

