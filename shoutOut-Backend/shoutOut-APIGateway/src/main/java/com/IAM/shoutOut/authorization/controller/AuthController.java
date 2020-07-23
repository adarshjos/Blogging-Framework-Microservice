package com.IAM.shoutOut.authorization.controller;

import com.IAM.shoutOut.authorization.authService.implementations.ShoutOutSignUp;
import com.IAM.shoutOut.authorization.model.DTO.AuthenticationResponse;
import com.IAM.shoutOut.authorization.model.DTO.LoginRequest;
import com.IAM.shoutOut.authorization.model.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private ShoutOutSignUp signUpObj;

    @Autowired
    public AuthController(ShoutOutSignUp signUpObj) {
        super();
        this.signUpObj=signUpObj;
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody UserDTO.SignUpRequest signUpRequest) throws Exception {
        signUpObj.doSignUp(signUpRequest);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/accountVerification/{token}")
    public ResponseEntity verifyAccount(@PathVariable String token )throws Exception{
        signUpObj.verifyAccount(token);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) throws Exception {
        return signUpObj.loginAccount(loginRequest);

    }


}
