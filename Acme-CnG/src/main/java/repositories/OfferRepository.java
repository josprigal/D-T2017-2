
package repositories;

import domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Offer;

import java.util.Collection;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer> {

	@Query("select avg(c.comments.size) from Offer c")
	Double avgCommensPerOffer();

	@Query("select ob from Offer  ob where (ob.banned= false or ob.customer.id=?1)")
    Collection<Offer> findAllNotBaned(Integer principal);
}
