package com.shoutOut.client.populate.repository;

import com.shoutOut.client.model.Entities.Post;
import com.shoutOut.client.model.Entities.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment,Long> {
    List<PostComment> findByPost(Post post);
    
}
