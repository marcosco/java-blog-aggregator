package local.test.jba.controller;

import java.security.Principal;

import javax.validation.Valid;

import local.test.jba.entity.Blog;
import local.test.jba.entity.User;
import local.test.jba.service.BlogService;
import local.test.jba.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;

	@ModelAttribute("blog") // command name nella form
	public Blog constructBlog() {
		return new Blog();
	}
	
	@RequestMapping
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "account";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String doAddBlog(Model model, @Valid @ModelAttribute("blog") Blog blog, BindingResult result,Principal principal) {
		if(result.hasErrors()) {
			return account(model, principal);
		}
		String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}

}
