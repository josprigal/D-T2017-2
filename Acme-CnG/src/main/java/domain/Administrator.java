
package domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.PROPERTY)
public class Administrator extends Actor {

	public Administrator() {
		super();
		this.banner = new ArrayList<Banner>();
		// TODO Auto-generated constructor stub
	}


	private Collection<Banner>	banner;



}
