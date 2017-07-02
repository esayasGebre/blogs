package ea.blog.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ea.blog.model.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer>{
	
}
