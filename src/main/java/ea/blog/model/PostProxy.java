package ea.blog.model;


public class PostProxy {
	private Post post;
	private boolean status;
	
	public PostProxy(Post post, boolean status)
	{
	 this.post=post;
	 this.status=status;
	 
	}
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
}