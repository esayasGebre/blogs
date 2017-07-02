package ea.blog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id @GeneratedValue
	private int id;
	@NotBlank
	private String title;
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	@NotBlank
	private String content;

	@ManyToOne
	private User owner;
	@OneToMany(mappedBy="post", cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments;
	@ManyToMany
	private List<User> likes ;

	public Post(){}

	public Post(String title, Date createdDate, Date updatedDate, String content, User owner, List<Comment> comments,
			List<User> likes) {
		super();
		this.title = title;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.content = content;
		this.owner = owner;
		this.comments = comments;
		this.likes = likes;
	}
	
	public Post(String title, Date createdDate, Date updatedDate, String content, User owner) {
		super();
		this.title = title;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.content = content;
		this.owner = owner;
		}

	public int getId() {
		return id;
	}

/*	public void setId(int id) {
		this.id = id;
	}*/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<User> getLikes() {
		return likes;
	}

	public int likesize()
	{
		return likes.size();
	}
	
	
	
	public void setLikes(List<User> likes) {
		this.likes = likes;
	}
	
	
}