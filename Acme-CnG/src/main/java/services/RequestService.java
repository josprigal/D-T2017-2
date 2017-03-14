
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Request;

@Service
@Transactional
public class RequestService {

	@Autowired
	RequestRepository	requestRepository;


	public RequestService() {
		super();
	}

	public Collection<Request> findAll() {
		Collection<Request> result;
		result = this.requestRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Request findOne(final int id) {
		Assert.isTrue(id != 0);
		Request result;
		result = this.requestRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Request request) {
		Assert.notNull(this.requestRepository);
		this.requestRepository.save(request);
	}

	public void delete(final Request request) {
		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);
		Assert.isTrue(this.requestRepository.exists(request.getId()));
		this.requestRepository.delete(request);
	}

}
