package com.IAM.shoutOut.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category extends BaseEntity {

    @Column(name = "categoryName")
    private String categoryName;

    @ManyToMany(mappedBy = "categories")
    private Set<Post> posts;

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
