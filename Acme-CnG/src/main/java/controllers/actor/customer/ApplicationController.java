package controllers.actor.customer;

import controllers.AbstractController;
import domain.Actor;
import domain.Application;
import domain.Customer;
import domain.OfferOrRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import security.LoginService;
import services.ActorService;
import services.ApplicationService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/application")
public class ApplicationController extends AbstractController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listApplications() {
        final ModelAndView result = new ModelAndView("application/list");

        result.addObject("applications", applicationService.findAllPrincipal());
        result.addObject("requestURI", "actor/list.do");

        return result;
    }

    @RequestMapping(value = "/sended", method = RequestMethod.GET)
    public ModelAndView sendedApplications() {
        final ModelAndView result = new ModelAndView("application/listMade");
        Assert.isTrue(LoginService.hasRole("CUSTOMER"));
        Customer actor = (Customer) actorService.findByPrincipal();

        result.addObject("applications", actor.getApplications());
        result.addObject("requestURI", "actor/sended.do");

        return result;
    }

    @RequestMapping(value = "/{application}/change", method = RequestMethod.GET)
    public ModelAndView listActors(@RequestParam("action") String action, @PathVariable Application application
            ,HttpServletRequest request) {
        Assert.isTrue(application.getOfferOrRequest().getCustomer().getId() == actorService.findByPrincipal().getId());
        applicationService.changeType(application, action);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:"+ referer);
    }

    @RequestMapping(value = "/{offerOrRequest}/new", method = RequestMethod.GET)
    public ModelAndView newApplication(@PathVariable OfferOrRequest offerOrRequest,HttpServletRequest request) {
        Assert.notNull(actorService.findByPrincipal());
        Assert.notNull(offerOrRequest);
        Assert.isTrue(offerOrRequest.getCustomer().getId() != actorService.findByPrincipal().getId());
       applicationService.addApplication(offerOrRequest);
        String referer = request.getHeader("Referer");
        return new ModelAndView("redirect:"+ referer);
    }
}
