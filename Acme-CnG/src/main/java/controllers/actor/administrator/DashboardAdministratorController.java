
package controllers.actor.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.*;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;

@Controller
@RequestMapping("/actor/administrator/dashboard")
public class DashboardAdministratorController extends AbstractController {

	// SERVICES ------------------------------------------

	@Autowired
	private ActorService			actorService;
	@Autowired
	private CustomerService			customerService;
	@Autowired
	private AdministratorService	administratorService;
	@Autowired
	private OfferOrRequestService	offerOrRequestService;
	@Autowired
	private OfferService			offerService;
	@Autowired
	private RequestService			requestService;

	@Autowired
	private CanBeCommentedService canBeCommentedService;

	// Constructor

	public DashboardAdministratorController() {
		super();
	}

	// Methods

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayDashboard() {
		ModelAndView result;

		final Double ratioOffersVersusRequest = this.offerOrRequestService.ratioOffersVersusRequest();
		final Double avgOffersAndRequestCustomer = this.customerService.avgOffersAndRequestCustomer();
		final Double avgApplicationsPerOfferOrRequest = this.offerOrRequestService.avgApplicationsPerOfferOrRequest();
		final Integer minMessagesSentPerActor = this.actorService.minMessagesSentPerActor();
		final Double avgCommentsPerActor = actorService.avgCommensPerActor();
        final Double avgCommentsPerOffer = offerService.avgCommensPerOffer();
        final Double avgCommentsPerRequest = requestService.avgCommensPerRequest();
        final Double avgCommentsPostedPerActor = actorService.avgCommentsPostedPerActor();
		final Integer maxMessagesSentPerActor = this.actorService.maxMessagesSentPerActor();
		final Double avgMessagesSentPerActor = this.actorService.avgMessagesSentPerActor();
		final Integer minMessagesReceivedPerActor = this.actorService.minMessagesReceivedPerActor();
		final Integer maxMessagesReceivedPerActor = this.actorService.maxMessagesReceivedPerActor();
		final Double avgMessagesReceivedPerActor = this.actorService.avgMessagesReceivedPerActor();
		final Collection<Customer> customerMoreDenied = this.customerService.customerMoreDenied();
		final Collection<Customer> customerMoreAccepted = this.customerService.customerMoreAccepted();
		final Collection<Actor> actorMoreThan10Percent = this.actorService.actorMoreThan10Percent();
		final Collection<Actor> actorHasMoreMessages = this.actorService.actorHasMoreMessages();
		final Collection<Actor> actorSentMoreMessages = this.actorService.actorSentMoreMessages();

		result = new ModelAndView("administrator/dashboard");

		result.addObject("ratioOffersVersusRequest", ratioOffersVersusRequest);
		result.addObject("avgOffersAndRequestCustomer", avgOffersAndRequestCustomer);
		result.addObject("avgApplicationsPerOfferOrRequest", avgApplicationsPerOfferOrRequest);
		result.addObject("avgCommensPerActor", avgCommentsPerActor);
		result.addObject("avgCommensPerOffer", avgCommentsPerOffer);
		result.addObject("avgCommensPerRequest", avgCommentsPerRequest);
		result.addObject("minMessagesSentPerActor", minMessagesSentPerActor);
		result.addObject("maxMessagesSentPerActor", maxMessagesSentPerActor);
		result.addObject("avgMessagesSentPerActor", avgMessagesSentPerActor);
		result.addObject("avgCommentsPostedPerActor",avgCommentsPostedPerActor);
		result.addObject("minMessagesReceivedPerActor", minMessagesReceivedPerActor);
		result.addObject("maxMessagesReceivedPerActor", maxMessagesReceivedPerActor);
		result.addObject("avgMessagesReceivedPerActor", avgMessagesReceivedPerActor);
		result.addObject("customerMoreDenied", customerMoreDenied);
		result.addObject("customerMoreAccepted", customerMoreAccepted);
		result.addObject("actorMoreThan10Percent", actorMoreThan10Percent);
		result.addObject("actorHasMoreMessages", actorHasMoreMessages);
		result.addObject("actorSentMoreMessages", actorSentMoreMessages);

		result.addObject("requestURI","actor/administrator/dashboard.do");

		return result;
	}

}
