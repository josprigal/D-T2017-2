
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
import services.PlaceService;
import domain.Offer;
import domain.Place;
import forms.EditOfferForm;

@Controller
@RequestMapping("/actor/customer/offer")
public class OfferCustomerController {

	@Autowired
	private OfferService			offerService;
	@Autowired
	private OfferOrRequestService	offerOrRequestService;
	@Autowired
	private PlaceService			placeService;


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

	@RequestMapping("/post")
	public ModelAndView newGet() {
		return this.createNewView(new Offer(), new Place(), new Place());
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditOfferForm editOfferForm, final BindingResult bindingResult) {
		final Offer offer = this.offerService.reconstruct(editOfferForm.getOffer(), bindingResult);
		final Place origin = this.placeService.reconstruct(editOfferForm.getOrigin(), bindingResult, false);
		final Place destination = this.placeService.reconstruct(editOfferForm.getDestination(), bindingResult, false);
		System.out.println(origin);
		System.out.println(destination);
		ModelAndView result;
		if (bindingResult.hasErrors())
			return this.createNewView(offer, origin, destination);
		else
			try {

				this.placeService.save(origin);
				this.placeService.save(destination);
				final Place origin2 = this.placeService.reconstruct(origin, bindingResult, true);
				final Place destination2 = this.placeService.reconstruct(destination, bindingResult, true);
				offer.setOrigin(origin2);
				offer.setDestination(destination2);
				this.offerService.save(offer);
				result = new ModelAndView("redirect:/actor/customer/offer/list.do");
				return result;
			} catch (final Throwable oops) {
				return this.createNewView(offer, origin, destination, "offer.commit.error");
			}
	}

	private ModelAndView createNewView(final Offer offer, final Place origin, final Place destination) {
		Assert.notNull(offer);

		final ModelAndView result = new ModelAndView("actor/customer/offer/post");
		final EditOfferForm editOfferForm = new EditOfferForm();
		editOfferForm.setOffer(offer);
		editOfferForm.setOrigin(origin);
		editOfferForm.setDestination(origin);
		result.addObject("form", editOfferForm);

		return result;
	}

	private ModelAndView createNewView(final Offer offer, final Place origin, final Place destination, final String message) {
		final ModelAndView result = new ModelAndView("actor/customer/offer/post");
		final EditOfferForm editOfferForm = new EditOfferForm();
		editOfferForm.setOffer(offer);
		editOfferForm.setOrigin(origin);
		editOfferForm.setDestination(origin);
		result.addObject("form", editOfferForm);
		result.addObject("message", message);

		return result;
	}
}
