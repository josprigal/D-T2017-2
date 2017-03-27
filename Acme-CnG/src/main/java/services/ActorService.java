
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import security.LoginService;

@Service
@Transactional
public class ActorService {

	@Autowired
	ActorRepository	actorRepository;
	@Autowired
	CommentService	commentService;


	public ActorService() {
		super();
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;
		result = this.actorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Actor findOne(final int id) {
		Assert.isTrue(id != 0);
		Actor result;
		result = this.actorRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Actor findByPrincipal(){
		Actor result = actorRepository.findByUserAccountId(LoginService.getPrincipal().getId());
		Assert.notNull(result);

		return result;
	}

	public void save(final Actor actor) {
		Assert.notNull(this.actorRepository);
		this.actorRepository.save(actor);
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));
		this.actorRepository.delete(actor);
	}

	public Double avgCommensPerActor() {
		// TODO Auto-generated method stub
		return this.actorRepository.avgCommensPerActor();
	}

	public Integer minMessagesSentPerActor() {
		// TODO Auto-generated method stub
		return this.actorRepository.minMessagesSentPerActor();
	}

	public Integer maxMessagesSentPerActor() {
		// TODO Auto-generated method stub
		return this.actorRepository.maxMessagesSentPerActor();
	}

	public Double avgMessagesSentPerActor() {
		// TODO Auto-generated method stub
		return this.actorRepository.avgMessagesSentPerActor();
	}

	public Integer minMessagesReceivedPerActor() {
		// TODO Auto-generated method stub
		return this.actorRepository.minMessagesReceivedPerActor();
	}

	public Integer maxMessagesReceivedPerActor() {
		// TODO Auto-generated method stub
		return this.actorRepository.maxMessagesReceivedPerActor();
	}

	public Double avgMessagesReceivedPerActor() {
		// TODO Auto-generated method stub
		return this.actorRepository.avgMessagesReceivedPerActor();
	}

	public Collection<Actor> actorMoreThan10Percent() {
		return actorRepository.postedMoreThan10PercentAvg();
	}

	public Collection<Actor> actorHasMoreMessages() {
		// TODO Auto-generated method stub
		return this.actorRepository.actorHasMoreMessages();
	}

	public Collection<Actor> actorSentMoreMessages() {
		// TODO Auto-generated method stub
		return this.actorRepository.actorSentMoreMessages();
	}
	public Actor findActorByPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		return this.actorRepository.findByUserAccountId(userAccount.getId());
	}

	public Double avgCommentsPostedPerActor() {
		return actorRepository.avgCommentsPostedPerActor();
	}
}
