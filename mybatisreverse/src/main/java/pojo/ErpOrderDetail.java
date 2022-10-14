package pojo;

public class ErpOrderDetail {
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getGoods_type() {
        return goods_type;
    }

    public void setGoods_type(String goods_type) {
        this.goods_type = goods_type == null ? null : goods_type.trim();
    }

    public Integer getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(Integer purchase_price) {
        this.purchase_price = purchase_price;
    }

    public Integer getSale_price() {
        return sale_price;
    }

    public void setSale_price(Integer sale_price) {
        this.sale_price = sale_price;
    }

    public String getGoods_plu() {
        return goods_plu;
    }

    public void setGoods_plu(String goods_plu) {
        this.goods_plu = goods_plu == null ? null : goods_plu.trim();
    }

    public Double getTare_weight() {
        return tare_weight;
    }

    public void setTare_weight(Double tare_weight) {
        this.tare_weight = tare_weight;
    }

    public Boolean getIs_gift() {
        return is_gift;
    }

    public void setIs_gift(Boolean is_gift) {
        this.is_gift = is_gift;
    }

    public Integer getSub_price() {
        return sub_price;
    }

    public void setSub_price(Integer sub_price) {
        this.sub_price = sub_price;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public String getErp_order_no() {
        return erp_order_no;
    }

    public void setErp_order_no(String erp_order_no) {
        this.erp_order_no = erp_order_no == null ? null : erp_order_no.trim();
    }
}