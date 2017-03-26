
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
		this.messagesReceived = new ArrayList<>();
		this.messagesSent = new ArrayList<>();
		this.comments = new ArrayList<Comment>();
		this.applications = new ArrayList<Application>();
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


	private Collection<Message> messagesReceived;
	private Collection<Message> messagesSent;
	private Collection<Comment>		comments;
	private Collection<Application>	applications;



	@OneToMany(mappedBy = "actor")
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}

	@OneToMany()
	public Collection<Application> getApplications() {
		return this.applications;
	}

	public void setApplications(final Collection<Application> applications) {
		this.applications = applications;
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

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	public Collection<Message> getMessagesReceived() {
		return messagesReceived;
	}

	public void setMessagesReceived(Collection<Message> messagesReceived) {
		this.messagesReceived = messagesReceived;
	}

	@OneToMany(mappedBy = "sender",cascade = CascadeType.ALL)
	public Collection<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(Collection<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", messagesReceived=" + messagesReceived +
                ", messagesSent=" + messagesSent +
                ", comments=" + comments +
                ", applications=" + applications +
                ", userAccount=" + userAccount +
                '}';
    }
}
