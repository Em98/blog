package com.yc.blogtemplate.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;


@Entity
public class User implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String userDisplayName;

    @Email(message = "邮箱格式错误。")
    private String email;

    private String avatar;

    private Boolean loginEnable = false;

    private Date lastLogin;

    public User() {
    }

    public User(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public User(String username, String password, String userDisplayName, @Email(message = "邮箱格式错误。") String email) {
        this.username = username;
        this.password = password;
        this.userDisplayName = userDisplayName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserDisplayName() {
        return userDisplayName;
    }

    public void setUserDisplayName(String userDisplayName) {
        this.userDisplayName = userDisplayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getLoginEnable() {
        return loginEnable;
    }

    public void setLoginEnable(Boolean loginEnable) {
        this.loginEnable = loginEnable;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}
