package com.IAM.shoutOut.model.Entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PostReactionID implements Serializable {
    @Column(name = "userID")
    private Long userID;

    @Column(name = "postID")
    private Long postID;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    //TODO implement hascode and equals
}
