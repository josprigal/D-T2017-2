package controllers.actor.administrator;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;
import domain.Customer;

@Controller
@RequestMapping("/actor/administrator/dashboard")
public class DashboardAdministratorController extends AbstractController {

	// SERVICES ------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructor

	public DashboardAdministratorController() {
		super();
	}

	// Methods

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayDashboard() {
		ModelAndView result;

		Double ratioOffersVersusRequest = null;
		Double avgOffersCustomer = null;
		Double avgRequestCustomer = null;
		Double avgApplicationsPerOffer = null;
		Double avgApplicationsPerRequest = null;
		Double avgCommensPerAdmin = null;
		Double avgCommensPerCustomer = null;
		Double avgCommensPerActor = null;
		Double avgCommensPerOffer = null;
		Double avgCommensPerRequest = null;
		Integer minMessagesSentPerActor = null;
		Integer maxMessagesSentPerActor = null;
		Double avgMessagesSentPerActor = null;
		Integer minMessagesReceivedPerActor = null;
		Integer maxMessagesReceivedPerActor = null;
		Double avgMessagesReceivedPerActor = null;
		Collection<Customer> customerMoreDenied = null;
		Collection<Customer> customerMoreAccepted = null;
		Collection<Actor> actorMoreThan10Percent = null;
		Collection<Actor> actorHasMoreMessages = null;
		Collection<Actor> actorSentMoreMessages = null;

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
