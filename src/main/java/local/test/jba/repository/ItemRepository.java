package local.test.jba.repository;

import java.util.List;

import local.test.jba.entity.Blog;
import local.test.jba.entity.Item;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer>{
	List<Item> findByBlog(Blog blog, Pageable pageable);
	
	Item findByBlogAndLink(Blog blog, String link);
}
