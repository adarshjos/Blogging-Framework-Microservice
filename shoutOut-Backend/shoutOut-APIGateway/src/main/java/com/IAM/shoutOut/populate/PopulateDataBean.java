package com.IAM.shoutOut.populate;

import com.IAM.shoutOut.populate.repository.VerificationTokenRepository;
import com.IAM.shoutOut.populate.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PopulateDataBean {

    private UserInfoRepository userInfoRepo;
    private VerificationTokenRepository verificationTokenRepo;

    public PopulateDataBean(UserInfoRepository userInfoRepo, VerificationTokenRepository verificationTokenRepo) {
        super();
        this.userInfoRepo = userInfoRepo;
        this.verificationTokenRepo = verificationTokenRepo;
    }

    public UserInfoRepository getUserInfoRepo() {
        return userInfoRepo;
    }

    public void setUserInfoRepo(UserInfoRepository userInfoRepo) {
        this.userInfoRepo = userInfoRepo;
    }

    public VerificationTokenRepository getVerificationTokenRepo() {
        return verificationTokenRepo;
    }

    public void setVerificationTokenRepo(VerificationTokenRepository verificationTokenRepo) {
        this.verificationTokenRepo = verificationTokenRepo;
    }
}
