package org.skypro.dao;

import org.skypro.model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
	void create(Employee employee)throws SQLException;

	Employee readById(int id);

	List<Employee> readAll();

	void updateById(Employee employee);

	void deleteById(Employee employee);


}

