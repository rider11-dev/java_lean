package rider11.hellospringboot.bean;

import lombok.Data;

@Data
public class ResponseBase {
    private String requestId;
    private String traceId;
}