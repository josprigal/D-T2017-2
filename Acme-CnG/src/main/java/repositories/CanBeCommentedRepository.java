package repositories;

import domain.CanBeCommented;
import domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CanBeCommentedRepository extends JpaRepository<CanBeCommented,Integer>{

    @Query("select avg(c.comments.size) from CanBeCommented c")
	public Double avgComments();
}