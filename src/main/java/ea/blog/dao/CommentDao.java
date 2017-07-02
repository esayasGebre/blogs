package ea.blog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ea.blog.model.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer>{

	List<Comment> findByPostId(int id); 
}
