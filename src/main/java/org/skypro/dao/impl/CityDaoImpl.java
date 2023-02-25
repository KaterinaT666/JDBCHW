package org.skypro.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.skypro.config.HibernateSessionFactoryUtil;
import org.skypro.model.City;

import java.util.List;

public class CityDaoImpl implements CityDao{

	@Override
	public void create(City city) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
			Transaction transaction = session.beginTransaction();
			session.save(city);
			transaction.commit();
		}
	}

	@Override
	public City readById(int id) {
		return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(City.class, id);
	}

	@Override
	public List<City> readAll() {
		List<City> cities = (List<City>) HibernateSessionFactoryUtil
				.getSessionFactory().openSession().createQuery("from City", City.class).list();
		return cities;
	}


	@Override
	public City updateById(City city) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(city);
			transaction.commit();
		}
		return updateById(city);
	}

	@Override
	public void deleteById(int id) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(id);
			transaction.commit();
		}
	}
}
