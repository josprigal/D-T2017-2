
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	@Autowired
	CommentRepository	commentRepository;


	public CommentService() {
		super();
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

	public void save(final Comment comment) {
		Assert.notNull(this.commentRepository);
		this.commentRepository.save(comment);
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		this.commentRepository.delete(comment);
	}

}
