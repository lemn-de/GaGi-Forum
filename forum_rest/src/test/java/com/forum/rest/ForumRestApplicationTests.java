package com.forum.rest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.common.dao.PostsDao;
import com.forum.common.dao.UserDao;
import com.forum.common.entity.Posts;
import com.forum.common.entity.User;
import com.forum.rest.service.PostsService;
import com.forum.rest.service.RedisService;
import com.forum.rest.service.UserService;
import com.forum.rest.service.impl.PostsServiceImpl;
import javafx.geometry.Pos;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ForumRestApplicationTests {

    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;


    @Test
    public void contextLoads() {
    }


    @Autowired
    PostsDao postsDao;
    @Test
    public void selectByPage() {
        IPage<Posts> page = new Page<>(1, 2);
        IPage<Posts> postsIPage = postsDao.selectPage(page,null);
        long total = postsIPage.getTotal();
        System.out.println("总记录数" +total);
        postsIPage.getRecords().forEach(posts -> System.out.println("posts=" + posts));
    }

    @Autowired
    UserDao userDao;
    @Test
    public void testSelectByPage() {
        IPage<User> page = new Page<>(1, 2);
        IPage<User> userIPage = userDao.selectPage(page,null);
        long total = userIPage.getTotal();
        System.out.println("总记录数" +total);
        userIPage.getRecords().forEach(user -> System.out.println("posts=" + user));
    }


    @Autowired
    PostsService postsService;
    @Test
    public void postsSelect() {
        Page<Posts> page = new Page<>(1,10);
//        List<Posts> posts = postsDao.selectPostsPage(page);
//        page.setRecords(postsDao.selectPostsPage(page));
//        postsService.selectPost();
        System.out.println("输出");
        System.out.println(page);
    }




    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testBoundKey(){
        BoundValueOperations<String, String> nameValueOperations = stringRedisTemplate.boundValueOps("name");
        nameValueOperations.set("1");
        //yuew
        nameValueOperations.set("2");
        String s = nameValueOperations.get();
        System.out.println(s);

    }

    @Value("${REDIS_USERID_KEY}")
    private String REDIS_USERID_KEY;
    @Test
    public void testDataSource() {
//        userService.updateUserPassword("2a8e48c8-9d09-4ef4-892c-b3436070297c","12345678","123456");
        redisService.cacheSet(REDIS_USERID_KEY,8);
        redisService.cacheSet(REDIS_USERID_KEY,10);
        redisService.deleteSet(REDIS_USERID_KEY,8);
        redisService.cacheSet("name", "xiaoming");
            System.out.println(redisService.setHasValue(REDIS_USERID_KEY,5));
    }
}
