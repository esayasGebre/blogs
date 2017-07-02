package ea.blog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;


@Entity
public class Comment {
	@Id 
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@NotBlank
	private String content;

	@ManyToOne
	private User owner;
	@ManyToOne
	private Post post;
	@ManyToMany
	 
	private List<User> likes ;
	
	public Comment(){}
	
	

	public Comment(Date createdDate, Date updatedDate, String content, User owner, Post post, List<User> likes) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.content = content;
		this.owner = owner;
		this.post = post;
		this.likes = likes;
	}


	public Comment(Date createdDate, Date updatedDate, String content, User owner, Post post) {
		super();
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.content = content;
		this.owner = owner;
		this.post = post;
		
	}

	

	public int getId() {
		return id;
	}

/*	public void setId(int id) {
		this.id = id;
	}*/

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public List<User> getLikes() {
		return likes;
	}

	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
	
	
	
	
}
