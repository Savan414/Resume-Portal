package com.savan.resumeportal;

import  com.savan.resumeportal.nodels.User;
import org.springfranework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
        Optional<User> findByuserName(String userNane);

}