package com.IAM.shoutOut.context;

import com.IAM.shoutOut.authorization.model.DTO.AuthenticationResponse;
import com.IAM.shoutOut.authorization.model.DTO.LoginRequest;
import com.IAM.shoutOut.authorization.model.DTO.UserDTO;
import com.IAM.shoutOut.populate.PopulateDataBean;
import org.apache.commons.chain.impl.ContextBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

@Component
public class WorkSpaceContext extends ContextBase {
    private String screenName;
    private UserDTO.SignUpRequest signUpRequestDTO;
    private AuthenticationResponse authenticationResponse;
    private LoginRequest loginDTO;
    private String authToken=null;
    private Action action =null;
    public PopulateDataBean populateDataBean= null;
    public enum Action{
        SIGNUP,
        VERIFYACCOUNT
    }
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender javaMailSender;

    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }

    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public AuthenticationResponse getAuthenticationResponse() {
        return authenticationResponse;
    }

    public void setAuthenticationResponse(AuthenticationResponse authenticationResponse) {
        this.authenticationResponse = authenticationResponse;
    }

    public LoginRequest getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginRequest loginDTO) {
        this.loginDTO = loginDTO;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public UserDTO.SignUpRequest getSignUpRequestDTO() {
        return signUpRequestDTO;
    }

    public void setSignUpRequestDTO(UserDTO.SignUpRequest signUpRequestDTO) {
        this.signUpRequestDTO = signUpRequestDTO;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
