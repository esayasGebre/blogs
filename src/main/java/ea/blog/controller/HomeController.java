package ea.blog.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import ea.blog.model.Post;
import ea.blog.model.PostProxy;
import ea.blog.model.User;
import ea.blog.service.PostService;
import ea.blog.service.UserService;

@Controller
@SessionAttributes("currentUser")
public class HomeController {

	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;
	 
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String addPost(ModelMap model,Principal principal){
		//System.out.println("uuuuuuu principallllll"+ principal.getName());
		model.addAttribute("posts",this.postService.getAllPost());
		String username= principal.getName();
		model.addAttribute("currentUser", userService.findByUsername(username) );
		
		List<Post> posts= (List<Post>) this.postService.getAllPost();
		List<PostProxy> poxyposts = new ArrayList<PostProxy>();
		
		for(Post post1: posts)
		{
			boolean 	status ;
			User u1=(User) model.get("currentUser");
			status=post1.getLikes().contains( userService.getUser(u1.getId()));
			System.out.println("status" + status);
			System.out.println("status" + status + u1.getId());
			poxyposts.add(new PostProxy(post1, status));
			
			for(PostProxy p: poxyposts)
			{
				System.out.println(p.getStatus());
			}
			
		}
		model.addAttribute("posts",poxyposts);
		return "home";
	}
	
	
	@RequestMapping(value="/likePost", method=RequestMethod.POST)
	public String likePost(int userid, int postid, Model model){
		
		User user= userService.getUser(userid);
		Post  post = postService.getPost(postid);
		post.getLikes().add(user);
		postService.updatePost(post);
		model.addAttribute("likepost",postService.doUserLike(userid, postid));
		return "redirect:/home";
	}
	
	
	@RequestMapping(value="/unLikePost", method=RequestMethod.POST)
	public String unLikePost(int userid, int postid, Model model){
		
		User user= userService.getUser(userid);
		Post  post = postService.getPost(postid);
		post.getLikes().remove(user);
		postService.updatePost(post);
		//model.addAttribute("likepost",postService.doUserLike(userid, postid));
		return "redirect:/home";
	}

	
	@RequestMapping(value="/deletePost", method=RequestMethod.POST)
	public String deletePost(int postid, Model model){
		postService.deletePostById(postid);
		//postService.deletePost(postService.getPost(postid));
		return "redirect:/home";
	}

/*
	@RequestMapping(value="/updatePost", method=RequestMethod.POST)
	public String updatePost(int postid, String content, Model model){
		
		Post post=postService.getPost(postid);
		post.setContent(content);
		postService.updatePost(post);

		return "redirect:/home";
	}
	
*/
	//through my rest web servie
	@RequestMapping(value="/updatePost", method=RequestMethod.POST)
	public String updatePost(int postid, String content, Model model){

		RestTemplate restTemplate = new RestTemplate();
		Post post = restTemplate.getForObject("http://localhost:8080/rest/post/get/"+postid, Post.class);

		//Post post=postService.getPost(postid);
		post.setContent(content);
		postService.updatePost(post);
		return "redirect:/home";
	}

}
