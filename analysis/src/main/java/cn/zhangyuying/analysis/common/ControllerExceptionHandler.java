package cn.zhangyuying.analysis.common;

import cn.zhangyuying.analysis.common.response.ApiResponse;
import cn.zhangyuying.analysis.common.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author zyy.
 */
@ControllerAdvice
@EnableWebMvc
public class ControllerExceptionHandler {
    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(AnalysisException.class)
    @ResponseBody
    String handleOrganParamException(AnalysisException e) {
        logger.error(e.getMessage(), e);
        Integer code = e.getStatus() == null ? 400 : e.getStatus();
        return ResponseUtil.toString(new ApiResponse(code, e.getMessage()));
    }



}
