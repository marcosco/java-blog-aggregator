package local.test.jba.repository;

import local.test.jba.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);

}
