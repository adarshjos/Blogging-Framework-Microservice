package com.IAM.shoutOut.authorization.authService.implementations;


import com.IAM.shoutOut.authorization.authService.UserDetailsServiceImpl;
import com.IAM.shoutOut.authorization.authService.WorkSpaceProcessor;
import com.IAM.shoutOut.authorization.model.DTO.UserDTO;
import com.IAM.shoutOut.authorization.model.UserDetailsImpl;
import com.IAM.shoutOut.authorization.util.JWTProviderUtil;
import com.IAM.shoutOut.exceptions.shoutOutExceptions;
import com.IAM.shoutOut.authorization.model.DTO.AuthenticationResponse;
import com.IAM.shoutOut.authorization.model.DTO.LoginRequest;
import com.IAM.shoutOut.context.WorkSpaceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class ShoutOutSignUp {

    private final AuthenticationManager authenticationManager;
    private final JWTProviderUtil jwtProviderUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final WorkSpaceContext mainContext;

    @Autowired
    public ShoutOutSignUp(AuthenticationManager authenticationManager,JWTProviderUtil jwtProviderUtil,UserDetailsServiceImpl userDetailsService,WorkSpaceContext workSpaceContext) {
        super();
        this.authenticationManager=authenticationManager;
        this.jwtProviderUtil=jwtProviderUtil;
        this.userDetailsService=userDetailsService;
        this.mainContext=workSpaceContext;
    }

    public void doSignUp(UserDTO.SignUpRequest user)throws Exception {
        mainContext.setAction(WorkSpaceContext.Action.SIGNUP);
        mainContext.setScreenName("screeName");
        mainContext.setSignUpRequestDTO(user);
        WorkSpaceProcessor processor=new WorkSpaceProcessor(mainContext);
        processor.execute();
    }

    public void verifyAccount(String token) throws Exception{
        mainContext.setAction(WorkSpaceContext.Action.VERIFYACCOUNT);
        WorkSpaceProcessor processor=new WorkSpaceProcessor(mainContext);
        processor.execute();
    }

    public AuthenticationResponse loginAccount(LoginRequest loginDTO) throws Exception{

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword())
            );
            final UserDetails userDetails=userDetailsService.loadUserByUsername(loginDTO.getEmail());
            final String jwt =jwtProviderUtil.generateToken((UserDetailsImpl) userDetails);
            return new AuthenticationResponse(jwt,loginDTO.getEmail());
        }catch (BadCredentialsException e){
            throw new shoutOutExceptions("Incorrect Username/Password");
        }
    }
}
