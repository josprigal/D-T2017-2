
package controllers.Customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomerService;
import domain.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ActorService		actorService;

	@Autowired
	CustomerService		customerService;

	@Autowired
	UserDetailsService	userDetailsService;


	@RequestMapping(value = "/register")
	public ModelAndView index() {
		return this.createEditModelAndView(new Customer(), null);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "register")
	public ModelAndView create(@ModelAttribute("user") @Valid final Customer customer, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(customer, "wrong");
		else
			try {

				final Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

				customer.getUserAccount().setPassword(md5PasswordEncoder

				.encodePassword(customer.getUserAccount().getPassword(), null));

				this.customerService.create(customer);

				final UserDetails userDetails = this.userDetailsService.loadUserByUsername(customer.getUserAccount().getUsername());

				final UsernamePasswordAuthenticationToken auth =

				new UsernamePasswordAuthenticationToken(userDetails, customer.getUserAccount().getPassword(),

				userDetails.getAuthorities());
				if (auth.isAuthenticated())
					SecurityContextHolder.getContext().setAuthentication(auth);
				result = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(customer, "wrong");
			}

		return result;

	}

	protected ModelAndView createEditModelAndView(final Customer customer, final String message) {

		ModelAndView result;
		result = new ModelAndView("customer/register");
		result.addObject("customer", customer);
		result.addObject("message", message);
		return result;

	}
}
