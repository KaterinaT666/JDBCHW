package org.skypro;

import org.skypro.dao.EmployeeDao;
import org.skypro.dao.impl.EmployeeDaoImpl;

import org.skypro.model.City;
import org.skypro.model.Employee;

import java.sql.*;
import java.util.List;


public class Application {
	public static void main(String[] args) throws SQLException {
		// Создаем объект класса ДАО
		EmployeeDao employeeDao = new EmployeeDaoImpl();

		Employee employee1 = new Employee("Timofej", "Obramov", "mail", 31);
		// Создаем объект
		employeeDao.create(employee1);
		System.out.println(employee1);

		// Получаем объект по id
		System.out.println(employeeDao.readById(11));

		// Получаем полный список объектов
		List<Employee> list = employeeDao.readAll();

		for (Employee employee : list) {
			System.out.println(employee);
		}

		Employee employee2 = new Employee( 10,"lubochka", "Obramova", "femail",32, new City(4,"Ереван"));

		// Изменяем объект
		employeeDao.updateById(employee1);

		// Удаляем объект
		employeeDao.deleteById(employee2);

	}
}
