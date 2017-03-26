
package controllers.actor.customer.offer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OfferOrRequestService;
import services.OfferService;
import domain.Offer;
import forms.EditOfferForm;

@Controller
@RequestMapping("/actor/customer/offer")
public class OfferCustomerController {

	@Autowired
	private OfferService			offerService;
	@Autowired
	private OfferOrRequestService	offerOrRequestService;


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

	@RequestMapping("/new")
	public ModelAndView newGet() {
		return this.createNewView(new Offer());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditOfferForm editOfferForm, final BindingResult bindingResult) {
		final Offer offer = this.offerService.reconstruct(editOfferForm.getOffer(), bindingResult);
		ModelAndView result;
		if (bindingResult.hasErrors())
			return this.createNewView(offer);
		else
			try {
				this.offerService.save(offer);
				result = new ModelAndView("redirect:/actor/lessor/offer/list.do");
				return result;
			} catch (final Throwable oops) {
				return this.createNewView(offer);
			}
	}

	private ModelAndView createNewView(final Offer offer) {
		Assert.notNull(offer);

		final ModelAndView result = new ModelAndView("actor/customer/offer/edit");
		final EditOfferForm editOfferForm = new EditOfferForm();
		editOfferForm.setOffer(offer);
		result.addObject("form", editOfferForm);

		return result;
	}
}
