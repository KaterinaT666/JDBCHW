package org.skypro;

import org.skypro.dao.EmployeeDao;
import org.skypro.dao.impl.CityDao;
import org.skypro.dao.impl.CityDaoImpl;
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
		employeeDao.createEmployee(employee1);
		System.out.println(employee1);

		// Получаем объект по id
		System.out.println(employeeDao.readEmployeeById(11));

		// Получаем полный список объектов
		List<Employee> list = employeeDao.readAllEmployees();

		for (Employee employee : list) {
			System.out.println(employee);
		}

		Employee employee2 = new Employee( 10,"lubochka", "Obramova", "femail",32, new City(4,"Ереван"));

		// Изменяем объект
		employeeDao.updateEmployee(employee1);

		// Удаляем объект
		employeeDao.deleteEmployee(employee2);



		// Создаем объект класса ДАО
		CityDao cityDao = new CityDaoImpl();

		City city1 = new City(5, "Ижевск");
		// Создаем объект
		cityDao.createCity(city1);
		System.out.println(city1);

		// Получаем объект по id
		System.out.println(cityDao.readCityById(5));

		// Получаем полный список объектов
		List<City> list = cityDao.readAllCity();

		for (City city : list) {
		System.out.println(city);
		}

		City city2 = new City(1, "Таганрог");

		// Изменяем объект
		cityDao.updateCity(city2);

		// Удаляем объект
		cityDao.deleteCity(city1);


		Employee employee3 = new Employee("Oleg", "Olegov","mail", 31,
				new City(6,"Ижевск"));
		employeeDao.createEmployee(employee3);
		System.out.println(employee3);
	}
}
