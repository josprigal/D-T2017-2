
package services;

import java.util.Collection;

import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Message;

@Service
@Transactional
public class MessageService {

	@Autowired
	MessageRepository	messageRepository;

	@Autowired
	private ActorService actorService;


	public MessageService() {
		super();
	}

	public Collection<Message> findAll() {
		Collection<Message> result;
		result = this.messageRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Message findOne(final int id) {
		Assert.isTrue(id != 0);
		Message result;
		result = this.messageRepository.findOne(id);
		return result;
	}

	public Message save(final Message Message) {
		Assert.notNull(Message);
		Assert.notNull(Message.getReceiver());
		Assert.isTrue(Message.getSender() == actorService.findByPrincipal());
		return this.messageRepository.save(Message);
	}

	public void delete(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(this.messageRepository.exists(message.getId()));
		Assert.notNull(actorService.findActorByPrincipal());
		this.messageRepository.delete(message);
	}

    public void newMessage(Message message) {
		Assert.notNull(message);
		Actor actor = actorService.findByPrincipal();
		Assert.notNull(actor);
		Assert.notNull(message.getReceiver());
		message.setSender(actor);
		save(message);
    }
}
