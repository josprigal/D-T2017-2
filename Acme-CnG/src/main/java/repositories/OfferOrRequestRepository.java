
package repositories;

import domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.OfferOrRequest;

import java.util.Collection;

@Repository
public interface OfferOrRequestRepository extends JpaRepository<OfferOrRequest, Integer> {

	@Query("select avg(o.applications.size) from OfferOrRequest o")
	Double avgApplicationsPerOfferOrRequest();

	@Query("select ob from OfferOrRequest  ob where (ob.banned= false or ob.customer=?1)")
	Collection<OfferOrRequest> findAllNotBaned(Actor a);
}
