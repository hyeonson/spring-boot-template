package com.hyeonson.yajalal.dto;

import com.hyeonson.yajalal.utils.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DefaultRes<T> {

    private int status;

    private String message;

    private T data;

    public DefaultRes(final int status, final String message) {
        this.status = status;
        this.message = message;
        this.data = null;
    }

    public static<T> DefaultRes<T> res(final int status, final String message) {
        return res(status, message, null);
    }

    public static<T> DefaultRes<T> res(final int status, final String message, final T t) {
        return DefaultRes.<T>builder()
                .data(t)
                .status(status)
                .message(message)
                .build();
    }

//    public static final DefaultRes SERVER_ERROR_RES = new DefaultRes(500, ResponseMessage.FAIL);
    public static final DefaultRes FAIL_REQUEST_RES = new DefaultRes(400, ResponseMessage.FAIL);
    public static final DefaultRes SUCCESS_RES = new DefaultRes(200, ResponseMessage.OK);
}
