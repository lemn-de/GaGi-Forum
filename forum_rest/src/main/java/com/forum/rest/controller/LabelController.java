package com.forum.rest.controller;

import com.forum.common.dto.QuarkResult;
import com.forum.common.entity.Label;
import com.forum.common.base.BaseController;
import com.forum.rest.service.LabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "标签接口", description = "获取标签")
@RestController
@RequestMapping("/label")
public class LabelController extends BaseController{
    @Autowired
    private LabelService labelService;


    @ApiOperation("获取标签")
    @GetMapping
    public QuarkResult getAllLabel() {

        QuarkResult result = restProcessor(() -> {
            List<Label> labels = labelService.findAll();
            return QuarkResult.ok(labels);
        });

        return result;
    }
}
