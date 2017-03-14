
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {

	public Message() {
		super();
		// TODO Auto-generated constructor stub
		this.sent = new Date();
	}


	private String	sender;
	private Date	sent;
	private String	title;
	private String	text;
	private String	attachments;


	@NotBlank
	public String getSender() {
		return this.sender;
	}

	public void setSender(final String sender) {
		this.sender = sender;
	}

	@NotNull
	@Past
	public Date getSent() {
		return this.sent;
	}

	public void setSent(final Date sent) {
		this.sent = sent;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@URL
	public String getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final String attachments) {
		this.attachments = attachments;
	}


	private Actor	actor;


	@ManyToOne()
	public Actor getActor() {
		return this.actor;
	}

	public void setActor(final Actor actor) {
		this.actor = actor;
	}

}
