package io.junq.examples.emall.boot.account.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

import lombok.Data;


@Data
@JsonInclude(Include.NON_NULL)
public class Meta {

    public static final Integer OK = 200;

    public static final String OK_MSG = "成功";

    private static final Object o = new Object();

    private static volatile Meta SUCCESS = null;

    private int code = -1;

    private String message;

    @JsonIgnore
    private List<String> errors;


    @JsonProperty("error_type")
    public String getErrorType() {
        if (CollectionUtils.isEmpty(errors))
            return null;

        return StringUtils.collectionToCommaDelimitedString(errors);
    }


    public static final Meta getSuccess() {
        if (SUCCESS == null) {
            synchronized (o){
                Meta meta = new Meta();
                meta.setCode(OK);
                meta.setMessage(OK_MSG);
                SUCCESS = meta;
                return SUCCESS;
            }
        }else {
            return SUCCESS;
        }
    }
}
