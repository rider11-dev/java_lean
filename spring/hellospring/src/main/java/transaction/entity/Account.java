package transaction.entity;

import java.math.BigDecimal;

public class Account {
    // 自增 id
    private Long id;
    // 用户 id
    private String userId;
    // 账户总金额
    private BigDecimal total;
    // 已用账户金额
    private BigDecimal used;
    // 剩余账户金额
    private BigDecimal residue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getUsed() {
        return used;
    }

    public void setUsed(BigDecimal used) {
        this.used = used;
    }

    public BigDecimal getResidue() {
        return residue;
    }

    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }
}
