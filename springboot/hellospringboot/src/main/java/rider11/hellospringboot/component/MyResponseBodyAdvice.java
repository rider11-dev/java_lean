package rider11.hellospringboot.component;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.val;
import lombok.extern.slf4j.Slf4j;
import rider11.hellospringboot.bean.ResponseBase;
import rider11.hellospringboot.utils.Constants;

@ControllerAdvice
@Slf4j
public class MyResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    @Nullable
    public Object beforeBodyWrite(@Nullable Object resp, MethodParameter methodParameter, MediaType mediaType,
            Class<? extends HttpMessageConverter<?>> converter, ServerHttpRequest request,
            ServerHttpResponse response) {
        if (resp != null && resp instanceof ResponseBase) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            assert attributes != null;
            Object val = attributes.getAttribute(Constants.RequestId, 0);
            ((ResponseBase) resp).setRequestId(val == null ? "" : val.toString());
            val = attributes.getAttribute(Constants.TraceId, 0);
            ((ResponseBase) resp).setTraceId(val == null ? "" : val.toString());
        }
        return resp;
    }

}
