
package services;

import java.util.Collection;

import domain.Actor;
import domain.OfferOrRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import security.LoginService;

@Service
@Transactional
public class ApplicationService {

	@Autowired
	ApplicationRepository	applicationRepository;

	@Autowired
	private ActorService actorService;


	public ApplicationService() {
		super();
	}

	public Collection<Application> findAll() {
		Collection<Application> result;
		result = this.applicationRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Application findOne(final int id) {
		Assert.isTrue(id != 0);
		Application result;
		result = this.applicationRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Application application) {
		Assert.notNull(this.applicationRepository);
		this.applicationRepository.save(application);
	}

	public void delete(final Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(this.applicationRepository.exists(application.getId()));
		this.applicationRepository.delete(application);
	}

    public Collection<Application> findAllPrincipal() {
		Actor a = actorService.findByPrincipal();
		Assert.notNull(a);
		Collection<Application> result = applicationRepository.findAllByCustomer(a.getId());
		Assert.notNull(result);

		return result;
    }

    public void addApplication(OfferOrRequest offerOrRequest) {
	    Assert.notNull(offerOrRequest);
	    Assert.notNull(LoginService.hasRole("CUSTOMER"));
	    Application a = new Application();
	    a.setOfferOrRequest(offerOrRequest);
	    a.setType("PENDING");
	    a.setActor(actorService.findByPrincipal());
        save(a);
    }

    public void changeType(Application application, String action) {
	    Assert.notNull(action);
        Assert.notNull(LoginService.hasRole("CUSTOMER"));
        application.setType(action);

        save(application);
    }
}
