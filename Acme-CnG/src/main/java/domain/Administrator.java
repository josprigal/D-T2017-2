
package domain;

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
		// TODO Auto-generated constructor stub
	}


	private Collection<Banner>	banner;


	@OneToMany(mappedBy = "actor")
	public Collection<Banner> getBanner() {
		return this.banner;
	}

	public void setBanner(final Collection<Banner> banner) {
		this.banner = banner;
	}

}
