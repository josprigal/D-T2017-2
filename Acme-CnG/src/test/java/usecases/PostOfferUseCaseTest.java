
package usecases;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.AdministratorService;
import services.CustomerService;
import services.OfferService;
import services.PlaceService;
import utilities.AbstractTest;
import domain.Customer;
import domain.Offer;
import domain.Place;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostOfferUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	CustomerService			customerService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	OfferService			offerService;

	@Autowired
	PlaceService			placeService;


	/*
	 * An actor who is authenticated as a customer must be able to:
	 * -Post an offer in which he or she advertises that hes going to move from a place to
	 * another place and would like to share his or her car with someone else.
	 */

	@Test
	public void testPostOffer() {
		//Postear un offer normal
		super.authenticate("customer1");
		final int size1 = this.offerService.findAll().size();//Listing
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("Titulo prueba");
		o.setDescription("Desc Prueba");
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(false);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		final int size2 = this.offerService.findAll().size();

		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.isTrue(size1 < size2);
		Assert.notNull(c);

		this.unauthenticate();
	}
	@Test
	public void testPostOffer2() {
		//Postear un offer con comienzo baneeado (esto no se podria hacer desde el controller habria que banearlo posteriormente)
		super.authenticate("customer1");

		final Customer c = this.customerService.findByPrincipal();
		final int size1 = this.offerService.findAll().size(); //Listing

		final Offer o = this.offerService.create();
		o.setTitle("Titulo prueba");
		o.setDescription("Desc Prueba");
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(true);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		final int size2 = this.offerService.findAll().size();

		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.notNull(c);
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.isTrue(size1 < size2);

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Intentar postear un offer sin estar autenticado
		this.authenticate(null);
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("Titulo prueba");
		o.setDescription("Desc Prueba");
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(false);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.notNull(c);
		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAsAdmin() {
		//Intentar postear un offer estando autenticado como admin
		super.authenticate("admin");
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("Titulo prueba");
		o.setDescription("Desc Prueba");
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(false);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.isTrue(c instanceof Customer);
		Assert.notNull(c);

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testTitleNull() {
		//Fallo por title null
		super.authenticate("customer1");

		final Customer c = this.customerService.findByPrincipal();

		final Offer o = this.offerService.create();
		o.setTitle(null);
		o.setDescription("Desc Prueba");
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(false);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);

		final Offer offer = this.offerService.save(o);
		Assert.notNull(offer.getTitle());
		Assert.notNull(offer.getDescription());
		Assert.notNull(offer.getMoment());
		Assert.isTrue(offer.getTitle() != "");
		Assert.isTrue(offer.getDescription() != "");
		Assert.notNull(c);

		this.unauthenticate();

	}
	@Test(expected = IllegalArgumentException.class)
	public void testTitleBlank() {
		//Fallo por title vacio
		super.authenticate("customer1");
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("");
		o.setDescription("Desc Prueba");
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(false);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.notNull(c);

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testDescNull() {
		//Fallo por descripcion null
		super.authenticate("customer1");
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("Titulo de prueba");
		o.setDescription(null);
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(false);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.notNull(c);

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testDescBlank() {
		//Fallo por descripcion vacia
		super.authenticate("customer1");
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("Titulo de prueba");
		o.setDescription("");
		o.setMoment(new Date(System.currentTimeMillis() - 1));
		o.setBanned(false);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.notNull(c);

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testMomentNull() {
		//Fallo por moment null
		super.authenticate("customer1");
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("Titulo de prueba");
		o.setDescription("Desc prueba");
		o.setMoment(null);
		o.setBanned(false);
		o.setCustomer(c);
		Place p = null;
		for (final Place pl : this.placeService.findAll()) {
			p = pl;
			break;
		}
		o.setOrigin(p);
		o.setDestination(p);
		final Offer ores = this.offerService.save(o);
		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.notNull(c);

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoPlaces() {
		//Fallo por places null
		super.authenticate("customer1");
		final Customer c = this.customerService.findByPrincipal();
		final Offer o = this.offerService.create();
		o.setTitle("Titulo de prueba");
		o.setDescription("Desc prueba");
		o.setMoment(null);
		o.setBanned(false);
		o.setCustomer(c);
		o.setOrigin(null);
		o.setDestination(null);
		final Offer ores = this.offerService.save(o);
		Assert.notNull(ores.getTitle());
		Assert.notNull(ores.getDescription());
		Assert.notNull(ores.getMoment());
		Assert.isTrue(ores.getTitle() != "");
		Assert.isTrue(ores.getDescription() != "");
		Assert.notNull(c);

		this.unauthenticate();
	}
}
