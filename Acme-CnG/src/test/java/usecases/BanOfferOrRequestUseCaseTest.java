
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
import services.BannerService;
import services.CustomerService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Administrator;
import domain.Request;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BanOfferOrRequestUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	CustomerService			customerService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	RequestService			requestService;

	@Autowired
	BannerService			bannerService;


	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * - Ban an offer or a request that he or she finds inappropriate. Such offers and requests
	 * must not be displayed to a general audience, only to the administrators and
	 * the customer who posted it
	 */

	@Test
	public void testBan() {
		//Banear request como admin
		super.authenticate("admin");
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		Request re = null;
		for (final Request r : this.requestService.findAll())
			if (!r.isBanned())
				re = r;
		this.requestService.banRequest(re);
		Assert.isTrue(re.isBanned());

		this.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Banear request sin estar autentificado

		super.authenticate(null);
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		Request re = null;
		for (final Request r : this.requestService.findAll())
			if (!r.isBanned())
				re = r;
		this.requestService.banRequest(re);
		Assert.isTrue(re.isBanned());
		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAsCustomer() {
		//Banear request como customer
		super.authenticate("customer1");
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		Request re = null;
		for (final Request r : this.requestService.findAll())
			if (!r.isBanned())
				re = r;
		this.requestService.banRequest(re);
		Assert.isTrue(re.isBanned());
		this.unauthenticate();
	}

}
