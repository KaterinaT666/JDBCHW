package org.skypro.dao.impl;

import jdbc.ConnectionManager;
import org.skypro.dao.EmployeeDao;
import org.skypro.model.City;
import org.skypro.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl implements EmployeeDao {

	private final CityDao cityDao = new CityDaoImpl();

	public EmployeeDaoImpl(){
	}
	private static final String READ_ALL = "SELECT * FROM employee";
	private static final String INSERT =
			"INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)";
	private static final String READ_BY_ID =
			"SELECT * FROM employee WHERE id= ?";
	private static final String UPDATE =
			"UPDATE employee SET first_name =?, last_name =?, gender =?, age =?, city_id =?  WHERE id= ?";
	private static final String DELETE = "DELETE FROM employee WHERE id=?";

	private static final String FIND_LAST_EMPLOYEE = "SELECT * FROM employee ORDER BY id DESC LIMIT 1";


	@Override
	public Optional<Employee> create(Employee employee) {
		int cityId = Integer.parseInt(null);
		if (employee.getCity() != null && cityDao.findById(employee.getCity().getCityId()).isPresent()){
			cityId = employee.getCity().getCityId();
		}
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastname());
			preparedStatement.setString(3, employee.getGender());
			preparedStatement.setInt(4, employee.getAge());
			preparedStatement.setObject(5, cityId);

			if(preparedStatement.executeUpdate() !=0){
				try(Statement findLastStatement = connection.createStatement();
				ResultSet resultSet = findLastStatement.executeQuery(FIND_LAST_EMPLOYEE)){
					if (resultSet.next()){
						return Optional.of(readEmployee(resultSet));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Employee> readById(int id) {
		try (Connection connection = ConnectionManager.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return Optional.of(readEmployee(resultSet));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return Optional.empty();
	}

	@Override
	public List<Employee> readAll() {
		List<Employee> employees = new ArrayList<>();
		try (Connection connection = ConnectionManager.getConnection();
				Statement statement = connection.createStatement ()){
			ResultSet resultSet = statement.executeQuery(READ_ALL);
			while (resultSet.next()){
				employees.add(readEmployee(resultSet));
			}
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
		return employees;
	}


	@Override
	public Optional<Employee> updateById(Employee employee) {
		int cityId = Integer.parseInt(null);
		if (employee.getCity() != null && cityDao.findById(employee.getCity().getCityId()).isPresent()){
			cityId = employee.getCity().getCityId();
		}
		try (Connection connection = ConnectionManager.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastname());
			preparedStatement.setString(3, employee.getGender());
			preparedStatement.setInt(4, employee.getAge());
			preparedStatement.setObject(5, cityId);
			preparedStatement.setInt(6, employee.getId());

			if(preparedStatement.executeUpdate() !=0) {
				return readById(employee.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Optional<Employee> deleteById(int id) {
		Optional<Employee> employeeOptional = readById(id);
		if (employeeOptional.isPresent()){
			try (Connection connection = ConnectionManager.getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
				preparedStatement.setInt(1,id);
				if(preparedStatement.executeUpdate() !=0) {
					return employeeOptional;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Optional.empty();
	}

	private Employee readEmployee (ResultSet resultSet)throws SQLException{
		Integer cityId = resultSet.getObject("city_id", Integer.class);
		City city = null;
		if(cityId!=null){
			city = cityDao.findById(cityId).orElse(null);
		}
		return new Employee(
				resultSet.getInt("id"),
				resultSet.getString("first_name"),
				resultSet.getString("last_name"),
				resultSet.getString("gender"),
				resultSet.getInt("age"),
				city
		);
	}
}
