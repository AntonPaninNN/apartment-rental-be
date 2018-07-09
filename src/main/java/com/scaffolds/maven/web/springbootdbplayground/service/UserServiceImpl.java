/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scaffolds.maven.web.springbootdbplayground.service;

import com.scaffolds.maven.web.springbootdbplayground.model.Role;
import com.scaffolds.maven.web.springbootdbplayground.model.User;
import com.scaffolds.maven.web.springbootdbplayground.repository.UserRepository;
import com.scaffolds.maven.web.springbootdbplayground.viewmodel.UserVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService  {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
         AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();
        if (user != null) {
            detailsChecker.check(user);
            return user;
        } else 
            throw new UsernameNotFoundException("user not found.");
    }

    @Override
    public User update(User user, UserVM userVM) {
        user.setEmail(userVM.getEmail());
        user.setPassword(userVM.getEncodedPassword());
        user.setUsername(userVM.getUsername());
        user.setRole(Role.valueOf(userVM.getRole()));
        return userRepository.save(user);
    }

    @Override
    public User findUser(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User createUser(UserVM userVM) {
        User user = toUser(userVM);
        return userRepository.save(user);
    }

    private User toUser(UserVM userVM) {
        User user = new User();
        user.setEmail(userVM.getEmail());
        user.setRole(Role.valueOf(userVM.getRole()));
        user.setPassword(userVM.getEncodedPassword());
        user.setUsername(userVM.getUsername());
        return user;
    }

}