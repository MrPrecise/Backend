package com.myapp.myapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myapp.myapp.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	private EntityManager em;

	@Autowired
	public CustomerDAOImpl(EntityManager em) {
		this.em = em;
	}

	@Override
	@Transactional
	public List<Customer> findAll() {
		Session currentSession = em.unwrap(Session.class);

		Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);

		List<Customer> customers = query.getResultList();

		return customers;
	}

	@Override
	@Transactional
	public Customer getCustomerById(String id) {
		Session currentSession = em.unwrap(Session.class);

		Customer customer = currentSession.get(Customer.class, id);

		return customer;
	}

	@Override
	@Transactional
	public Customer save(Customer theCustomer) {
		Session currentSession = em.unwrap(Session.class);
		currentSession.save(theCustomer);
		System.out.println(theCustomer.getId());
		return theCustomer;
	}

}
