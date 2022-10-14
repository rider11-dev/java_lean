package aop.dao.impl;

import org.springframework.stereotype.Component;

import aop.dao.GoodsDao;

@Component("goodsDao")
public class GoodsDaoImpl implements GoodsDao {
    public void save() {
        int a = 10;
        int b = 0;
        System.out.println(a / b);
    }
}
