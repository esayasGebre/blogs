package ea.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ea.blog.model.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findTop1ByUsernameIgnoreCase(String username);

	User findByFirstnameIgnoreCase(String username);
	
	User findByLastnameIgnoreCase(String username);
	
    User findTop1ByAddress_EmailIgnoreCase(String email);

    User findByUsernameOrAddress_Email(String username, String email);
    
}
