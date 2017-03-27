
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Application extends DomainEntity {

	public Application() {
		super();
	}


	private String	type;
	private OfferOrRequest offerOrRequest;
	private Actor actor;

	@NotBlank
	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	@ManyToOne
    public OfferOrRequest getOfferOrRequest() {
        return offerOrRequest;
    }

    public void setOfferOrRequest(OfferOrRequest offerOrRequest) {
        this.offerOrRequest = offerOrRequest;
    }

    @ManyToOne
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
}
