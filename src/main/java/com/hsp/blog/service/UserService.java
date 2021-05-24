package com.hsp.blog.service;

import com.hsp.blog.po.User;


public interface UserService {
    User checkUser(String username, String password);
}