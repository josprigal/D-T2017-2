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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import security.UserAccount;

@Entity
@Access(AccessType.PROPERTY)
public class Actor extends CanBeCommented {

	private String name;
	private String email;
	private String phone;

	public Actor() {
		super();
		this.messagesReceived = new ArrayList<>();
		this.messagesSent = new ArrayList<>();
		this.commentsSent = new ArrayList<>();
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
	@Pattern(regexp = "/^(\\+|\\d)[0-9]{7,16}$/")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	private Collection<Message> messagesReceived;
	private Collection<Message> messagesSent;
	private Collection<Comment> commentsSent;

	@OneToMany(mappedBy = "actor")
	public Collection<Comment> getCommentsSent() {
		return this.commentsSent;
	}

	public void setCommentsSent(final Collection<Comment> comments) {
		this.commentsSent = comments;
	}

	private UserAccount userAccount;
 
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

	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	public Collection<Message> getMessagesSent() {
		return messagesSent;
	}

	public void setMessagesSent(Collection<Message> messagesSent) {
		this.messagesSent = messagesSent;
	}



}
