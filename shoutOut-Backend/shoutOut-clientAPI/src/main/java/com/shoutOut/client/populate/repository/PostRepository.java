package com.shoutOut.client.populate.repository;

import com.shoutOut.client.model.Entities.Category;
import com.shoutOut.client.model.Entities.Post;

import com.shoutOut.client.model.Entities.Tag;
import com.shoutOut.client.model.Entities.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {


    Page<Post> findAllByCategoriesOrderByPublishedDateDesc(Category category, Pageable pageable);
    Page<Post> findAllByAuthorOrderByPublishedDateDesc(UserInfo author, Pageable pageable);
    Page<Post> findAllByTagsOrderByPublishedDateDesc(Tag tag, Pageable pageable);

}
