
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends DomainEntity {

	private String	name;
	private String	email;
	private String	phone;


	public Actor() {
		super();
		this.messages = new ArrayList<Message>();
		this.comments = new ArrayList<Comment>();
		this.application = new ArrayList<Application>();
	}

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@NotBlank
	@Email
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	@NotBlank
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}


	private Collection<Message>		messages;
	private Collection<Comment>		comments;
	private Collection<Application>	application;


	@OneToMany(mappedBy = "actor")
	public Collection<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(final Collection<Message> messages) {
		this.messages = messages;
	}

	@OneToMany(mappedBy = "actor")
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany()
	public Collection<Application> getApplications() {
		return this.application;
	}

	public void setApplications(final Collection<Application> application) {
		this.application = application;
	}


	private UserAccount	userAccount;


	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(final UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public Integer getMessagesSent() {
		int i = 0;
		for (final Message m : this.messages)
			if (m.getSender().equalsIgnoreCase(this.getName()))
				i++;
		return i;
	}
	public void setMessagesSent(final Integer i) {
	}
	public Integer getMessagesReceived() {
		int i = 0;
		for (final Message m : this.messages)
			if (m.getActor().getName().equalsIgnoreCase(this.getName()))
				i++;
		return i;
	}
	public void setMessagesReceived(final Integer i) {
	}

}
