package com.forum.rest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.forum.common.base.BaseServiceImpl;
import com.forum.common.dao.UserDao;
import com.forum.common.entity.User;
import com.forum.common.exception.ServiceProcessException;
import com.forum.rest.service.RedisService;
import com.forum.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private RedisService<Integer> redisSocketService;

    @Autowired
    private RedisService<User> redisService;

    @Value("${REDIS_USERID_KEY}")
    private String REDIS_USERID_KEY;

    @Value("${REDIS_USER_KEY}")
    private String REDIS_USER_KEY;

    @Value("${REDIS_USER_TIME}")
    private Integer REDIS_USER_TIME;


    @Override
    public boolean checkUserName(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        User user = repository.selectOne(queryWrapper);
        if (user == null) return true;
        return false;
    }

    @Override
    public boolean checkUserEmail(String email) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", email);
        User user = repository.selectOne(queryWrapper);
        if (user == null) return  true;
        return false;
    }

    @Override
    public User findByEmail(String email) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("email", email);
        return repository.selectOne(queryWrapper);
    }

    @Override
    public void createUser(String email,String username,String password) {
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setInitTime(new Date());
        repository.insert(user);
    }

    @Override
    public String LoginUser(User user) {
        String token = UUID.randomUUID().toString();
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
        redisSocketService.cacheSet(REDIS_USERID_KEY, user.getId());
        return token;
    }

    @Override
    public User getUserByToken(String token) {
        User user = redisService.getStringAndUpdate(REDIS_USER_KEY + token, REDIS_USER_TIME);
        return user;
    }

    @Override
    public void LogoutUser(String token) {
        User user = getUserByToken(token);
        redisService.deleteString(REDIS_USER_KEY + token);
        redisSocketService.deleteSet(REDIS_USERID_KEY, user.getId());
    }

    @Override
    public void updateUser(String token, String username, String signature, Integer sex) {
        User cacheuser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheuser == null) throw new ServiceProcessException("用户Session过期，请重新登录");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", cacheuser.getId());
        User user = repository.selectOne(queryWrapper);
        user.setUsername(username);
        user.setSex(sex);
        user.setSignature(signature);
        repository.updateById(user);
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
    }

    @Override
    public void updataUserIcon(String token, String icon) {
        User cacheUser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheUser == null) throw new ServiceProcessException("用户Session过期，请重新登录");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", cacheUser.getId());
        User user = repository.selectOne(queryWrapper);
        user.setIcon(icon);
        repository.updateById(user);
        redisService.cacheString(REDIS_USER_KEY + token, user, REDIS_USER_TIME);
    }

    @Override
    public void updateUserPassword(String token, String oldpsd, String newpsd) {
        User cacheUser = redisService.getString(REDIS_USER_KEY + token);
        if (cacheUser == null) throw new ServiceProcessException("用户Session过期，请重新登录");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", cacheUser.getId());
        User user = repository.selectOne(queryWrapper);
        if (!user.getPassword().equals(DigestUtils.md5DigestAsHex(oldpsd.getBytes())))
            throw new ServiceProcessException("原始密码错误，请重新输入");
        user.setPassword(DigestUtils.md5DigestAsHex(newpsd.getBytes()));
        repository.updateById(user);
        redisService.deleteString(REDIS_USER_KEY + token);
    }
}
