package ea.blog.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class User {
	@Id
	@GeneratedValue
	private int id;
	@Pattern(regexp="[A-Za-z]*", message="First Name can only contain letters")
	private String firstname;
	@Pattern(regexp="[A-Za-z]*", message="Last Name can only contain letters")
	private String lastname;
	@Column(nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String sex;
	private String role;
	@Column(unique = true, nullable = false, length = 50)
	@NotBlank
	@Size.List({ @Size(min = 3, message = "Username too short"), @Size(max = 25, message = "Username too long") })
	private String username;
	@Column(nullable = false, length = 80)
    @Size.List({
            @Size(min = 6, message = "Password too short"),
            @Size(max = 80, message = "Password too long")
    })
    @NotBlank
	private String password;
	private boolean enabled;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Embedded
	private Address address;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> posts;
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "owner", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments;
	@ManyToMany(mappedBy = "likes", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Post> likedPosts;

	@ManyToMany(mappedBy = "likes")
	@JsonIgnore
	private List<Comment> likedComments;

	@ManyToMany
	private List<User> friends;

	public User() {
	}

	public User(String firstname, String lastname, Date dob, String sex, String username, String password,
			Address address, List<Post> posts, List<Comment> comments, List<Post> likedPosts,
			List<Comment> likedComments) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
		this.sex = sex;
		this.username = username;
		this.password = password;
		this.address = address;
		this.posts = posts;
		this.comments = comments;
		this.likedPosts = likedPosts;
		this.likedComments = likedComments;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	/*
	 * public void setId(int id) { this.id = id; }
	 */

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Post> getLikedPosts() {
		return likedPosts;
	}

	public void setLikedPosts(List<Post> likedPosts) {
		this.likedPosts = likedPosts;
	}

	public List<Comment> getLikedComments() {
		return likedComments;
	}

	public void setLikedComments(List<Comment> likedComments) {
		this.likedComments = likedComments;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

}
