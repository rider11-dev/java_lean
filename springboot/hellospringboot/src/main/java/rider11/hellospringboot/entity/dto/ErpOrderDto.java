package rider11.hellospringboot.entity.dto;

import java.util.List;

import lombok.Data;
import rider11.hellospringboot.entity.ErpOrder;
import rider11.hellospringboot.entity.ErpOrderDetail;

@Data
public class ErpOrderDto extends ErpOrder {
    private List<ErpOrderDetail> details;

    public static ErpOrderDto build(ErpOrder order) {
        return DtoMapper.Instance.mapOrderDto(order);
    }
}
