
package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.AdministratorService;
import services.ApplicationService;
import services.CustomerService;
import services.OfferOrRequestService;
import utilities.AbstractTest;
import domain.Customer;
import domain.OfferOrRequest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ApplyForOfferOrRequestUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	CustomerService			customerService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	OfferOrRequestService	offerOrRequestService;

	@Autowired
	ApplicationService		applicationService;


	/*
	 * An actor who is authenticated as a customer must be able to:
	 * Apply for an offer or a request, which must be accepted by the customer who posted
	 * it. Applications can be pending, accepted, or denied
	 */

	@Test
	public void testApply() {
		//Apply un request normal
		super.authenticate("customer1");
		final int size1 = this.applicationService.findAll().size(); //Listing

		final Customer c = this.customerService.findByPrincipal();
		OfferOrRequest or = null;
		for (final OfferOrRequest a : this.offerOrRequestService.findAll())
			if (a.getCustomer() != null) {
				if (a.getCustomer().getId() != c.getId())
					or = a;
			} else
				or = a;
		this.applicationService.addApplication(or);
		final int size2 = this.applicationService.findAll().size(); //Listing
		Assert.isTrue(size1 < size2);
		Assert.notNull(c);
		if (or.getCustomer() != null)
			Assert.isTrue(or.getCustomer().getId() != c.getId());

		this.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Intentar aplicar un request sin estar autenticado

		super.authenticate(null);
		final int size1 = this.applicationService.findAll().size(); //Listing

		final Customer c = this.customerService.findByPrincipal();
		OfferOrRequest or = null;
		for (final OfferOrRequest a : this.offerOrRequestService.findAll())
			if (a.getCustomer() != null) {
				if (a.getCustomer().getId() != c.getId())
					or = a;
			} else
				or = a;
		this.applicationService.addApplication(or);
		final int size2 = this.applicationService.findAll().size(); //Listing
		Assert.isTrue(size1 < size2);
		Assert.notNull(c);
		if (or.getCustomer() != null)
			Assert.isTrue(or.getCustomer().getId() != c.getId());

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAsAdmin() {
		//Intentar apply un request estando autenticado como admin
		super.authenticate("admin");
		final int size1 = this.applicationService.findAll().size(); //Listing

		final Customer c = this.customerService.findByPrincipal();
		OfferOrRequest or = null;
		Assert.notNull(c);
		for (final OfferOrRequest a : this.offerOrRequestService.findAll())
			if (a.getCustomer() != null) {
				if (a.getCustomer().getId() != c.getId())
					or = a;
			} else
				or = a;
		this.applicationService.addApplication(or);
		final int size2 = this.applicationService.findAll().size(); //Listing
		Assert.isTrue(size1 < size2);

		if (or.getCustomer() != null)
			Assert.isTrue(or.getCustomer().getId() != c.getId());

		this.unauthenticate();
	}

}
