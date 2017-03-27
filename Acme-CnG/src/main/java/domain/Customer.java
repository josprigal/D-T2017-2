
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Customer extends Actor {

	public Customer() {
		super();
		this.offerOrRequests = new ArrayList<OfferOrRequest>();
		// TODO Auto-generated constructor stub
	}


	private Collection<OfferOrRequest>	offerOrRequests;
	private Collection<Application> applications;

	@OneToMany(mappedBy = "customer")
	public Collection<OfferOrRequest> getOfferOrRequests() {
		return this.offerOrRequests;
	}

	public void setOfferOrRequests(final Collection<OfferOrRequest> offerOrRequests) {
		this.offerOrRequests = offerOrRequests;
	}

	@OneToMany(mappedBy = "actor")
	public Collection<Application> getApplications() {
		return applications;
	}

	public void setApplications(Collection<Application> applications) {
		this.applications = applications;
	}
}
