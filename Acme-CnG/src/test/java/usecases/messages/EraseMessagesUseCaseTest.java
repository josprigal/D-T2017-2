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
public class EraseMessagesUseCaseTest extends AbstractTest {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MessageService messageService;

    /*
     An actor who is authenticated must be able to:
          - Exchange messages with other actors.
     */
    @Test
    public void testPositiveErraseMessages(){
        // Authenticate as customer1, get a message that he/she has received reply and delete it
        // check the message no longer exists
        authenticate("customer1");
        List<Message> messagesReceived = (List<Message>) actorService.findByPrincipal().getMessagesReceived();
        Assert.notNull(messagesReceived);
        Message received =  ((List<Message>) actorService.findByPrincipal().getMessagesReceived()).get(0);
        Integer id = received.getId();
        Message m = received;

        messageService.delete(m);

        Assert.isNull(messageService.findOne(id));
        Assert.notNull(m);

        unauthenticate();
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNegativeListAndReplyMessages(){
        // Try to errase a message being Unauthorized
        // Expect a exception
        authenticate(null);
        Message m = messageService.findOne(25);

        messageService.delete(m);

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
