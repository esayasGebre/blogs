package ea.blog.restcontroller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ea.blog.model.User;
import ea.blog.service.UserService;

@RestController
@RequestMapping(value = "/rest")
public class UserControllerRest {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new User());
		return mav;
	}

/*	@PostMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") User login) {
		ModelAndView mav = null;
		User user = userService.validateUser(login);
		if (null != user) {
			mav = new ModelAndView("home");
			mav.addObject("firstname", user.getFirstname());
		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong!!");
		}
		return mav;
	}*/

	@GetMapping(value = "/register")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("registration");
		mav.addObject("user", new User());
		return mav;
	}

	@GetMapping(value = "/user/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.getUser(userId);
	}
	
	@GetMapping(value = "/user/username/{uname}")
	public User getUserByUsername(@PathVariable String uname) {
		return userService.findByUsername(uname);
	}

	@GetMapping(value = "/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();

	}

	@PostMapping("/register")
	public User registerUser(@ModelAttribute(value = "user") User user, BindingResult result) {
		//user.setUsername(StringUtils.trimWhitespace(user.getUsername()));
		//user.getAddress().setEmail(StringUtils.trimWhitespace(user.getAddress().getEmail()));
		if (result.hasErrors()) {
			return user;
		}
		userService.addUser(user);
		return user;
	}

	@PostMapping(value = "/edit_profile")
	public User editProfile(@ModelAttribute(value = "user") User user, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			// User currentUser = userService.currentUser();
			return user;
		}
		userService.changeProfileInfo(user);
		redirectAttributes.addFlashAttribute("success", true);
		return user;
	}
}
