package local.test.jba.controller;

import local.test.jba.entity.Blog;
import local.test.jba.service.BlogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/blog/remove/{id}")
	public String removeBlog(@PathVariable int id) {
		Blog blog = blogService.findOne(id);
		blogService.delete(blog);
		return "redirect:/account.html";
	}
}
