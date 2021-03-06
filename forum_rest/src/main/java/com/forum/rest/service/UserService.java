package com.forum.rest.service;

import com.forum.common.base.BaseService;
import com.forum.common.entity.User;

public interface UserService extends BaseService<User> {

    /**
     *验证用户名是否可以使用
     * @param username
     * @return
     */
    boolean checkUserName(String username);

    boolean checkUserEmail(String email);

    User findByEmail(String email);

    /**
     * 注册用户
     * @param email
     * @param username
     * @param password
     */
    void createUser(String email, String username, String password);


    /**
     * 用户登录
     * @param user
     * @return
     */
    String LoginUser(User user);

    /**
     * 通过Token获取用户信息
     * @param token
     * @return
     */
    User getUserByToken(String token);

    /**
     * 根据Token登出用户
     * @param token
     */
    void LogoutUser(String token);

    /**
     * 修改用户信息
     * @param token
     * @param username
     * @param signature
     * @param sex
     */
    void updateUser(String token, String username, String signature, Integer sex);


    /**
     * 修改用户的头像
     * @param token
     * @param icon
     */
    void updataUserIcon(String token,String icon);

    /**
     * 修改用户的密码
     * @param token
     * @param oldpsd
     * @param newpsd
     */
    void updateUserPassword(String token,String oldpsd,String newpsd);
}
