package com.forum.common.base;

import com.forum.common.dto.QuarkResult;

import com.forum.common.exception.ServiceProcessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected QuarkResult restProcessor(ResultProcessor processor) {
        QuarkResult result = null;
        try {
            result = processor.process();
        }
        catch (ServiceProcessException e1) {
            logger.error("ServiceProcess Error Log :" + e1.getLocalizedMessage(), e1);
            result = QuarkResult.error(e1.getMessage());
        }
        catch (Exception e) {
            logger.error("Error Log :" + e.getLocalizedMessage(), e);
            result = QuarkResult.error("服务器异常");
        }

        return result;
    }
}