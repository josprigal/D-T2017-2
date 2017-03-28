
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.RequestRepository;
import security.LoginService;
import domain.Request;

@Service
@Transactional
public class RequestService {

	@Autowired
	RequestRepository		requestRepository;

	@Autowired
	private ActorService	actorService;


	public RequestService() {
		super();
	}
	public Request create() {
		final Request request = new Request();
		return request;
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

	public Request save(final Request request) {
		Assert.notNull(this.requestRepository);
		final Request res = this.requestRepository.save(request);
		return res;
	}

	public void delete(final Request request) {
		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);
		Assert.isTrue(this.requestRepository.exists(request.getId()));
		this.requestRepository.delete(request);
	}

	public Double avgApplicationsPerRequest() {
		// TODO Auto-generated method stub
		return null;
	}

	public Double avgCommensPerRequest() {
		// TODO Auto-generated method stub
		return this.requestRepository.avgCommensPerRequest();
	}
	public Request reconstruct(final Request offer, final BindingResult bindingResult) {

		return offer;
	}
	public void banRequest(final Request request) {
		Assert.isTrue(LoginService.hasRole("ADMIN"));
		request.setBanned(true);

		this.save(request);
	}

	public Collection<Request> findAllNotBaned() {
		Collection<Request> result;
		if (LoginService.hasRole("ADMIN")) {
			result = this.requestRepository.findAll();
			Assert.notNull(result);
			return result;
		}
		result = this.requestRepository.findAllNotBaned(this.actorService.findByPrincipal().getId());
		Assert.notNull(result);

		return result;
	}
}
