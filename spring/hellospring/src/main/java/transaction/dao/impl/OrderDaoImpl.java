package transaction.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import transaction.dao.OrderDao;
import transaction.entity.Order;

@Repository
public class OrderDaoImpl implements OrderDao {

    private JdbcTemplate jdbc;

    public OrderDaoImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public int createOrder(Order order) {
        String sql = "insert into `order` (order_id,user_id, product_id, `count`, money, status) values (?,?,?,?,?,?)";
        int update = this.jdbc.update(sql, order.getOrderId(), order.getUserId(), order.getProductId(),
                order.getCount(), order.getMoney(), order.getStatus());
        return update;
    }

    @Override
    public void updateOrderStatus(String orderId, Integer status) {
        String sql = " update `order`  set status = 1 where order_id = ? and status = ?;";
        this.jdbc.update(sql, orderId, status);
    }

}
