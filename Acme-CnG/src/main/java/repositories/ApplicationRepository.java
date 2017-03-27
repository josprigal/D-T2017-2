package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

import java.util.Collection;

@Repository
public interface ApplicationRepository extends JpaRepository<Application,Integer>{


    @Query("select a from Application  a where a.offerOrRequest.customer.id=?1")
    Collection<Application> findAllByCustomer(int id);
}