package ea.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ea.blog.dao.UserDao;
import ea.blog.model.User;

@Service
@Transactional()
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	public User getUser(int id) {
		return userDao.findOne(id);
	}

	public User addUser(User user) {
		return userDao.save(user);
	}

	public User updateUser(User user) {
		return userDao.save(user);
	}

	public void deleteUser(int id) {
		userDao.delete(id);
	}

	public void deleteAllUsers() {
		userDao.deleteAll();
	}

	public User findByEmail(String email) {
		return userDao.findTop1ByAddress_EmailIgnoreCase(email);
	}

	public User findByUsername(String username) {
		return userDao.findTop1ByUsernameIgnoreCase(username);
	}

	public boolean usernameExists(String username) {
		return findByUsername(username) != null;
	}

	public boolean emailExists(String email) {
		return findByEmail(email) != null;
	}

	public void changeUsername(User user, String username, String currentPassword) {
		if (user.getPassword().equals(currentPassword)) {
			user.setUsername(username);
			userDao.saveAndFlush(user);
		}
	}

	public User currentUser() {
		// TODO Auto-generated method stub
		return null;
	}

	public void changeProfileInfo(User user) {
		updateUser(user);
	}

	public boolean validateUser(String username, String password) {
		// TODO Auto-generated method stub
		User user = userDao.findTop1ByUsernameIgnoreCase(username);
		if (user == null) {
			return false;
		}
		return user.getPassword().equals(password);
	}

	public void changePassword(User user, String password) {
		user.setPassword(password);
		userDao.saveAndFlush(user);
	}
}
