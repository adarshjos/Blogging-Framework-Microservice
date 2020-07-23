package com.shoutOut.client.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "tag")
public class Tag extends BaseEntity{

    @Column(name = "tagName")
    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts;

    public String getTagName() { return tagName; }

    public void setTagName(String tagName) { this.tagName = tagName; }

    public Set<Post> getPosts() { return posts; }

    public void setPosts(Set<Post> posts) { this.posts = posts; }
}
