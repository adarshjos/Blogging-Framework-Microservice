package com.shoutOut.client.populate.repository;


import com.shoutOut.client.model.Entities.PostReaction;
import com.shoutOut.client.model.Entities.PostReactionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostReactionRepository extends JpaRepository<PostReaction, PostReactionID> {

}
