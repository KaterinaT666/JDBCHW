package org.skypro.dao.impl;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.skypro.config.HibernateSessionFactoryUtil;
import org.skypro.dao.EmployeeDao;

import org.skypro.model.Employee;
import java.util.List;



public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public void create(Employee employee) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
			Transaction transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
		}
	}

	@Override
	public Employee readById(int id) {
		return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
	}

	@Override
	public List<Employee> readAll() {
		List<Employee> employees = (List<Employee>) HibernateSessionFactoryUtil
				.getSessionFactory().openSession().createQuery("from Employee", Employee.class).list();
		return employees;
	}


	@Override
	public void updateById(Employee employee) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(employee);
			transaction.commit();
		}
	}

	@Override
	public void deleteById(Employee employee) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(employee);
			transaction.commit();
		}
	}
}

