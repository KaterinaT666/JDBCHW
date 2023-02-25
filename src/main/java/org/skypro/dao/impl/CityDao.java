package org.skypro.dao.impl;

import org.skypro.model.City;

import java.util.List;


public interface CityDao {


	void create(City city);

	City readById(int id);

	List<City> readAll();

	City updateById(City city);

	void deleteById(int id);
}
