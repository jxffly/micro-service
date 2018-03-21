package io.junq.examples.emall.boot.account.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.junq.examples.emall.boot.account.domain.MyException;


@ControllerAdvice(basePackages = "io.junq.examples.emall")
public class RestAPIExceptionHandler implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestAPIExceptionHandler.class);
    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        // TODO exception
        ApiResponse apiRes = new ApiResponse(null);

        if (body instanceof Meta) {
            apiRes.setMeta((Meta) body);
            return apiRes;

        }
        apiRes.setMeta(Meta.getSuccess());
        apiRes.setData(body);
        return apiRes;
    }


    //	TODO global exception handler
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Meta exceptionHandler(HttpServletRequest request, HttpServletResponse response, Throwable t) {
        // If exception has a ResponseStatus annotation then use its response
        // code
        /*ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(t.getClass(), ResponseStatus.class);
        buildModelAndViewErrorPage(request, response, t, responseStatusAnnotation != null ?
                responseStatusAnnotation.value() : HttpStatus.NOT_ACCEPTABLE);*/
        return exceptionBuilder(t);

    }


    private Meta exceptionBuilder(Throwable t) {
        Meta meta = new Meta();
        LOGGER.warn("request occur an error",t);
        if (t instanceof MyException) {
            MyException t1 = (MyException) t;
            meta.setCode(t1.getCode());
            meta.setMessage(t1.getMsg());
            return meta;
        }else if (t instanceof RuntimeException) {
            meta.setCode(1001);
            meta.setMessage("runtime exception");
            return meta;
        }else if(t instanceof MethodArgumentNotValidException){
            meta.setCode(1003);
            meta.setMessage(handlerParamsException((MethodArgumentNotValidException) t));
            return meta;
        }
        else {
            meta.setCode(1002);
            meta.setMessage("fatal exception exception");
            return meta;
        }
    }


    private String handlerParamsException(MethodArgumentNotValidException e) {
        List<FieldError> allErrors = e.getBindingResult().getFieldErrors();
        ObjectNode errorNode = mapper.createObjectNode();
        allErrors.forEach(objectError -> errorNode.put(objectError.getField(),objectError.getDefaultMessage()));
        return errorNode.toString();
    }


    private String buildModelAndViewErrorPage(HttpServletRequest request, HttpServletResponse response, Throwable t, HttpStatus httpStatus) {


        return "test";
    }

}
