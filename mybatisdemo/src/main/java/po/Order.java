package po;

public class Order{
    private int oId;
    private int ordernum;

    public int getoId() {
        return oId;
    }

    public void setoId(int oId) {
        this.oId = oId;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    @Override 
    public String toString() {
        return "Order [id=" + oId + ", ordernum=" + ordernum + "]";
    }
}