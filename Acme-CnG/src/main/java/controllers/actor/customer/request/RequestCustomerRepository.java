
package controllers.actor.customer.request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OfferOrRequestService;
import services.RequestService;
import domain.Request;

@Controller
@RequestMapping("/actor/customer/request")
public class RequestCustomerRepository {

	@Autowired
	private RequestService			requestService;
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
		final ModelAndView result = new ModelAndView("actor/customer/request/list");

		final Collection<Request> requests = this.requestService.findAll();

		result.addObject("requests", requests);
		result.addObject("requestURI", "actor/customer/request/list.do");

		return result;
	}
}
