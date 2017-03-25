
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import domain.Application;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository	customerRepository;


	public CustomerService() {
		super();
	}
	public void create(final Customer customer) {
		Assert.notNull(customer);
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority a = new Authority();
		a.setAuthority("CUSTOMER");
		authorities.add(a);
		customer.getUserAccount().setAuthorities(authorities);
		Assert.notNull(customer);
		this.customerRepository.save(customer);
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;
		result = this.customerRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Customer findOne(final int id) {
		Assert.isTrue(id != 0);
		Customer result;
		result = this.customerRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Customer customer) {
		Assert.notNull(this.customerRepository);
		this.customerRepository.save(customer);
	}

	public void delete(final Customer customer) {
		Assert.notNull(customer);
		Assert.isTrue(customer.getId() != 0);
		Assert.isTrue(this.customerRepository.exists(customer.getId()));
		this.customerRepository.delete(customer);
	}
	public Double avgOffersAndRequestCustomer() {
		// TODO Auto-generated method stub
		return this.customerRepository.avgOffersAndRequestCustomer();
	}

	public Double avgCommensPerCustomer() {
		// TODO Auto-generated method stub
		return this.customerRepository.avgCommensPerCustomer();
	}
	public Collection<Customer> customerMoreDenied() {
		// TODO Auto-generated method stub
		final int i = 0;
		final Collection<Customer> result = new ArrayList<Customer>();
		final Collection<Customer> customers = this.findAll();
		for (final Customer c : customers)
			if (this.findApplications(c.getApplications(), "DENIED") > i) {
				result.clear();
				result.add(c);
			} else if (this.findApplications(c.getApplications(), "DENIED") == i)
				result.add(c);
		return result;
	}
	public Collection<Customer> customerMoreAccepted() {
		// TODO Auto-generated method stub
		final int i = 0;
		final Collection<Customer> result = new ArrayList<Customer>();
		final Collection<Customer> customers = this.findAll();
		for (final Customer c : customers)
			if (this.findApplications(c.getApplications(), "ACCEPTED") > i) {
				result.clear();
				result.add(c);
			} else if (this.findApplications(c.getApplications(), "ACCEPTED") == i)
				result.add(c);
		return result;
	}
	public Integer findApplications(final Collection<Application> apps, final String state) {
		int i = 0;
		for (final Application a : apps)
			if (a.getType().equalsIgnoreCase(state))
				i++;
		return i;
	}

}
