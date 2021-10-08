package com.forum.portal.service;

import com.forum.portal.entity.User;

public interface UserService {
    User getUserByApi(String token);
}
