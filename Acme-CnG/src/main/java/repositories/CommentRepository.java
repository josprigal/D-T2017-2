package repositories;

import domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{
	
	@Query("select distinct c from Comment c  where (c.banned = false or c.actor =  ?1)")
    public List<Comment> findNotBanned(Actor a);
}