package org.skypro.dao.impl;

import org.skypro.dao.EmployeeDao;

import org.skypro.model.City;
import org.skypro.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

	final String user = "postgres";
	final String password = "pobeda09051945";
	final String url = "jdbc:postgresql://localhost:5432/skypro";

	private Connection connection;

	public EmployeeDaoImpl(){
	}

	public EmployeeDaoImpl(Connection connection){
		this.connection = connection;
	}


	private static final String READ_ALL = "SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id";
	private static final String INSERT =
			"INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)";
	private static final String READ_BY_ID =
			"SELECT * FROM employee INNER JOIN city ON employee.city_id=city_id AND city_id=(?)";
	private static final String UPDATE = "UPDATE employee SET age=(?) WHERE id=(?)";
	private static final String DELETE = "DELETE FROM employee WHERE id=(?)";


	@Override
	public Employee create(Employee employee) {
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(INSERT)) {

			statement.setString(1, employee.getFirst_name());
			statement.setString(2, employee.getLast_name());
			statement.setString(3, employee.getGender());
			statement.setInt(4, employee.getAge());
			statement.setInt(5, employee.getCity().getCity_id());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public Employee readById(int id) {
		Employee employee = new Employee();
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(
				READ_BY_ID)) {
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {

				employee.setId(Integer.parseInt(resultSet.getString("id")));
				employee.setFirst_name(resultSet.getString("first_name"));
				employee.setLast_name(resultSet.getString("last_name"));
				employee.setGender(resultSet.getString("gender"));
				employee.setAge(Integer.parseInt(resultSet.getString("age")));
				employee.setCity(new City(resultSet.getInt("city_id"),
						resultSet.getString("city_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public List<Employee> readAll() {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement (READ_ALL)){
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()){
				int id = Integer.parseInt(resultSet.getString("id"));
				String first_name = resultSet.getString("first_name");
				String last_name = resultSet.getString("last_name");
				String gender = resultSet.getString("gender");
				int age = Integer.parseInt(resultSet.getString("age"));
				City city = new City(resultSet.getInt("city_id"),
						resultSet.getString("city_name"));

				employees.add(new Employee(id, first_name, last_name, gender, age, city));
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return employees;
	}


	@Override
	public void updateAgeById(int id, int age) {
		try(Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(
				UPDATE)) {

			statement.setInt(1, id);
			statement.setInt(5, age);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteById(int id) {
		try(Connection connection = DriverManager.getConnection(url, user, password);
				PreparedStatement statement = connection.prepareStatement(
				DELETE)) {

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
