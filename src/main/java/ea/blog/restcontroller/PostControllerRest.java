package ea.blog.restcontroller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ea.blog.model.Post;
import ea.blog.service.PostService;

@RestController
@RequestMapping(value="/rest/post/")
public class PostControllerRest {
	
	@Autowired
	private PostService postService;
	
	@PostMapping("/add")
	public void addPost(@RequestBody Post post){
		this.postService.addPost(post);
	}
	//I used for my application by implementing Rest
	@GetMapping("/get/{id}")
	public Post getPost(@PathVariable("id") int id){
		return this.postService.getPost(id);
	}
	
	@GetMapping("/getposts")
	public Collection<Post> getAllPost(){
		return this.postService.getAllPost();
	}
	
   @PutMapping("/update")
    public void updateStudent(@RequestBody Post post){
        this.postService.addPost(post);
    }
   
   @DeleteMapping("/remove/{id}")
   public void removeStudent(@PathVariable("id") int id) {
       this.postService.removePostById(id);
   }
 
}
