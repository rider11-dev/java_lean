package rider11.hellospringboot.bean;

import lombok.Data;

@Data
public class ResponseData<T> extends ResponseBase {
    private T data;

    public ResponseData(T data) {
        this.data = data;
    }
    public ResponseData() {
    }
}
