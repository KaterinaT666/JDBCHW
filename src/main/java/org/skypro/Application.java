package org.skypro;
import org.skypro.dao.EmployeeDao;
import org.skypro.dao.impl.EmployeeDaoImpl;
import org.skypro.model.City;
import org.skypro.model.Employee;

import java.sql.SQLException;
import java.util.Optional;


public class Application {
	public static void main(String[] args) throws SQLException{
		EmployeeDao employeeDao = new EmployeeDaoImpl();
		City city1 = new City(1,"Москва");
		City city2 = new City(2, "Санкт-Петербург");
		City city3 = new City(3, "Казань");

		employeeDao.create(new Employee
				("Иван","Иванов","муж", 45, city1))
				.ifPresent(employee-> System.out.println("Добавленный сотрудник: " + employee));
		Optional<Employee> employeeOptional = employeeDao.create(new Employee
						("Мария","Иванова","ж", 25, city2));
		employeeOptional.ifPresent(employee-> System.out.println("Добавленный сотрудник: " + employee));
		employeeDao.create(new Employee
						("Семен","Семенов","муж", 34, city3))
				.ifPresent(employee-> System.out.println("Добавленный сотрудник: " + employee));
		employeeDao.create(new Employee
						("Леонид","Васильев","муж", 32))
				.ifPresent(employee-> System.out.println("Добавленный сотрудник: " + employee));

		System.out.println("Все сотрудники: ");
		employeeDao.readAll().forEach(System.out::println);

		if (employeeOptional.isPresent()){
			employeeDao.readById(employeeOptional.get().getId())
					.ifPresent(employee -> System.out.println("Найденный сотрудник: " + employee));
		}

		if (employeeOptional.isPresent()){
			Employee employee =employeeOptional.get();
			employee.setAge(40);
			employee.setFirstName("Анатолий");
			employeeDao.updateById(employee)
					.ifPresent(emp -> System.out.println("Обновленный сотрудник: " + emp));
		}
		if (employeeOptional.isPresent()){
			employeeDao.deleteById(employeeOptional.get().getId())
					.ifPresent(emp -> System.out.println("Удаленный сотрудник: " + emp));
		}

	}
}
