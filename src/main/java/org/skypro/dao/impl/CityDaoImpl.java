package org.skypro.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.skypro.config.HibernateSessionFactoryUtil;
import org.skypro.model.City;

import java.util.List;

public class CityDaoImpl implements CityDao{

	@Override
	public void createCity(City city) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
			Transaction transaction = session.beginTransaction();
			session.save(city);
			transaction.commit();
		}
	}

	@Override
	public City readCityById(int id) {
		return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(City.class, id);
	}

	@Override
	public List<City> readAllCity() {
		List<City> cities = (List<City>) HibernateSessionFactoryUtil
				.getSessionFactory().openSession().createQuery("from City", City.class).list();
		return cities;
	}


	@Override
	public void updateCity(City city) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(city);
			transaction.commit();
		}
	}

	@Override
	public void deleteCity(City city) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(city);
			transaction.commit();
		}
	}
}
