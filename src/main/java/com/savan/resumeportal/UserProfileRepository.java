package com.savan.resumeportal;

import com.savan.resumeportal.models.User;
import com.savan.resumeportal.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
        Optional<UserProfile> findByUserName(String userName);

}