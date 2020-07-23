package com.IAM.shoutOut.model.Entities;

import com.IAM.shoutOut.util.DataModelConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post extends BaseEntity{

    @Column(name="title",nullable = false)
    @NotEmpty
    private String title;

    @Column(name="summary",nullable=false)
    @NotEmpty
    private String summary;

    @Column(name="slug",nullable = false)
    private String slug;

    @Lob
    @Column(name="content")
    private String content;

    @Enumerated(value = EnumType.STRING)
    @Column(name="publishStatus")
    private DataModelConstants.PUBLISH_STATUS status;

    @Column(name="votes")
    private Integer votes;

    @ManyToOne
    @JoinColumn(name = "authorID")
    private UserInfo author;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private Set<PostComment> postComments;

    @ManyToMany
    @JoinTable(name = "postCategory",joinColumns = @JoinColumn(name = "postID"),inverseJoinColumns = @JoinColumn(name = "categoryID"))
    private Set<Category> categories;

    @ManyToMany
    @JoinTable(name = "postTag",joinColumns = @JoinColumn(name="postID"),inverseJoinColumns = @JoinColumn(name="tagID"))
    private Set<Tag> tags;


    @CreatedDate
    @Column(name="publishedDate",updatable = false)
    private LocalDateTime publishedDate;

    @LastModifiedDate
    @Column(name="updateDate")
    private LocalDateTime updateDate;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    Set<PostReaction> postReactions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DataModelConstants.PUBLISH_STATUS getStatus() {
        return status;
    }

    public void setStatus(DataModelConstants.PUBLISH_STATUS status) {
        this.status = status;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public UserInfo getAuthor() {
        return author;
    }

    public void setAuthor(UserInfo author) {
        this.author = author;
    }

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public LocalDateTime getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDateTime publishedDate) {
        this.publishedDate = publishedDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public Set<PostReaction> getPostReactions() {
        return postReactions;
    }

    public void setPostReactions(Set<PostReaction> postReactions) {
        this.postReactions = postReactions;
    }

}
