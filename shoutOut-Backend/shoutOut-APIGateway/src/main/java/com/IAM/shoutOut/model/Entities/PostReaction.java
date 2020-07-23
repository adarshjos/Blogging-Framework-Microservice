package com.IAM.shoutOut.model.Entities;


import com.IAM.shoutOut.util.DataModelConstants;

import javax.persistence.*;

@Entity
@Table(name = "postReaction")
public class PostReaction {
    @EmbeddedId
    private PostReactionID id;

    @ManyToOne
    @MapsId("userID")
    @JoinColumn(name = "userID")
    private UserInfo user;

    @ManyToOne
    @MapsId("postID")
    @JoinColumn(name = "postID")
    private Post post;

    @Enumerated(value = EnumType.STRING)
    @Column(name="reaction")
    private DataModelConstants.POST_REACTION reaction;


    public PostReactionID getId() { return id; }

    public void setId(PostReactionID id) { this.id = id; }

    public UserInfo getUser() { return user; }

    public void setUser(UserInfo user) { this.user = user; }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }

    public DataModelConstants.POST_REACTION getReaction() { return reaction; }

    public void setReaction(DataModelConstants.POST_REACTION reaction) { this.reaction = reaction; }
}
