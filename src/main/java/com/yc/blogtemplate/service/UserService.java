package com.yc.blogtemplate.service;

import com.yc.blogtemplate.domain.User;

public interface UserService {
    /**
     * 保存用户
     * @param user
     */
    User saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    User updateUser(User user);


    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

}
