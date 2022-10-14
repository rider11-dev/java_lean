package rider11.hellospringboot.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("erp_order")
// 生成无参构造函数
@NoArgsConstructor
public class ErpOrder {
    @TableId
    @JsonIgnore
    private Long id;

    private String erp_order_no;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Date time_start;

    private String order_source;

    private String order_type;
    private String appId;

    private Byte status;

    private String status_desc;

    private String merchantId;

    private String cashier;

    private Integer total_amt;

    private Integer dis_amt;

    private Integer real_amt;

    private Boolean member;

    private String orig_order_no;

    @JsonIgnore
    private Date reportTime;

    private String pp_trade_no;

    private Boolean is_whole_order;

    private String member_id;

    private String trans_mode;

    private String orig_erp_order_no;

    private Integer yipay_amt;
}
