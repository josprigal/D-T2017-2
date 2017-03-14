
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	@Autowired
	CustomerRepository	customerRepository;


	public CustomerService() {
		super();
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

}
