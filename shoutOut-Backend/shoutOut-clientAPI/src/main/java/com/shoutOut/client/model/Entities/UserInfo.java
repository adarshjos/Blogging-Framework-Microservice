package com.shoutOut.client.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "userInfo")
public class UserInfo extends BaseEntity {

//    @Column(name="firstName")
//    @NotEmpty
//    @Pattern(regexp = "[a-z-A-Z]*", message = "First Name has invalid characters")
//    private String firstName;

    @Column(name="middleName")
    @Pattern(regexp = "[a-z-A-Z]*", message = "Middle Name has invalid characters")
    private String middleName;

//    @Column(name="lastName")
//    @NotEmpty
//    @Pattern(regexp = "[a-z-A-Z]*", message = "Last Name has invalid characters")
//    private String LastName;


//    @Column(name="mobileNumber",unique = true,nullable = false)
//    @NotEmpty
//    @Digits(fraction = 0,integer = 10)
//    private String mobNumber;

    @Email
    @Column(name="email",nullable = false,unique = true)
    private String email;

    @JsonIgnore
    @Column(name="password",nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "author")
    private Set<Post> posts;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "commentor")
    private Set<PostComment> postComments;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private Set<PostReaction>postReactions;

    public UserInfo(String email,String password){
        this.email=email;
        this.password=password;
    }

    @Lob
    @Column(name="profileImage")
    private Byte[] profilePicture;

    public UserInfo() {

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(name="enabled")
    private boolean enabled;

    @CreatedDate
    @Column(name="createdDate")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name="modifiedDate")
    private LocalDateTime modifiedDate;

//    public String getFirstName() {
//        return firstName;
//    }
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
//    public String getLastName() {
//        return LastName;
//    }
//    public void setLastName(String lastName) {
//        LastName = lastName;
//    }
//    public String getMobNumber() {
//        return mobNumber;
//    }
//    public void setMobNumber(String mobNumber) {
//        this.mobNumber = mobNumber;
//    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public Byte[] getProfilePicture() { return profilePicture; }
    public void setProfilePicture(Byte[] profilePicture) { this.profilePicture = profilePicture; }
    public LocalDateTime getModifiedDate() { return modifiedDate; }
    public void setModifiedDate(LocalDateTime modifiedDate) { this.modifiedDate = modifiedDate; }

    public Set<Post> getPostsInternal() {
        if(this.posts==null&&this.posts.isEmpty()){
            this.posts=new HashSet<>();
        }
        return this.posts;
    }

    public void setPostsInternal(Set<Post> posts) {
        this.posts = posts;
    }

    //TODO enhance
    public Set<Post> getPosts(){return this.posts;}
    public void addPost(Post post){
        getPostsInternal().add(post);
        post.setAuthor(this);
    }

    public Set<PostReaction> getPostReactions() {
        return postReactions;
    }

    public void setPostReactions(Set<PostReaction> postReactions) {
        this.postReactions = postReactions;
    }
}
