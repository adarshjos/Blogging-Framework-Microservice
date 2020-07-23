package com.IAM.shoutOut.authorization.authService.commands;


import com.IAM.shoutOut.authorization.model.DTO.UserDTO;
import com.IAM.shoutOut.model.Entities.UserInfo;
import com.IAM.shoutOut.model.Entities.VerificationToken;
import com.IAM.shoutOut.context.WorkSpaceContext;
import org.apache.commons.chain.Context;
import org.apache.commons.chain.Filter;
import org.springframework.transaction.annotation.Transactional;


public class PopulateDB implements Filter {
    @Transactional
    @Override
    public boolean execute(Context context) throws Exception {
        WorkSpaceContext mainContext = (WorkSpaceContext)context;
        UserInfo user=new UserInfo();
        UserDTO.SignUpRequest signUpDO=mainContext.getSignUpRequestDTO();
        user.setEmail(signUpDO.getEmail());
        user.setPassword(encodePassword(signUpDO.getPassword()));
        user.setEnabled(false);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(mainContext.getAuthToken());

        mainContext.populateDataBean.getUserInfoRepo().save(user);
        mainContext.populateDataBean.getVerificationTokenRepo().save(verificationToken);

        return false;
    }

    private String encodePassword(String password){
        //return  passwordEncoder.encode(password);
        return  password;
    }


    @Override
    public boolean postprocess(Context context, Exception e) {
        return false;
    }
}
