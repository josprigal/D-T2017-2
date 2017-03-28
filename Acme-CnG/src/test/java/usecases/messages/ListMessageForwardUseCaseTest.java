package usecases.messages;

import domain.Actor;
import domain.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import services.ActorService;
import services.MessageService;
import utilities.AbstractTest;

import java.util.List;

@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ListMessageForwardUseCaseTest extends AbstractTest {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MessageService messageService;

    /*
     An actor who is authenticated must be able to:
          - Exchange messages with other actors.
     */
    @Test
    public void testPositiveListAndReplyMessages(){
        // Authenticate as customer1, get a message that he/she has received reply and check
        // the receiver and sender are correct and listing is correct
        authenticate("customer1");
        List<Message> messagesReceived = (List<Message>) actorService.findByPrincipal().getMessagesReceived();
        Assert.notNull(messagesReceived);
        Message received =  ((List<Message>) actorService.findByPrincipal().getMessagesReceived()).get(0);
        Message m = received;
        received.setSender(actorService.findActorByPrincipal());
        received.setReceiver(actorService.findOne(26));

        Message m2 = messageService.save(m);
        Assert.notNull(m);
        Assert.isTrue(m2.getText().equals(m.getText()));
        Assert.isTrue(m2.getTitle().equals(m.getTitle()));
        Assert.isTrue(m2.getAttachments().equals(m.getAttachments()));

        unauthenticate();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNegativeListAndReplyMessages(){
        // Try to send a message with the sender being other actor and not the principal
        // Expect a exception
        authenticate("customer1");
        Message m =  ((List<Message>) actorService.findByPrincipal().getMessagesReceived()).get(0);
        m.setSender(actorService.findOne(26));
        m.setReceiver(actorService.findOne(30));

        messageService.save(m);

    }

    private Message createMessage(Actor principal, Actor receiver){
        Message m = new Message();
        m.setSender(principal);
        m.setReceiver(receiver);
        m.setText("Texto de prueba");
        m.setAttachments("http://google.es");
        m.setTitle("Titulo");

        return  m;
    }
}
