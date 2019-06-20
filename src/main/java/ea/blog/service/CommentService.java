package ea.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import ea.blog.dao.CommentDao;
import ea.blog.model.Comment;
import ea.blog.model.User;

@Transactional
@Controller
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	public CommentService() {
		// TODO Auto-generated constructor stub
	}

	public Comment addComment(Comment comment) {
		return commentDao.save(comment);
	}

	public Comment getComment(int id) {
		return commentDao.findOne(id);
	}

	public void deleteComment(Comment comment) {
		commentDao.delete(comment);
	}

	public int numberOfLikes(int id) {
		return commentDao.findOne(id).getLikes().size();
	}

	public Comment updateComment(Comment comment) {
		return commentDao.save(comment);
	}

	public void deleteCommentById(int id) {
		commentDao.delete(id);
	}

	public List<Comment> getCommentForPost(int id) {
		return commentDao.findByPostId(id);
	}

	public boolean statusLikeComment(User user, Comment comment) {
		return comment.getLikes().contains(user);
	}

	public Comment likeComment(User user, Comment comment) {
		comment.getLikes().add(user);
		return commentDao.save(comment);
	}

	public Comment unLikeComment(User user, Comment comment) {
		comment.getLikes().remove(user);
		return commentDao.save(comment);
	}
}
