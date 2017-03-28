
package usecases;

import java.util.Collection;

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
import services.OfferService;
import utilities.AbstractTest;
import domain.Customer;
import domain.Offer;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SearchForOfferOrRequestUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	CustomerService			customerService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	OfferService			offerService;

	@Autowired
	OfferOrRequestService	offerOrRequestService;

	@Autowired
	ApplicationService		applicationService;


	/*
	 * An actor who is authenticated as a customer must be able to:
	 * Search for offers and requests using a single keyword that must appear somewhere
	 * in their titles, descriptions, or places.
	 */

	@Test
	public void testSearch() {
		//Busqueda normal
		super.authenticate("customer1");
		final Customer c = this.customerService.findByPrincipal();

		Assert.notNull(c);
		Offer of = null;
		String busqueda = "";
		for (final Offer o : this.offerService.findAll()) {
			busqueda = o.getTitle();
			of = o;
			break;
		}

		final Collection<Offer> allFound = this.offerOrRequestService.findBySearchOffer(busqueda);
		Assert.notEmpty(allFound);
		Assert.isTrue(allFound.contains(of));

		this.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Buscamos sin estar autenticado

		super.authenticate(null);
		final Customer c = this.customerService.findByPrincipal();

		Assert.notNull(c);
		Offer of = null;
		String busqueda = "";
		for (final Offer o : this.offerService.findAll()) {
			busqueda = o.getTitle();
			of = o;
			break;
		}

		final Collection<Offer> allFound = this.offerOrRequestService.findBySearchOffer(busqueda);
		Assert.notEmpty(allFound);
		Assert.isTrue(allFound.contains(of));

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAsAdmin() {
		//Busqueda como admin
		super.authenticate("admin");
		final Customer c = this.customerService.findByPrincipal();

		Assert.notNull(c);
		Offer of = null;
		String busqueda = "";
		for (final Offer o : this.offerService.findAll()) {
			busqueda = o.getTitle();
			of = o;
			break;
		}

		final Collection<Offer> allFound = this.offerOrRequestService.findBySearchOffer(busqueda);
		Assert.notEmpty(allFound);
		Assert.isTrue(allFound.contains(of));

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testRandomSearch() {
		//Metemos caracteres aleatorios a la busqueda para que no encuentre nada
		super.authenticate("customer1");
		final Customer c = this.customerService.findByPrincipal();

		Assert.notNull(c);
		Offer of = null;
		String busqueda = "";
		for (final Offer o : this.offerService.findAll()) {
			busqueda = o.getTitle();
			of = o;
			break;
		}
		busqueda += "lkdsafjklafdj";

		final Collection<Offer> allFound = this.offerOrRequestService.findBySearchOffer(busqueda);
		Assert.notEmpty(allFound);
		Assert.isTrue(allFound.contains(of));

		this.unauthenticate();
	}

}
