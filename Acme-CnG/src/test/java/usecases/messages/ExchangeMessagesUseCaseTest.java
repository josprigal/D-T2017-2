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
import repositories.ActorRepository;
import services.ActorService;
import services.MessageService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
        "classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ExchangeMessagesUseCaseTest extends AbstractTest {

    @Autowired
    private ActorService actorService;

    @Autowired
    private MessageService messageService;

    /*
     An actor who is authenticated must be able to:
          - Exchange messages with other actors.
     */
    @Test
    public void testPositiveExchangeMessages(){
        //Authenticate as customer 1, check that the message are created sucessfully and check that sender and
        // recipient are correct

        authenticate("customer1");
        Message m = createMessage( actorService.findActorByPrincipal(), actorService.findOne(26));

        Message m2 = messageService.save(m);
        Assert.notNull(m2);
        Assert.isTrue(m2.getSender() == m.getSender());
        Assert.isTrue(m2.getReceiver() == m.getReceiver());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testNegativeExchangeMessages(){
        // Authenticate as customer1, no receiver and check that an IllegalArgumentException is thrown
        authenticate("customer1");
        Message m = createMessage( actorService.findActorByPrincipal(), null);

        Message m2 = messageService.save(m);
        Assert.notNull(m);
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
