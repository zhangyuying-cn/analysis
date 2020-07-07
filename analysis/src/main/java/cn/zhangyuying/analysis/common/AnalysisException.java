package cn.zhangyuying.analysis.common;

import cn.zhangyuying.analysis.common.response.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zyy.
 */
@Setter
@Getter
public class AnalysisException extends IllegalArgumentException {
    private final Integer status;

    public AnalysisException(Integer code, String message) {
        super(message);
        this.status = code;
    }

    public AnalysisException(ResponseStatus status) {
        super(status.getMessage());
        this.status = status.getStatus();
    }

    public AnalysisException(String message) {
        super(message);
        this.status = 400;
    }
}
