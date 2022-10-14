package rider11.hellospringboot.bean;


import lombok.Data;

@Data
public class RequestLog {
    private String url;
    private String method;
    private String param;
    private String ip;
}
