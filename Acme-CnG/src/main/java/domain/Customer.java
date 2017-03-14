
package domain;

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
		// TODO Auto-generated constructor stub
	}


	private Collection<OfferOrRequest>	offerOrRequests;


	@OneToMany(mappedBy = "customer")
	public Collection<OfferOrRequest> getOfferOrRequests() {
		return this.offerOrRequests;
	}

	public void setOfferOrRequests(final Collection<OfferOrRequest> offerOrRequests) {
		this.offerOrRequests = offerOrRequests;
	}

}
