package pojo;

import java.util.Date;

public class ErpOrder {
    private Long id;

    private String erp_order_no;

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

    private Date reportTime;

    private String pp_trade_no;

    private Boolean is_whole_order;

    private String member_id;

    private String trans_mode;

    private String orig_erp_order_no;

    private Integer yipay_amt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getErp_order_no() {
        return erp_order_no;
    }

    public void setErp_order_no(String erp_order_no) {
        this.erp_order_no = erp_order_no == null ? null : erp_order_no.trim();
    }

    public Date getTime_start() {
        return time_start;
    }

    public void setTime_start(Date time_start) {
        this.time_start = time_start;
    }

    public String getOrder_source() {
        return order_source;
    }

    public void setOrder_source(String order_source) {
        this.order_source = order_source == null ? null : order_source.trim();
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type == null ? null : order_type.trim();
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getStatus_desc() {
        return status_desc;
    }

    public void setStatus_desc(String status_desc) {
        this.status_desc = status_desc == null ? null : status_desc.trim();
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier == null ? null : cashier.trim();
    }

    public Integer getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(Integer total_amt) {
        this.total_amt = total_amt;
    }

    public Integer getDis_amt() {
        return dis_amt;
    }

    public void setDis_amt(Integer dis_amt) {
        this.dis_amt = dis_amt;
    }

    public Integer getReal_amt() {
        return real_amt;
    }

    public void setReal_amt(Integer real_amt) {
        this.real_amt = real_amt;
    }

    public Boolean getMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }

    public String getOrig_order_no() {
        return orig_order_no;
    }

    public void setOrig_order_no(String orig_order_no) {
        this.orig_order_no = orig_order_no == null ? null : orig_order_no.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getPp_trade_no() {
        return pp_trade_no;
    }

    public void setPp_trade_no(String pp_trade_no) {
        this.pp_trade_no = pp_trade_no == null ? null : pp_trade_no.trim();
    }

    public Boolean getIs_whole_order() {
        return is_whole_order;
    }

    public void setIs_whole_order(Boolean is_whole_order) {
        this.is_whole_order = is_whole_order;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id == null ? null : member_id.trim();
    }

    public String getTrans_mode() {
        return trans_mode;
    }

    public void setTrans_mode(String trans_mode) {
        this.trans_mode = trans_mode == null ? null : trans_mode.trim();
    }

    public String getOrig_erp_order_no() {
        return orig_erp_order_no;
    }

    public void setOrig_erp_order_no(String orig_erp_order_no) {
        this.orig_erp_order_no = orig_erp_order_no == null ? null : orig_erp_order_no.trim();
    }

    public Integer getYipay_amt() {
        return yipay_amt;
    }

    public void setYipay_amt(Integer yipay_amt) {
        this.yipay_amt = yipay_amt;
    }
}