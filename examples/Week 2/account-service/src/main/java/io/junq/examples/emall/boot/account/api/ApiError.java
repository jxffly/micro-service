package io.junq.examples.emall.boot.account.api;

import org.springframework.http.HttpStatus;

import java.util.List;

import lombok.Data;


/**
 * Created by jinxiaofei.
 * Time 2017/8/12 下午6:41
 * Desc 文件描述
 */
@Data
public class ApiError {

    HttpStatus status;

    String localizedMessage;

    List<String> errors;


    public ApiError(HttpStatus status, String localizedMessage, List<String> errors) {
        this.status = status;
        this.localizedMessage = localizedMessage;
        this.errors = errors;
    }
}
