
package repositories;

import domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;

import java.util.Collection;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

	@Query("select avg(c.comments.size) from Request c")
	Double avgCommensPerRequest();

	@Query("select ob from Request  ob where (ob.banned= false or ob.customer.id=?1)")
    Collection<Request> findAllNotBaned(Integer id);
}
