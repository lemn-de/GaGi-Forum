package com.forum.common.base;

import com.forum.common.dto.QuarkResult;

@FunctionalInterface
public interface ResultProcessor {
    QuarkResult process();
}
