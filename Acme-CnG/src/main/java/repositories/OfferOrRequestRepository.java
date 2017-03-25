
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.OfferOrRequest;

@Repository
public interface OfferOrRequestRepository extends JpaRepository<OfferOrRequest, Integer> {

	@Query("select avg(o.applications.size) from OfferOrRequest o")
	Double avgApplicationsPerOfferOrRequest();
}
