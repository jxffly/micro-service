package io.junq.examples.emall.boot.auth;

import lombok.Data;


/**
 * Created by jinxiaofei.
 * Time 2017/8/12 下午7:42
 * Desc 文件描述
 */
@Data
public class MyException extends RuntimeException {

    private Integer code;

    private String msg;

}
