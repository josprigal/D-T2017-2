
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int userAccountId);

	@Query("select avg(c.comments.size) from Actor c")
	Double avgCommensPerActor();

	@Query("select min(c.messagesSent.size) from Actor c")
	Integer minMessagesSentPerActor();

	@Query("select max(c.messagesSent.size) from Actor c")
	Integer maxMessagesSentPerActor();

	@Query("select avg(c.messagesSent.size) from Actor c")
	Double avgMessagesSentPerActor();

	@Query("select min(c.messagesReceived.size) from Actor c")
	Integer minMessagesReceivedPerActor();

	@Query("select max(c.messagesReceived.size) from Actor c")
	Integer maxMessagesReceivedPerActor();

	@Query("select avg(c.messagesReceived.size) from Actor c")
	Double avgMessagesReceivedPerActor();

	@Query("select c from Actor c where c.messagesReceived.size=(select max(a.messagesReceived.size)from Actor a)")
	Collection<Actor> actorHasMoreMessages();

	@Query("select c from Actor c where c.messagesSent.size=(select max(a.messagesSent.size)from Actor a)")
	Collection<Actor> actorSentMoreMessages();
}
