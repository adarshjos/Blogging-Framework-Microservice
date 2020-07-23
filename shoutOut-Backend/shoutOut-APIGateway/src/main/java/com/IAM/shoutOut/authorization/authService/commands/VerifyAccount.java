package com.IAM.shoutOut.authorization.authService.commands;


import com.IAM.shoutOut.exceptions.shoutOutExceptions;
import com.IAM.shoutOut.model.Entities.UserInfo;
import com.IAM.shoutOut.model.Entities.VerificationToken;
import com.IAM.shoutOut.context.WorkSpaceContext;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class VerifyAccount implements Filter {


    @Override
    public boolean execute(Context context) throws Exception {
        WorkSpaceContext mainContext=(WorkSpaceContext)context;
        Optional<VerificationToken> verificationTokenOptional=mainContext.populateDataBean.
                getVerificationTokenRepo().
                findByToken(mainContext.getAuthToken());
        verificationTokenOptional.orElseThrow(()->new shoutOutExceptions("Invalid Token"));
        fetchUserAndEnable(verificationTokenOptional.get(),mainContext);
        return false;
    }

    @Transactional
    protected void fetchUserAndEnable(VerificationToken verificationTokenOptional, WorkSpaceContext mainContext) {
        String userEmail=verificationTokenOptional.getUser().getEmail();
        Optional<UserInfo> userInfoOptional=mainContext.populateDataBean.getUserInfoRepo().findByEmail(userEmail);
        userInfoOptional.orElseThrow(()->new shoutOutExceptions("No user Found::Email:::"+userEmail));
        UserInfo userInfo=userInfoOptional.get();
        userInfo.setEnabled(true);
        mainContext.populateDataBean.getUserInfoRepo().save(userInfo);
    }

    @Override
    public boolean postprocess(Context context, Exception e) {
        return false;
    }
}
