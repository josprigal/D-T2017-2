package controllers.actor.customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OfferOrRequestService;
import services.OfferService;

import domain.Offer;
import domain.OfferOrRequest;


@Controller
@RequestMapping("/actor/customer/offer")
public class OfferCustomerController {
	@Autowired
	private OfferService offerService;
	@Autowired
	private OfferOrRequestService offerOrRequestService;

/*	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView createPost(
			@ModelAttribute("form") EditPropertyForm editPropertyForm,
			BindingResult bindingResult) {
		Property property = propertyService.reconstruct(
				editPropertyForm.getProperty(),
				editPropertyForm.getAttributesValue(), bindingResult, false);
		ModelAndView result;
		if (bindingResult.hasErrors()) {
			return createNewView(property);
		} else {
			try {
				propertyService.save(property);
				result = new ModelAndView(
						"redirect:/actor/lessor/property/list.do");
				return result;
			} catch (Throwable oops) {
				return createNewView(property);
			}
		}
	}*/

	// Listing Offer

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOffer() {
		ModelAndView result;
		Collection<Offer> offers;

		offers = offerService.findAll();

		result = new ModelAndView("offer/administrator/list");
		result.addObject("offers", offers);
		result.addObject("requestURI", "offer/list.do");

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(String searchText) {
		Collection<OfferOrRequest> allFound = offerOrRequestService
				.findBySearch(searchText);
		Collection<Offer> offers = new ArrayList<Offer>();
		for (OfferOrRequest o : allFound) {
			boolean b = o.getClass().getName().equals("Offer");
			if (b)
				offers.add((Offer) o);
		}
		ModelAndView result = new ModelAndView("offer/list");
		result.addObject("offers", offers);
		result.addObject("requestURI", "/offer/search.do");

		return result;
	}

}
