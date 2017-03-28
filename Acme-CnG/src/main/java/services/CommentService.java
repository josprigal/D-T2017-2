
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import security.LoginService;
import domain.Actor;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	CommentRepository	commentRepository;
	@Autowired
	ActorService		actorService;


	public CommentService() {
		super();
	}
	public Comment create() {
		return new Comment();
	}
	public Collection<Comment> findAll() {
		Collection<Comment> result;
		result = this.commentRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Comment findOne(final int id) {
		Assert.isTrue(id != 0);
		Comment result;
		result = this.commentRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(this.commentRepository);
		final Actor a = this.actorService.findByPrincipal();
		comment.setActor(a);
		return this.commentRepository.save(comment);
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		this.commentRepository.delete(comment);
	}

	public List<Comment> getAllNotBannedByCollection(final List<Comment> comments) {
		final List<Comment> result = new ArrayList<>();
		if (LoginService.hasRole("ADMIN"))
			return comments;

		for (final Comment e : comments)
			if (!e.isBanned())
				result.add(e);
			else if (e.getActor().getId() == this.actorService.findByPrincipal().getId())
				result.add(e);
		return result;
	}
}
