
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
import services.CanBeCommentedService;
import services.CommentService;
import services.CustomerService;
import services.OfferOrRequestService;
import utilities.AbstractTest;
import domain.Actor;
import domain.CanBeCommented;
import domain.Comment;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CommentUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	CustomerService			customerService;

	@Autowired
	AdministratorService	administratorService;

	@Autowired
	OfferOrRequestService	offerOrRequestService;

	@Autowired
	CommentService			commentService;

	@Autowired
	CanBeCommentedService	canBeCommentedService;


	/*
	 * - An actor who is authenticated must be able to:
	 * - Post a comment on another actor, on an offer, or a request
	 */

	@Test
	public void testComment() {
		//Comentar como customer
		super.authenticate("customer1");
		final int size1 = this.commentService.findAll().size(); //Listing

		final Actor a = this.actorService.findByPrincipal();
		Assert.notNull(a);
		CanBeCommented cb = null;
		for (final CanBeCommented cbc : this.canBeCommentedService.findAll()) {
			cb = cbc;
			break;
		}
		final Comment c = this.commentService.create();
		c.setCanBeCommented(cb);
		c.setActor(a);
		c.setBanned(false);
		c.setMoment(new Date(System.currentTimeMillis() - 1));
		c.setRate(3);
		c.setText("Texto ejemplo");
		c.setTitle("titulo ejemplo");
		this.commentService.save(c);
		final int size2 = this.commentService.findAll().size(); //Listing
		Assert.isTrue(size1 < size2);

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Intentar comentar sin estar autenticado

		super.authenticate(null);
		final int size1 = this.commentService.findAll().size(); //Listing

		final Actor a = this.actorService.findByPrincipal();
		Assert.notNull(a);
		CanBeCommented cb = null;
		for (final CanBeCommented cbc : this.canBeCommentedService.findAll()) {
			cb = cbc;
			break;
		}
		final Comment c = this.commentService.create();
		c.setCanBeCommented(cb);
		c.setActor(a);
		c.setBanned(false);
		c.setMoment(new Date(System.currentTimeMillis() - 1));
		c.setRate(3);
		c.setText("Texto ejemplo");
		c.setTitle("titulo ejemplo");
		this.commentService.save(c);
		final int size2 = this.commentService.findAll().size(); //Listing
		Assert.isTrue(size1 < size2);

		this.unauthenticate();
	}

}
