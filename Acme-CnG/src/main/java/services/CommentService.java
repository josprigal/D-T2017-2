
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import repositories.CommentRepository;
import domain.Comment;
import security.LoginService;

@Service
@Transactional
public class CommentService {

	@Autowired
	CommentRepository	commentRepository;
	@Autowired
	ActorService actorService;


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
		Actor a = actorService.findByPrincipal();
		comment.setActor(a);
		this.commentRepository.save(comment);
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		this.commentRepository.delete(comment);
	}


	public List<Comment> getAllNotBannedByCollection(List<Comment> comments) {
		List<Comment> result = new ArrayList<>();
		if (LoginService.hasRole("ADMIN")) return comments;

		for(Comment e: comments){
			if (!e.isBanned()) result.add(e);
			else{
				if (e.getActor().getId() == actorService.findByPrincipal().getId()){
					result.add(e);
				}
			}
		}
		return result;
	}
}
