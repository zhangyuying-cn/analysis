package cn.zhangyuying.analysis.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private Integer status;

    private String message;

    private T data;

    public ApiResponse(String message) {
        this.message = message;
    }

    public ApiResponse(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Integer code, String message) {
        this(code, message, null);
    }

    public ApiResponse(ResponseStatus status, String message) {
        this(status.getStatus(), message, null);
    }

    public ApiResponse(Integer status, T data) {
        this(ResponseStatus.getRequestStatus(status), data);
    }

    public ApiResponse(ResponseStatus status, T data) {
        this(status.getStatus(), status.getMessage(), data);
    }

    public ApiResponse(ResponseStatus status) {
        this(status.getStatus(), status.getMessage(), null);
    }


}
