package local.test.jba.controller;

import javax.validation.Valid;

import local.test.jba.entity.User;
import local.test.jba.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private UserService userService;
	
	@ModelAttribute("user") // command name nella form
	public User constructUser() {
		return new User();
	}
	
	@RequestMapping
	public String showRegister() {
		return "user-register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String doRegister(@Valid @ModelAttribute("user") User user, BindingResult result) {
		if(result.hasErrors()) {
			return "user-register";
		}
		user = userService.save(user);
		return "redirect:/register.html?success=true";
	}
}
