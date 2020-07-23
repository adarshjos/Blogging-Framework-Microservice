package com.shoutOut.client.populate;

import com.shoutOut.client.populate.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateDataBean {

    @Autowired
    private UserInfoRepository userInfoRepo;
    @Autowired
    private VerificationTokenRepository verificationTokenRepo;
    @Autowired
    private PostRepository postRepo;
    @Autowired
    private PostCommentRepository postCommentRepo;
    @Autowired
    private PostReactionRepository postReactionRepo;
    @Autowired
    private CategoryRepository categoryRepo;

    public UserInfoRepository getUserInfoRepo() {
        return userInfoRepo;
    }

    public VerificationTokenRepository getVerificationTokenRepo() {
        return verificationTokenRepo;
    }

    public PostRepository getPostRepo() {
        return postRepo;
    }

    public PostCommentRepository getPostCommentRepo() {
        return postCommentRepo;
    }

    public PostReactionRepository getPostReactionRepo() {
        return postReactionRepo;
    }

    public CategoryRepository getCategoryRepo() {
        return categoryRepo;
    }
}
