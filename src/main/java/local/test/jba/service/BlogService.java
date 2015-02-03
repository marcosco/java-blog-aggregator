package local.test.jba.service;

import java.util.List;

import local.test.jba.entity.Blog;
import local.test.jba.entity.Item;
import local.test.jba.entity.User;
import local.test.jba.exception.RssException;
import local.test.jba.repository.BlogRepository;
import local.test.jba.repository.ItemRepository;
import local.test.jba.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RssService rssService;
	
	@Autowired
	private ItemRepository itemRepository;
	
	public void saveItems(Blog blog) {
		try {
	    List<Item> items = rssService.getItems(blog.getUrl());
	    for (Item item : items) {
	    	Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
	    	if(savedItem == null) {
		    	item.setBlog(blog);
		    	itemRepository.save(item);
	    	}
	    }
    } catch (RssException e) {
	    e.printStackTrace();
    }
	}

	// 1 hour = 60 secs * 60 mins * 1000
	@Scheduled(fixedDelay=3600000)
	public void reloadBlogs() {
		List<Blog> blogs = blogRepository.findAll();
		for (Blog blog : blogs) {
			saveItems(blog);
		}
	}
	
	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
	  blogRepository.delete(blog);
  }


	public Blog findOne(int id) {
	  return blogRepository.findOne(id);
  }

}
