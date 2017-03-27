
package controllers.actor.customer.comment;

import java.util.Date;

import domain.*;
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
import forms.EditCommentForm;

@Controller
@RequestMapping("/actor/comment")
public class CommentCustomerController {

	@Autowired
	CommentService	commentService;


	public CommentCustomerController() {
		super();
	}

	@RequestMapping("/{canBeCommented}/new")
	public ModelAndView newGet(@PathVariable CanBeCommented canBeCommented) {
		final Comment c = new Comment();
		c.setCanBeCommented(canBeCommented);
		return this.createNewView(c);
	}
	@RequestMapping(value = "/{canBeCommented}/new", method = RequestMethod.POST)
	public ModelAndView createPost(@ModelAttribute("form") final EditCommentForm editCommentForm,
								   final BindingResult bindingResult, @PathVariable CanBeCommented canBeCommented) {
		ModelAndView result;
		editCommentForm.getComment().setMoment(new Date());
		if (bindingResult.hasErrors()) {
			for (final ObjectError e : bindingResult.getAllErrors())
				System.out.println(e.toString());
			return this.createNewView(editCommentForm.getComment());

		} else
			try {
		        Comment  c = editCommentForm.getComment();
		        c.setCanBeCommented(canBeCommented);
				this.commentService.save(c);
				result = new ModelAndView("redirect:list.do");
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

    @RequestMapping("/{canBeCommented}/list")
    public ModelAndView listComment(@PathVariable CanBeCommented canBeCommented) {
        ModelAndView result = new ModelAndView("comment/list");
        result.addObject("comments",commentService.getAllNotBannedByCollection(canBeCommented.getComments()));
        result.addObject("requestURI","/actor/comment/"+canBeCommented.getId()+"/list.do");
        result.addObject("id",canBeCommented.getId());

        return result;
    }

}
