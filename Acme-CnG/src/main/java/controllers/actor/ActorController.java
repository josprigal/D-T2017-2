package controllers.actor;

import controllers.AbstractController;
import domain.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.ActorService;

import java.util.Collection;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController{


    @Autowired
    private ActorService actorService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listActors() {
        final ModelAndView result = new ModelAndView("actor/list");

        result.addObject("actors", actorService.findAll());
        result.addObject("requestURI", "actor/list.do");

        return result;
    }
}
