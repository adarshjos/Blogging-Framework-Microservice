package com.shoutOut.client.model.Entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "postComment")
public class PostComment extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "postID")
    private Post post;

    @ManyToOne
    @JoinColumn(name="commentorID")
    private UserInfo commentor;

    @Column(name = "parentCommentID")
    private Long parentCommentID;

    @Lob
    @Column(name="content")
    private String content;

    @CreatedDate
    @Column(name="createdDate")
    private LocalDateTime createdDate;

    public PostComment(String content, UserInfo commentor) {
        this.content=content;
        this.commentor=commentor;
        createdDate=LocalDateTime.now();
    }

    public UserInfo getCommentor() {
        return commentor;
    }

    public void setCommentor(UserInfo commentor) {
        this.commentor = commentor;
    }

    public Long getParentCommentID() {
        return parentCommentID;
    }

    public void setParentCommentID(Long parentCommentID) {
        this.parentCommentID = parentCommentID;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Post getPost() { return post; }

    public void setPost(Post post) { this.post = post; }

    public String getContent() { return content; }

    public void setContent(String content) { this.content = content; }
}
