
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
		final Double avgApplicationsPerOffer = this.offerService.avgApplicationsPerOffer();
		final Double avgApplicationsPerRequest = this.requestService.avgApplicationsPerRequest();
		final Double avgCommensPerAdmin = this.administratorService.avgCommensPerAdmin();
		final Double avgCommensPerCustomer = this.customerService.avgCommensPerCustomer();
		final Double avgCommensPerActor = this.actorService.avgCommensPerActor();
		final Double avgCommensPerOffer = this.offerService.avgCommensPerOffer();
		final Double avgCommensPerRequest = this.requestService.avgCommensPerRequest();
		final Integer minMessagesSentPerActor = this.actorService.minMessagesSentPerActor();
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

		result = this.createDashboardModelAndView(ratioOffersVersusRequest, avgOffersAndRequestCustomer, avgApplicationsPerOffer, avgApplicationsPerRequest, avgCommensPerAdmin, avgCommensPerCustomer, avgCommensPerActor, avgCommensPerOffer,
			avgCommensPerRequest, minMessagesSentPerActor, maxMessagesSentPerActor, avgMessagesSentPerActor, minMessagesReceivedPerActor, maxMessagesReceivedPerActor, avgMessagesReceivedPerActor, customerMoreDenied, customerMoreAccepted,
			actorMoreThan10Percent, actorHasMoreMessages, actorSentMoreMessages);
		return result;
	}
	protected ModelAndView createDashboardModelAndView(final Double ratioOffersVersusRequest, final Double avgOffersAndRequestCustomer, final Double avgApplicationsPerOffer, final Double avgApplicationsPerRequest, final Double avgCommensPerAdmin,
		final Double avgCommensPerCustomer, final Double avgCommensPerActor, final Double avgCommensPerOffer, final Double avgCommensPerRequest, final Integer minMessagesSentPerActor, final Integer maxMessagesSentPerActor,
		final Double avgMessagesSentPerActor, final Integer minMessagesReceivedPerActor, final Integer maxMessagesReceivedPerActor, final Double avgMessagesReceivedPerActor, final Collection<Customer> customerMoreDenied,
		final Collection<Customer> customerMoreAccepted, final Collection<Actor> actorMoreThan10Percent, final Collection<Actor> actorHasMoreMessages, final Collection<Actor> actorSentMoreMessages) {
		ModelAndView result;

		result = new ModelAndView("actor/administrator/dashboard");

		result.addObject("ratioOffersVersusRequest", ratioOffersVersusRequest);
		result.addObject("avgOffersAndRequestCustomer", avgOffersAndRequestCustomer);
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
		result.addObject("minMessagesReceivedPerActor", minMessagesReceivedPerActor);
		result.addObject("maxMessagesReceivedPerActor", maxMessagesReceivedPerActor);
		result.addObject("avgMessagesReceivedPerActor", avgMessagesReceivedPerActor);
		result.addObject("customerMoreDenied", customerMoreDenied);
		result.addObject("customerMoreAccepted", customerMoreAccepted);
		result.addObject("actorMoreThan10Percent", actorMoreThan10Percent);
		result.addObject("actorHasMoreMessages", actorHasMoreMessages);
		result.addObject("actorSentMoreMessages", actorSentMoreMessages);

		return result;
	}

}
