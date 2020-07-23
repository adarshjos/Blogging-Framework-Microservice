package com.IAM.shoutOut.authorization.authService;


import com.IAM.shoutOut.authorization.model.UserDetailsImpl;
import com.IAM.shoutOut.model.Entities.UserInfo;
import com.IAM.shoutOut.populate.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserDetailsServiceImpl(UserInfoRepository userInfoRepository) {
        super();
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserInfo> userInfoOptional=userInfoRepository.findByEmail(email);
        userInfoOptional.orElseThrow(()->new UsernameNotFoundException("User not found::"+email));
        UserInfo userInfo=userInfoOptional.get();
        return new UserDetailsImpl(userInfo);
//        UserInfo userInfo=new UserInfo("adarsh87@gmail.com","pass");
////        return new UserDetailsImpl(userInfo);


    }
}
