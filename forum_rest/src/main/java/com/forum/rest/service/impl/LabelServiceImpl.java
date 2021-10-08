package com.forum.rest.service.impl;

import com.forum.common.base.BaseServiceImpl;
import com.forum.common.dao.LabelDao;
import com.forum.common.entity.Label;
import com.forum.rest.service.LabelService;
import org.springframework.stereotype.Service;

@Service
public class LabelServiceImpl extends BaseServiceImpl<LabelDao, Label> implements LabelService {

}
