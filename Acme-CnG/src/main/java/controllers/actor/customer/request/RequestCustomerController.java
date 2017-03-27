
package controllers.actor.customer.request;

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
import services.PlaceService;
import services.RequestService;
import domain.Place;
import domain.Request;
import forms.EditRequestForm;

@Controller
@RequestMapping("/actor/customer/request")
public class RequestCustomerController {

	@Autowired
	private RequestService			requestService;
	@Autowired
	private OfferOrRequestService	requestOrRequestService;
	@Autowired
	private PlaceService			placeService;



	// Listing Request

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listRequest() {
		final ModelAndView result = new ModelAndView("actor/customer/request/list");

		final Collection<Request> requests = this.requestService.findAllNotBaned();

		result.addObject("requests", requests);
		result.addObject("requestURI", "actor/customer/request/list.do");

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView search(final String searchText) {
		final Collection<Request> allFound = this.requestOrRequestService.findBySearchRequest(searchText);

		final ModelAndView result = new ModelAndView("actor/customer/request/list");
		result.addObject("requests", allFound);
		result.addObject("requestURI", "actor/customer/request/search.do");

		return result;
	}
	@RequestMapping("/post")
	public ModelAndView newGet() {
		return this.createNewView(new Request(), new Place(), new Place());
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditRequestForm editRequestForm, final BindingResult bindingResult) {
		final Request request = this.requestService.reconstruct(editRequestForm.getRequest(), bindingResult);
		final Place origin = this.placeService.reconstruct(editRequestForm.getOrigin(), bindingResult, false);
		final Place destination = this.placeService.reconstruct(editRequestForm.getDestination(), bindingResult, false);
		ModelAndView result;
		if (bindingResult.hasErrors())
			return this.createNewView(request, origin, destination);
		else
			try {

				this.placeService.save(origin);
				this.placeService.save(destination);
				final Place origin2 = this.placeService.reconstruct(origin, bindingResult, true);
				final Place destination2 = this.placeService.reconstruct(destination, bindingResult, true);
				request.setOrigin(origin2);
				request.setDestination(destination2);
				this.requestService.save(request);
				result = new ModelAndView("redirect:/actor/customer/request/list.do");
				return result;
			} catch (final Throwable oops) {

				return this.createNewView(request, origin, destination, "request.commit.error");
			}
	}

	private ModelAndView createNewView(final Request request, final Place origin, final Place destination) {
		Assert.notNull(request);

		final ModelAndView result = new ModelAndView("actor/customer/request/post");
		final EditRequestForm editRequestForm = new EditRequestForm();
		editRequestForm.setRequest(request);
		editRequestForm.setOrigin(origin);
		editRequestForm.setDestination(origin);
		result.addObject("form", editRequestForm);

		return result;
	}
	private ModelAndView createNewView(final Request request, final Place origin, final Place destination, final String message) {
		final ModelAndView result = new ModelAndView("actor/customer/request/post");
		final EditRequestForm editRequestForm = new EditRequestForm();
		editRequestForm.setRequest(request);
		editRequestForm.setOrigin(origin);
		editRequestForm.setDestination(origin);
		result.addObject("form", editRequestForm);
		result.addObject("message", message);

		return result;
	}
}
