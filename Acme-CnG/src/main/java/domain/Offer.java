
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Offer extends OfferOrRequest {

	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
