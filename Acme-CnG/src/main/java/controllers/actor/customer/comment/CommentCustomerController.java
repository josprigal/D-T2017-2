
package controllers.actor.customer.comment;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Actor;
import domain.Comment;
import domain.Offer;
import domain.Request;
import forms.EditCommentForm;

@Controller
@RequestMapping("/actor/comment")
public class CommentCustomerController {

	@Autowired
	CommentService	commentService;


	public CommentCustomerController() {
		super();
	}

	@RequestMapping("/a/{actor}/new")
	public ModelAndView newGet(@PathVariable final Actor actor) {
		final Comment c = new Comment();
		c.setActor(actor);
		return this.createNewView(c);
	}
	@RequestMapping("/o/{offer}/new")
	public ModelAndView newGet(@PathVariable final Offer offer) {
		final Comment c = new Comment();
		c.setOfferOrRequest(offer);
		return this.createNewView(c);
	}
	@RequestMapping("/r/{request}/new")
	public ModelAndView newGet(@PathVariable final Request request) {
		final Comment c = new Comment();
		c.setOfferOrRequest(request);
		return this.createNewView(c);
	}
	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditCommentForm editCommentForm, final BindingResult bindingResult) {
		ModelAndView result;
		editCommentForm.getComment().setMoment(new Date());
		if (bindingResult.hasErrors()) {
			for (final ObjectError e : bindingResult.getAllErrors())
				System.out.println(e.toString());
			return this.createNewView(editCommentForm.getComment());

		} else
			try {
				this.commentService.save(editCommentForm.getComment());
				result = new ModelAndView("redirect:/");
				return result;
			} catch (final Throwable oops) {
				return this.createNewView(editCommentForm.getComment());
			}
	}
	@RequestMapping(value = "/r/{request}/new", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditCommentForm editCommentForm, @PathVariable final Request request, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			return this.createNewView(editCommentForm.getComment());
		else
			try {
				this.commentService.save(editCommentForm.getComment());
				result = new ModelAndView("redirect:/");
				return result;
			} catch (final Throwable oops) {
				return this.createNewView(editCommentForm.getComment());
			}
	}
	@RequestMapping(value = "/o/{offer}/new", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditCommentForm editCommentForm, @PathVariable final Offer offer, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			return this.createNewView(editCommentForm.getComment());
		else
			try {
				this.commentService.save(editCommentForm.getComment());
				result = new ModelAndView("redirect:/");
				return result;
			} catch (final Throwable oops) {
				return this.createNewView(editCommentForm.getComment());
			}
	}
	@RequestMapping(value = "/a/{actor}/new", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditCommentForm editCommentForm, @PathVariable final Actor actor, final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			return this.createNewView(editCommentForm.getComment());
		else
			try {
				this.commentService.save(editCommentForm.getComment());
				result = new ModelAndView("redirect:/");
				return result;
			} catch (final Throwable oops) {
				return this.createNewView(editCommentForm.getComment());
			}
	}

	private ModelAndView createNewView(final Comment comment) {
		Assert.notNull(comment);
		final ModelAndView result = new ModelAndView("actor/comment/edit");
		final EditCommentForm editCommentForm = new EditCommentForm();
		editCommentForm.setComment(comment);
		result.addObject("form", editCommentForm);

		return result;
	}
}
