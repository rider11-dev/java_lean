package rider11.hellospringboot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@TableName("erp_order_detail")
public class ErpOrderDetail {
    @TableId
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private Long order_id;

    private String category;

    private String barcode;

    private String name;

    private Double num;

    private String unit;

    private String goods_type;

    private Integer purchase_price;

    private Integer sale_price;

    private String goods_plu;

    private Double tare_weight;

    private Boolean is_gift;

    private Integer sub_price;

    private Long parent_id;

    private String erp_order_no;
}
