
package services;

import java.util.Collection;

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
		Assert.notNull(result);
		return result;
	}

	public void save(final Message Message) {
		Assert.notNull(this.messageRepository);
		this.messageRepository.save(Message);
	}

	public void delete(final Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(this.messageRepository.exists(message.getId()));
		this.messageRepository.delete(message);
	}

}
