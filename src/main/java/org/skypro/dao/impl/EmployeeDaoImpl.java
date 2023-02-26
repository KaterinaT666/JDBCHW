package org.skypro.dao.impl;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.skypro.config.HibernateSessionFactoryUtil;
import org.skypro.dao.EmployeeDao;

import org.skypro.model.Employee;
import java.util.List;



public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public void createEmployee(Employee employee) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();) {
			Transaction transaction = session.beginTransaction();
			session.save(employee);
			transaction.commit();
		}
	}

	@Override
	public Employee readEmployeeById(int id) {
		return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Employee.class, id);
	}

	@Override
	public List<Employee> readAllEmployees() {
		List<Employee> employees = (List<Employee>) HibernateSessionFactoryUtil
				.getSessionFactory().openSession().createQuery("from Employee", Employee.class).list();
		return employees;
	}


	@Override
	public void updateEmployee(Employee employee) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(employee);
			transaction.commit();
		}
	}

	@Override
	public void deleteEmployee(Employee employee) {
		try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(employee);
			transaction.commit();
		}
	}
}

