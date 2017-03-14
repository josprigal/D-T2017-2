
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Place extends DomainEntity {

	public Place() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String	address;
	private String	gpsCoordinates;


	@NotBlank
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getGpsCoordinates() {
		return this.gpsCoordinates;
	}

	public void setGpsCoordinates(final String gpsCoordinates) {
		this.gpsCoordinates = gpsCoordinates;
	}

}
