package com.forum.rest.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.forum.common.base.BaseController;
import com.forum.common.dto.QuarkResult;
import com.forum.common.entity.Label;
import com.forum.common.entity.Posts;
import com.forum.common.entity.User;
import com.forum.rest.service.LabelService;
import com.forum.rest.service.PostsService;
import com.forum.rest.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(value = "帖子接口")
@RestController
@RequestMapping("/posts")
public class PostsController extends BaseController {
    @Autowired
    private LabelService labelService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostsService postsService;

    @ApiOperation("发帖接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "帖子内容", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "帖子标题", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "用户令牌", dataType = "String"),
            @ApiImplicitParam(name = "labelId", value = "标签ID", dataType = "Integer")
    })
    @PostMapping
    public QuarkResult CreatePosts(Posts posts, String token, Integer labelId) {
        QuarkResult result = restProcessor(() -> {
            if (token == null) return QuarkResult.warn("请先登录!");
            User userbytoken = userService.getUserByToken(token);
            if (userbytoken == null) return QuarkResult.warn("用户不存在，请先登录！");
            if (userbytoken.getEnable() != 1) return QuarkResult.warn("用户处于封禁状态！");
            Label label = labelService.findOne(labelId);
            posts.setLabel(label);
            posts.setUser(userbytoken);
            postsService.save(posts);
            return QuarkResult.ok();
        });
        return result;
    }

    @ApiOperation("分页查询帖子接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "search", value = "查询条件", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "帖子类型[top : good : ]", dataType = "String"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int")
    })
    @GetMapping()
    public QuarkResult GetPosts(
            @RequestParam(required = false, defaultValue = "") String search,
            @RequestParam(required = false, defaultValue = "") String type,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length) {
        QuarkResult result = restProcessor(() -> {
            if (!type.equals("good") && !type.equals("top") && type.equals(""))
                return QuarkResult.error("类型错误!");
            Page<Posts> postsPage = postsService.getPostByPage(type, search, pageNo - 1, length);
            return QuarkResult.ok(postsPage.getRecords(), postsPage.getSize(), postsPage.getTotal());
//            return QuarkResult.ok(postsPage.getContent(), postsPage.getNumberOfElements(), postsPage.getTotalElements());
        });

        return result;
    }

    @ApiOperation("根据labelId获取帖子接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "labelid", value = "标签的id", dataType = "int"),
            @ApiImplicitParam(name = "pageNo", value = "页码[从1开始]", dataType = "int"),
            @ApiImplicitParam(name = "length", value = "返回结果数量[默认20]", dataType = "int"),
    })
    @GetMapping("/label/{labelid}")
    public QuarkResult GetPostsByLabel(
            @PathVariable("labelid") Integer labelid,
            @RequestParam(required = false, defaultValue = "1") int pageNo,
            @RequestParam(required = false, defaultValue = "20") int length){
        QuarkResult result = restProcessor(() ->{
            Label label = labelService.findOne(labelid);
            if (label == null) return QuarkResult.error("标签不存在");
            Page<Posts> page = postsService.getPostsByLabel(label, pageNo - 1, length);
            return QuarkResult.ok(page.getRecords(), page.getSize(), page.getTotal());
//            return QuarkResult.ok(page.getContent(), page.getNumberOfElements(), page.getTotalElements());
        });
        return result;
    }

}
