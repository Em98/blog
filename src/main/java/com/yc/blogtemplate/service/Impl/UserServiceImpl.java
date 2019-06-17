package com.yc.blogtemplate.service.Impl;


import com.yc.blogtemplate.domain.User;
import com.yc.blogtemplate.repository.UserRepository;
import com.yc.blogtemplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }
}
