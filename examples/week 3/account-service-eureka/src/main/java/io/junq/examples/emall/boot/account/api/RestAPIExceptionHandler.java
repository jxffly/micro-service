package io.junq.examples.emall.boot.account.api;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(basePackages = "io.junq.examples.emall")
public class RestAPIExceptionHandler implements ResponseBodyAdvice<Object> {

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// TODO exception
		ApiResponse apiRes = new ApiResponse(body);
		return apiRes;
	}

	//TODO global exception handler
//	@ExceptionHandler(Exception.class)
//	public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception ex) {
//		// If exception has a ResponseStatus annotation then use its response
//		// code
//		ResponseStatus responseStatusAnnotation = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
//		
////		return buildModelAndViewErrorPage(request, response, ex,
////				responseStatusAnnotation != null ? responseStatusAnnotation.value() : HttpStatus.INTERNAL_SERVER_ERROR);
//	}

}
