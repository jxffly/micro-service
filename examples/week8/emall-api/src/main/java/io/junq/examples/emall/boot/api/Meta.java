package io.junq.examples.emall.boot.api;

import java.util.List;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Meta {
	public static final Integer OK = 200;
	
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
}
