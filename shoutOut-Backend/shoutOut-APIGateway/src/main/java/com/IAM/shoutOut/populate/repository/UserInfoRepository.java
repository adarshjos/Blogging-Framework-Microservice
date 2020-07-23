package com.IAM.shoutOut.populate.repository;


import com.IAM.shoutOut.model.Entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo>findByEmail(String email);
    boolean existsByEmail(String email);

}
