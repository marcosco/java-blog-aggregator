package local.test.jba.repository;

import java.util.List;

import local.test.jba.entity.Blog;
import local.test.jba.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	List<Blog> findByUser(User user);
}
