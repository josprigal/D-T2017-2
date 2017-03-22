package controllers.actor.administrator;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import services.ActorService;
import services.AdministratorService;
import services.CustomerService;
import services.OfferOrRequestService;
import services.OfferService;
import services.RequestService;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;

@Controller
@RequestMapping("/actor/administrator/dashboard")
public class DashboardAdministratorController extends AbstractController {

	// SERVICES ------------------------------------------

	@Autowired
	private ActorService actorService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private OfferOrRequestService offerOrRequestService;
	@Autowired
	private OfferService offerService;
	@Autowired
	private RequestService requestService;

	// Constructor

	public DashboardAdministratorController() {
		super();
	}

	// Methods

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayDashboard() {
		ModelAndView result;

		Double ratioOffersVersusRequest = offerOrRequestService.ratioOffersVersusRequest();
		Double avgOffersCustomer = customerService.avgOffersCustomer();
		Double avgRequestCustomer = customerService.avgRequestCustomer();
		Double avgApplicationsPerOffer = offerService.avgApplicationsPerOffer();
		Double avgApplicationsPerRequest = requestService.avgApplicationsPerRequest();
		Double avgCommensPerAdmin = administratorService.avgCommensPerAdmin();
		Double avgCommensPerCustomer = customerService.avgCommensPerCustomer();
		Double avgCommensPerActor = actorService.avgCommensPerActor();
		Double avgCommensPerOffer = offerService.avgCommensPerOffer();
		Double avgCommensPerRequest = requestService.avgCommensPerRequest();
		Integer minMessagesSentPerActor = actorService.minMessagesSentPerActor();
		Integer maxMessagesSentPerActor = actorService.maxMessagesSentPerActor();
		Double avgMessagesSentPerActor = actorService.avgMessagesSentPerActor();
		Integer minMessagesReceivedPerActor = actorService.minMessagesReceivedPerActor();
		Integer maxMessagesReceivedPerActor = actorService.maxMessagesReceivedPerActor();
		Double avgMessagesReceivedPerActor = actorService.avgMessagesReceivedPerActor();
		Collection<Customer> customerMoreDenied = customerService.customerMoreDenied();
		Collection<Customer> customerMoreAccepted = customerService.customerMoreAccepted();
		Collection<Actor> actorMoreThan10Percent = actorService.actorMoreThan10Percent();
		Collection<Actor> actorHasMoreMessages = actorService.actorHasMoreMessages();
		Collection<Actor> actorSentMoreMessages = actorService.actorSentMoreMessages();

		result = createDashboardModelAndView(ratioOffersVersusRequest,
				avgOffersCustomer, avgRequestCustomer, avgApplicationsPerOffer,
				avgApplicationsPerRequest, avgCommensPerAdmin,
				avgCommensPerCustomer, avgCommensPerActor, avgCommensPerOffer,
				avgCommensPerRequest, minMessagesSentPerActor,
				maxMessagesSentPerActor, avgMessagesSentPerActor,
				minMessagesReceivedPerActor, maxMessagesReceivedPerActor,
				avgMessagesReceivedPerActor, customerMoreDenied,
				customerMoreAccepted, actorMoreThan10Percent,
				actorHasMoreMessages, actorSentMoreMessages);
		return result;
	}

	protected ModelAndView createDashboardModelAndView(
			Double ratioOffersVersusRequest, Double avgOffersCustomer,
			Double avgRequestCustomer, Double avgApplicationsPerOffer,
			Double avgApplicationsPerRequest, Double avgCommensPerAdmin,
			Double avgCommensPerCustomer, Double avgCommensPerActor,
			Double avgCommensPerOffer, Double avgCommensPerRequest,
			Integer minMessagesSentPerActor, Integer maxMessagesSentPerActor,
			Double avgMessagesSentPerActor,
			Integer minMessagesReceivedPerActor,
			Integer maxMessagesReceivedPerActor,
			Double avgMessagesReceivedPerActor,
			Collection<Customer> customerMoreDenied,
			Collection<Customer> customerMoreAccepted,
			Collection<Actor> actorMoreThan10Percent,
			Collection<Actor> actorHasMoreMessages,
			Collection<Actor> actorSentMoreMessages) {
		ModelAndView result;

		result = new ModelAndView("actor/administrator/dashboard");

		result.addObject("ratioOffersVersusRequest", ratioOffersVersusRequest);
		result.addObject("avgOffersCustomer", avgOffersCustomer);
		result.addObject("avgRequestCustomer", avgRequestCustomer);
		result.addObject("avgApplicationsPerOffer", avgApplicationsPerOffer);
		result.addObject("avgApplicationsPerRequest", avgApplicationsPerRequest);
		result.addObject("avgCommensPerAdmin", avgCommensPerAdmin);
		result.addObject("avgCommensPerCustomer", avgCommensPerCustomer);
		result.addObject("avgCommensPerActor", avgCommensPerActor);
		result.addObject("avgCommensPerOffer", avgCommensPerOffer);
		result.addObject("avgCommensPerRequest", avgCommensPerRequest);
		result.addObject("minMessagesSentPerActor", minMessagesSentPerActor);
		result.addObject("maxMessagesSentPerActor", maxMessagesSentPerActor);
		result.addObject("avgMessagesSentPerActor", avgMessagesSentPerActor);
		result.addObject("minMessagesReceivedPerActor",
				minMessagesReceivedPerActor);
		result.addObject("maxMessagesReceivedPerActor",
				maxMessagesReceivedPerActor);
		result.addObject("avgMessagesReceivedPerActor",
				avgMessagesReceivedPerActor);
		result.addObject("customerMoreDenied", customerMoreDenied);
		result.addObject("customerMoreAccepted", customerMoreAccepted);
		result.addObject("actorMoreThan10Percent", actorMoreThan10Percent);
		result.addObject("actorHasMoreMessages", actorHasMoreMessages);
		result.addObject("actorSentMoreMessages", actorSentMoreMessages);

		return result;
	}

}
