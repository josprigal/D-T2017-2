
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CanBeCommentedRepository;
import domain.CanBeCommented;

@Service
@Transactional
public class CanBeCommentedService {

	@Autowired
	private CanBeCommentedRepository	canBeCommentedRepository;


	public Double avgComments() {

		return this.canBeCommentedRepository.avgComments();
	}

	public Collection<CanBeCommented> findAll() {
		Collection<CanBeCommented> result;
		result = this.canBeCommentedRepository.findAll();
		Assert.notNull(result);
		return result;
	}
}
