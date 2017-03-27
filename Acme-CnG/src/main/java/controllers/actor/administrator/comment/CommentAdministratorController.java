
package controllers.actor.administrator.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import domain.Comment;

@Controller
@RequestMapping("/administrator/comment")
public class CommentAdministratorController {

	@Autowired
	CommentService	commentService;


	public CommentAdministratorController() {
		super();
	}

	@RequestMapping("/list")
	public ModelAndView listComment() {
		final ModelAndView result = new ModelAndView("actor/comment/ban");
		result.addObject("comments", this.commentService.findAll());
		result.addObject("requestURI", "/administrator/comment/list.do");

		return result;
	}
	@RequestMapping("/{comment}/ban")
	public ModelAndView ban(@PathVariable final Comment comment) {
		comment.setBanned(true);
		this.commentService.save(comment);
		final ModelAndView result = new ModelAndView("actor/comment/ban");
		result.addObject("comments", this.commentService.findAll());
		result.addObject("requestURI", "/administrator/comment/list.do");

		return result;
	}
	@RequestMapping("/{comment}/unban")
	public ModelAndView unban(@PathVariable final Comment comment) {
		comment.setBanned(false);
		this.commentService.save(comment);
		final ModelAndView result = new ModelAndView("actor/comment/ban");
		result.addObject("comments", this.commentService.findAll());
		result.addObject("requestURI", "/administrator/comment/list.do");

		return result;
	}
}
