package org.skypro.dao;

import org.skypro.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
	void createEmployee(Employee employee)throws SQLException;

	Employee readEmployeeById(int id);

	List<Employee> readAllEmployees();

	void updateEmployee(Employee employee);

	void deleteEmployee(Employee employee);


}

