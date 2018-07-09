package com.scaffolds.maven.web.springbootdbplayground.service;

import com.scaffolds.maven.web.springbootdbplayground.model.User;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.UserVM;

import java.util.Optional;

public interface UserService extends org.springframework.security.core.userdetails.UserDetailsService {

    User update(User user, UserVM params);
    User findUser(Long id);
    User createUser(UserVM userVM);

}