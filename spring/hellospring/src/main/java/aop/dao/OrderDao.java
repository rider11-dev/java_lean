package aop.dao;

import org.springframework.stereotype.Repository;

@Repository("orderDao")
public class OrderDao {
     public void add() {
        System.out.println("正在执行 OrderDao 的 add() 方法……");
    }
    public void adds() {
        System.out.println("正在执行 OrderDao 的 adds() 方法……");
    }
    public void delete() {
        System.out.println("正在执行 OrderDao 的 delete() 方法……");
    }
    public void modify() {
        System.out.println("正在执行 OrderDao 的 modify() 方法……");
    }
    public void get() {
        System.out.println("正在执行 OrderDao 的 get() 方法……");
    }
}
