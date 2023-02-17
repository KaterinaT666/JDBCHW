package org.skypro;

import org.skypro.dao.EmployeeDao;
import org.skypro.dao.impl.EmployeeDaoImpl;
import org.skypro.model.City;
import org.skypro.model.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Application {
	public static void main(String[] args) throws SQLException{

		final String user = "postgres";
		final String password = "pobeda09051945";
		final String url = "jdbc:postgresql://localhost:5432/skypro";

		try (Connection connection = DriverManager.getConnection(url, user, password)) {

			EmployeeDao employeeDao = new EmployeeDaoImpl(connection);
			employeeDao.create(new Employee(7, "Елена", "Попова", "ж", 25,
					new City(1, "Казань")));

			List<Employee> list = new ArrayList<>(employeeDao.readAll());

			for (Employee employee : list) {
				System.out.println(employee);
			}
			System.out.println();

			System.out.println(employeeDao.readById(1));
			System.out.println();

		}
	}
}
