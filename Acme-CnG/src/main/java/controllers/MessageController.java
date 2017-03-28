package controllers;


import domain.Actor;
import domain.Banner;
import domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;
import services.MessageService;

import java.util.Collection;

@Controller
@RequestMapping("/message")
public class MessageController extends AbstractController{

    @Autowired
    private ActorService actorService;

    @Autowired
    private MessageService messageService;


    @RequestMapping("/list")
    public ModelAndView list() {
        Actor a = actorService.findByPrincipal();
        Assert.notNull(a);

        return listView(a.getMessagesReceived());
    }

    private ModelAndView listView(Collection<Message> messages) {
        ModelAndView result;

        result = new ModelAndView("message/list");
        result.addObject("messages",messages);

        return result;
    }

    @RequestMapping("/new")
    public ModelAndView newMessageMessage(@RequestParam(value = "message",required = false) Message message,
                                   @RequestParam(value = "recipient",required = false) Actor actor) {
        Message message1 = new Message();
        if (message!= null){
            message1.setAttachments(message.getAttachments());
            message1.setText(message.getText());
            message1.setTitle(message.getTitle());
        }
        if (actor != null){
            message1.setReceiver(actor);
        }
        return newView(message1);
    }


    private ModelAndView newView(Message message) {
        ModelAndView result;

        result = new ModelAndView("message/new");
        result.addObject("message1",message);

        return result;
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    public ModelAndView newMessagePost(@ModelAttribute("message1") Message message) {
        Assert.notNull(message);
        try{
            messageService.newMessage(message);
            return new ModelAndView("redirect:list.do");
        } catch (Exception e){
            return newView(message);
        }
    }


    @RequestMapping(value = "/delete")
    public ModelAndView deleteMessage(@RequestParam("message") Message message) {
        Assert.notNull(message);
        try{
            messageService.delete(message);
            return new ModelAndView("redirect:list.do");
        } catch (Exception e){
            return new ModelAndView("redirect:list.do");
        }
    }

}
