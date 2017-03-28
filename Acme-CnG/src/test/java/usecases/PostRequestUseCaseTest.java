
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
import services.PlaceService;
import services.RequestService;
import utilities.AbstractTest;
import domain.Customer;
import domain.Place;
import domain.Request;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class PostRequestUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	CustomerService			customerService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	RequestService			requestService;

	@Autowired
	PlaceService			placeService;


	/*
	 * An actor who is authenticated as a customer must be able to:
	 * Post a request in which he or she informs that he or she wishes to move from a
	 * place to another one and would like to find someone with whom he or she can share
	 * the trip.
	 */

	@Test
	public void testPostRequest() {
		//Postear un request normal
		super.authenticate("customer1");
		final int size1 = this.requestService.findAll().size();//Listing
		final Customer c = this.customerService.findByPrincipal();
		final Request o = this.requestService.create();
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
		final Request ores = this.requestService.save(o);
		final int size2 = this.requestService.findAll().size();

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
	public void testPostRequest2() {
		//Postear un request con comienzo baneeado (esto no se podria hacer desde el controller habria que banearlo posteriormente)
		super.authenticate("customer1");

		final Customer c = this.customerService.findByPrincipal();
		final int size1 = this.requestService.findAll().size(); //Listing

		final Request o = this.requestService.create();
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
		final Request ores = this.requestService.save(o);
		final int size2 = this.requestService.findAll().size();

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
		//Intentar postear un request sin estar autenticado
		this.authenticate(null);
		final Customer c = this.customerService.findByPrincipal();
		final Request o = this.requestService.create();
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
		final Request ores = this.requestService.save(o);
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
		//Intentar postear un request estando autenticado como admin
		super.authenticate("admin");
		final Customer c = this.customerService.findByPrincipal();
		final Request o = this.requestService.create();
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
		final Request ores = this.requestService.save(o);
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

		final Request o = this.requestService.create();
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

		final Request request = this.requestService.save(o);
		Assert.notNull(request.getTitle());
		Assert.notNull(request.getDescription());
		Assert.notNull(request.getMoment());
		Assert.isTrue(request.getTitle() != "");
		Assert.isTrue(request.getDescription() != "");
		Assert.notNull(c);

		this.unauthenticate();

	}

}
