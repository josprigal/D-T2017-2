
package controllers.actor.customer.offer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OfferOrRequestService;
import services.OfferService;
import domain.Offer;

@Controller
@RequestMapping("/actor/customer/offer")
public class OfferCustomerController {

	@Autowired
	private OfferService			offerService;
	@Autowired
	private OfferOrRequestService	offerOrRequestService;


	/*
	 * @RequestMapping(value = "/new", method = RequestMethod.POST)
	 * public ModelAndView createPost(
	 * 
	 * @ModelAttribute("form") EditPropertyForm editPropertyForm,
	 * BindingResult bindingResult) {
	 * Property property = propertyService.reconstruct(
	 * editPropertyForm.getProperty(),
	 * editPropertyForm.getAttributesValue(), bindingResult, false);
	 * ModelAndView result;
	 * if (bindingResult.hasErrors()) {
	 * return createNewView(property);
	 * } else {
	 * try {
	 * propertyService.save(property);
	 * result = new ModelAndView(
	 * "redirect:/actor/lessor/property/list.do");
	 * return result;
	 * } catch (Throwable oops) {
	 * return createNewView(property);
	 * }
	 * }
	 * }
	 */

	// Listing Offer

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listOffer() {
		final ModelAndView result = new ModelAndView("actor/customer/offer/list");

		final Collection<Offer> offers = this.offerService.findAll();

		result.addObject("offers", offers);
		result.addObject("requestURI", "actor/customer/offer/list.do");

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(final String searchText) {
		final Collection<Offer> allFound = this.offerOrRequestService.findBySearchOffer(searchText);

		final ModelAndView result = new ModelAndView("actor/customer/offer/list");
		result.addObject("offers", allFound);
		result.addObject("requestURI", "actor/customer/offer/search.do");

		return result;
	}

}
