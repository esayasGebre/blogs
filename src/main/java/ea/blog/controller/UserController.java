package ea.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import ea.blog.model.User;
import ea.blog.service.UserService;

@Controller
@SessionAttributes(value = { "currentUser", "user" })
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/login")
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("currentUser", new User());
		return mav;
	}

	/*
	 * @GetMapping(value = "/login") public String showLogin() {
	 * 
	 * return "login"; }
	 */
/*
	@PostMapping(value = "/login")
	public String login(String username, String password,  ModelMap model) {

		boolean valid = userService.validateUser(username, password);
		if (valid) {
			
			model.addAttribute("currentUser", userService.findByUsername(username));
			
			System.out.println(((User)model.get("currentUser")).getFirstname());
			return "redirect:/home";
		} else {
			model.addAttribute("message", "Username or Password is wrong!!");
			return "login";
		}

	}*/
	@PostMapping(value = "/login")
	public String login(String username, String password, ModelMap model) {

		boolean valid = userService.validateUser(username, password);
		if (valid) {
			model.addAttribute("currentUser", userService.findByUsername(username));
			//User us=(User) model.get("currentUser");
			return "redirect:/home";
		} else {
			model.addAttribute("message", "Username or Password is wrong!!");
			return "login";
		}

	}

	/*
	 * @PostMapping(value = "/login") public String
	 * login(@ModelAttribute("login") User login, BindingResult result, Model
	 * model) { if (result.hasErrors()) { model.addAttribute("message",
	 * "Username or Password is wrong!!"); return "login"; } String username =
	 * login.getUsername(); String password = login.getPassword(); boolean valid
	 * = userService.validateUser(username, password); if (valid) {
	 * model.addAttribute("login", userService.findByUsername(username)); return
	 * "redirect:home"; }
	 * 
	 * return "login"; }
	 */

	/*@GetMapping(value = "/register")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("registration");
		User user = (User) model.get("currentUser");
		mav.addObject("user", user);
		return mav;
	}*/

	@GetMapping(value = "/register")
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("registration");
		User user = (User) model.get("currentUser");
		mav.addObject("currentUser", user);
		return mav;
	}
/*	@PostMapping(value = "/register")
	public String register(@ModelAttribute(value = "user") User user, BindingResult result, Model model) {

		if (result.hasErrors()) {

			model.addAttribute("regmessage", "There is an error on registration." + result.getAllErrors());
			return "registration";
		} else {
			if (userService.usernameExists(user.getUsername())
					|| userService.emailExists(user.getAddress().getEmail())) {
				model.addAttribute("regmessage", "User name or email already exists.");
				return "registration";
			}
			
			Date timestamp = new Timestamp(user.getDob().getTime());
			user.setDob(timestamp);
			user.setEnabled(true);
			user.setRole("user");
			User addUser=userService.addUser(user);
			model.addAttribute("currentUser", addUser);

			return "redirect:home";
		}
	}*/
	@PostMapping(value = "/register")
	public String register(@ModelAttribute(value = "currentUser") @Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {

			model.addAttribute("regmessage", "There is an error on registration.");
			return "login";
		} else {
			if (userService.usernameExists(user.getUsername())
					|| userService.emailExists(user.getAddress().getEmail())) {
				model.addAttribute("regmessage", "User name or email already exists.");
				return "login";
			}

			/*Date timestamp = new Timestamp(user.getDob().getTime());
			user.setDob(timestamp);*/
			user.setEnabled(true);
			user.setRole("user");
			User addUser = userService.addUser(user);
			model.addAttribute("currentUser", addUser);

			return "redirect:home";
		}
	}


	@GetMapping(value = "/edit_profile")
	public ModelAndView showRegister(ModelMap model) {
		ModelAndView mav = new ModelAndView("registration");
		User user = (User) model.get("currentUser");
		mav.addObject("user", user);
		return mav;
	}

	@PostMapping(value = "/edit_profile")
	public String editProfile(@ModelAttribute(value = "user") @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("editmessage", "There is an error on editing.");
			return "registration";
		}
		userService.changeProfileInfo(user);
		model.addAttribute("success", true);

		return "redirect:home";
	}

	@GetMapping(value = "/settings")
	public ModelAndView showSettings(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("settings");
		mav.addObject("modifiedUser", new User());
		return mav;
	}

	@GetMapping(value = "/profile/{userId}")
	public String getUser(@PathVariable int userId, Model model) {
		model.addAttribute("searchUser", userService.getUser(userId));
		return "profile";

	}

	
	@GetMapping(value = "/profile/un/{username}")
	public String getUserByProfile(@PathVariable String username, Model model) {
		//model.addAttribute("searchUser", userService.getUser(username));
		
		RestTemplate restTemplate = new RestTemplate();
		User user = restTemplate.getForObject("http://localhost:8080/rest/user/username/"+username, User.class);
		
		model.addAttribute("searchUser", user);
		return "profile";

	}
	
	@GetMapping(value = "/users")
	public String getAllUsers(Model model) {
		model.addAttribute("allUsers", userService.getAllUsers());
		return "registration";
	}

	@GetMapping(value = "/check_email")
	public @ResponseBody String checkEmail(@RequestParam("email") String email) {
		return userService.emailExists(email) ? "false" : "true";
	}

	@GetMapping(value = "/check_username")
	public @ResponseBody String checkUsername(@RequestParam("username") String username) {
		return userService.usernameExists(username) ? "false" : "true";
	}

	@PostMapping(value = "/change_username")
	public String changeEmail(String username, String password, ModelMap model) {
		model.addAttribute("isEmailForm", true);
		User user = (User) model.get("currentUser");
		if (userService.usernameExists(username)) {
			model.addAttribute("message", "User name  already exists.");
			return "settings";
		}
		if (user != null) {
			userService.changeUsername(user, username, password);
			return "redirect:/home";
		}

		return "settings";
	}

	@PostMapping(value = "/change_password")
	public String changePassword(String currentpassword, String repeatPassword, String newPassword, ModelMap model) {

		model.addAttribute("isPasswordForm", true);
		User user = (User) model.get("currentUser");
		if (!repeatPassword.equals(newPassword)) {
			model.addAttribute("message", "Two passwords do not match!");
			return "settings";
		}
		if (user != null) {
			if (user.getPassword().equals(currentpassword)) {
				userService.changePassword(user, newPassword);
				return "redirect:/home";
			} else {
				model.addAttribute("message", "Incorrect password!");
				return "settings";
			}
		}

		return "settings";
	}

/*	@PostMapping(value = "/edit_profile")
	public String editProfile(@ModelAttribute(value = "user") User user, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			model.addAttribute("editmessage", "There is an error on editing.");
			return "registration";
		}
		userService.changeProfileInfo(user);
		model.addAttribute("success", true);

		return "redirect:home";
	}*/

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));

	}

}
