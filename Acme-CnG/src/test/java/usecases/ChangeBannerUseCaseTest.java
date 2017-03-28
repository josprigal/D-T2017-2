
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
import services.OfferOrRequestService;
import utilities.AbstractTest;
import domain.Administrator;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChangeBannerUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	CustomerService			customerService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	OfferOrRequestService	offerOrRequestService;

	@Autowired
	BannerService			bannerService;


	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * - Change the banner that the system shows on the welcome page
	 */

	@Test
	public void testChange() {
		//Cambiar banner como admin
		super.authenticate("admin");
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		final String s = "http://img.clipartall.com/clipart-banner-tumundografico-banner-clip-art-8326_2675.png";
		this.bannerService.changeBanner(s);
		Assert.isTrue(this.bannerService.getPrincipal().getImage().equalsIgnoreCase(s));

		this.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Cambiar banner sin estar autentificado

		super.authenticate(null);
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		final String s = "http://img.clipartall.com/clipart-banner-tumundografico-banner-clip-art-8326_2675.png";
		this.bannerService.changeBanner(s);
		Assert.isTrue(this.bannerService.getPrincipal().getImage().equalsIgnoreCase(s));
		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAsCustomer() {
		//Cambiar banner como customer
		super.authenticate("customer1");
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		final String s = "http://img.clipartall.com/clipart-banner-tumundografico-banner-clip-art-8326_2675.png";
		this.bannerService.changeBanner(s);
		Assert.isTrue(this.bannerService.getPrincipal().getImage().equalsIgnoreCase(s));

		this.unauthenticate();
	}

}
