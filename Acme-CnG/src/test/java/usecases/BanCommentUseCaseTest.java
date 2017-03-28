
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
import services.CanBeCommentedService;
import services.CommentService;
import services.CustomerService;
import services.OfferOrRequestService;
import utilities.AbstractTest;
import domain.Administrator;
import domain.Comment;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BanCommentUseCaseTest extends AbstractTest {

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
	 * An actor who is authenticated as an administrator must be able to:
	 * - Ban a comment that he or she finds inappropriate. Such comments must not be
	 * displayed to a general audience, only to the administrators and the actor who posted
	 * it.
	 */

	@Test
	public void testComment() {
		//Banear como admin
		super.authenticate("admin");
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		Comment c = null;
		for (final Comment cbc : this.commentService.findAll())
			if (!cbc.isBanned()) {
				c = cbc;
				break;
			}

		c.setBanned(true);

		final Comment res = this.commentService.save(c);
		Assert.isTrue(res.isBanned());

		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Intentar banear un comentario sin estar autenticado

		super.authenticate(null);
		final Administrator a = this.administratorService.findByPrincipal();
		Assert.notNull(a);
		Comment c = null;
		for (final Comment cbc : this.commentService.findAll())
			if (!cbc.isBanned()) {
				c = cbc;
				break;
			}

		c.setBanned(true);

		final Comment res = this.commentService.save(c);
		Assert.isTrue(res.isBanned());

		this.unauthenticate();
	}

}
