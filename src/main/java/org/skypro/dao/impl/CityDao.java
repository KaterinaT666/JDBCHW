package org.skypro.dao.impl;

import org.skypro.model.City;

import java.sql.SQLException;
import java.util.List;


public interface CityDao {


	void createCity(City city)throws SQLException;

	City readCityById(int id);

	List<City> readAllCity();

	void updateCity(City city);

	void deleteCity(City city);
}
