/* AdministratorController.java
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import domain.Banner;
import domain.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import services.BannerService;
import services.RequestService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}

	@Autowired
    private BannerService bannerService;

	@Autowired
    private RequestService requestService;
		
	// Action-1 ---------------------------------------------------------------		

	@RequestMapping("/changeBanner")
	public ModelAndView action1() {
		return changeBannerView(bannerService.findAll().get(0));
	}

    public ModelAndView changeBannerView(Banner banner) {
        ModelAndView result;

        result = new ModelAndView("administrator/changeBanner");
        result.addObject("banner",banner);

        return result;
    }

	@RequestMapping(value = "/changeBanner",method = RequestMethod.POST)
	public ModelAndView changeBannerPost(@ModelAttribute("banner") Banner banner, BindingResult bindingResult) {
		ModelAndView result = changeBannerView(bannerService.findAll().get(0));

		try{
		    banner = bannerService.changeBanner(banner.getImage());
            result = changeBannerView(banner);
		    return  result;
        }catch (Throwable throwable){
            result.addObject("error","wrong");
        }

		return result;
	}

    @RequestMapping("/banrequest")
    public ModelAndView banrequest() {
        return banRequestView();
    }

    @RequestMapping("/banrequest/{request}")
    public ModelAndView banrequest(@PathVariable Request request) {
        Assert.notNull(request);
        try{
            requestService.banRequest(request);
        }catch (Throwable throwable){

        }

        return new ModelAndView("redirect:banrequest.do");
    }

    public ModelAndView banRequestView() {
        ModelAndView result;

        result = new ModelAndView("administrator/banrequest");
        result.addObject("requests",requestService.findAll());

        return result;
    }
	
}