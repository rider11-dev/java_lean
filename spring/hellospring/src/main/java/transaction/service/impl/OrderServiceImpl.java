package transaction.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import transaction.dao.OrderDao;
import transaction.dao.AccountDao;
import transaction.dao.StorageDao;
import transaction.entity.Account;
import transaction.entity.Order;
import transaction.entity.Storage;
import transaction.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;
    private AccountDao accountDao;
    private StorageDao storageDao;
    Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    public OrderServiceImpl(OrderDao orderDao, AccountDao accountDao, StorageDao storageDao) {
        this.orderDao = orderDao;
        this.accountDao = accountDao;
        this.storageDao = storageDao;
    }

    // 基于注解的事务声明
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, timeout = 10, readOnly = false)
    @Override
    public void createOrder(Order order) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String format = sdf.format(new Date());
        String orderId = order.getUserId() + order.getProductId() + format;
        logger.info("生成的订单id为：" + orderId);
        order.setOrderId(orderId);
        // 创建订单数据
        orderDao.createOrder(order);
        logger.info("订单数据创建完成：" + orderId);
        logger.info("开始查询商品库存，商品id为：" + order.getProductId());
        Storage storage = this.storageDao.selectByProductId(order.getProductId());
        if (storage != null && storage.getResidue() >= order.getCount()) {
            logger.info("商品库存充足，正在扣减商品库存");
            storage.setUsed(storage.getUsed() + order.getCount());
            storage.setResidue(storage.getTotal() - storage.getUsed());
            storageDao.decrease(storage);
            logger.info("商品库存扣减完成");
        } else {
            logger.info("警告：商品库存不足，正在执行回滚操作！");
            throw new RuntimeException("商品库存不足:" + order.getProductId());
        }
        logger.info("开始查询用户账户金额");
        Account account = accountDao.selectByUserId(order.getUserId());
        if (account != null && account.getResidue().intValue() >= order.getMoney().intValue()) {
            logger.info("账户金额充足，正在扣减账户金额");
            accountDao.decrease(order.getUserId(), order.getMoney());
            logger.info("账户金额扣减完成");
        } else {
            logger.info("警告：账户余额不足，正在执行回滚操作");
            throw new RuntimeException("账户余额不足");
        }
        logger.info("开始修改订单状态，未完成>>>>>>已完成");
        orderDao.updateOrderStatus(order.getOrderId(), 1);
        logger.info("修改订单状态完成");
    }

}
