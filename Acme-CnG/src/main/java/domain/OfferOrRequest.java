
package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class OfferOrRequest extends DomainEntity {

	public OfferOrRequest() {
		super();
		this.comments = new ArrayList<Comment>();
		this.applications = new ArrayList<Application>();
		this.banned = false;
		// TODO Auto-generated constructor stub
	}


	private String	title;
	private String	description;
	private Date	moment;
	private boolean	banned;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@NotNull
	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(final boolean banned) {
		this.banned = banned;
	}


	private Place					origin;
	private Place					destination;
	private Collection<Comment>		comments;
	private Collection<Application>	applications;
	private Customer				customer;


	@OneToMany(mappedBy = "offerOrRequest")
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}

	@ManyToOne
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}
	@ManyToMany()
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
	}

	@OneToOne
	public Place getOrigin() {
		return this.origin;
	}

	public void setOrigin(final Place origin) {
		this.origin = origin;
	}

	@OneToOne
	public Place getDestination() {
		return this.destination;
	}

	public void setDestination(final Place destination) {
		this.destination = destination;
	}
}
