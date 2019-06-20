package ea.blog.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ea.blog.dao.PostDao;
import ea.blog.dao.UserDao;
import ea.blog.model.Post;

@Transactional
@Service
public class PostService {

	@Autowired
	private PostDao postDao;

	@Autowired
	private UserDao usertDao;

	public void addPost(Post post) {
		this.postDao.save(post);
	}

	public Collection<Post> getAllPost() {
		return this.postDao.findAll();
	}

	public Post getPost(int postId) {
		return this.postDao.findOne(postId);
	}

	public void deletePost(Post post) {
		this.postDao.delete(post);
	}

	public void deletePostById(int postId) {
		this.postDao.delete(postId);
	}

	public void removePostById(int postId) {
		this.postDao.delete(postId);
	}

	public Post updatePost(Post post) {
		return this.postDao.save(post);
	}

	public boolean doUserLike(int userid, int postid) {
		return this.postDao.getOne(postid).getLikes().contains(this.usertDao.getOne(userid));
	}
}
