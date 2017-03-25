
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query("select avg(c.offerOrRequests.size) from Customer c")
	Double avgOffersAndRequestCustomer();
	@Query("select avg(c.comments.size) from Customer c")
	Double avgCommensPerCustomer();
}
