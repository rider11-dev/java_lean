package hellospring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import aop.dao.GoodsDao;
import aop.dao.OrderDao;
import aop.dao.UserDao;
import aspectj.service.UserService;
import autodi.controller.UserController;
import didemo.Dog;
import didemo.Employee;
import didemo.EmployeeAuto;
import didemo.Student;
import jdbc.entity.User;
import transaction.entity.Order;
import transaction.service.OrderService;

/**
 * Hello world!
 *
 */
public class App {
    // private static final Log logger = LogFactory.getLog(App.class);
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        // System.out.println("Hello World!");
        try {
            ApplicationContext ctx = GetAppContext();
            // Test1(ctx);
            // Test2(ctx);
            // Test3(ctx);
            // Test4(ctx);
            // Test5(ctx);
            // Test6(ctx);
            // Test7(ctx);
            // Test8(ctx);
            // Test9(ctx);
            // Test10(ctx);
            // Test11(ctx);
            Test12(ctx);
        } catch (Exception ex) {
            logger.error("出错了", ex);
        }
    }

    private static void Test12(ApplicationContext ctx) {
        OrderService orderService = ctx.getBean(OrderService.class);
        Order order = new Order();
        order.setProductId("1");
        order.setCount(30);
        order.setMoney(new BigDecimal(600));
        order.setUserId("1");
        order.setStatus(0);
        orderService.createOrder(order);
    }

    private static void Test11(ApplicationContext ctx) {
        jdbc.service.UserService userService = ctx.getBean(jdbc.service.UserService.class);
        User user = new User();
        user.setUserName("小张");
        user.setStatus("离线线");
        // 新增一个用户
        int i = userService.addUser(user);
        System.out.println("新增用户成功！");
        User user1 = new User();
        user1.setUserName("小张");
        user1.setStatus("在线");
        int u = userService.updateUser(user1);
        System.out.println("修改用户成功");
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = { "小明", "在线" };
        Object[] o2 = { "小龙", "离线" };
        Object[] o3 = { "小林", "在线" };
        Object[] o4 = { "小李", "在线" };
        batchArgs.add(o1);
        batchArgs.add(o2);
        batchArgs.add(o3);
        batchArgs.add(o4);
        userService.batchAddUser(batchArgs);
        System.out.println("批量增加完毕");
        User user2 = new User();
        user2.setStatus("在线");
        int i1 = userService.countUser(user2);
        System.out.println("在线用户的个数为：" + i1);
        List<User> userList = userService.getUserList(user2);
        System.out.println("在线用户列表查询成功！");
        for (User user4 : userList) {
            System.out
                    .println("用户 ID:" + user4.getUserId() + "，用户名：" + user4.getUserName() + "，状态：" + user4.getStatus());
        }
    }

    private static void Test10(ApplicationContext ctx) {
        UserService userService = ctx.getBean(UserService.class);
        userService.addUser("张三");
        userService.getUser();
        userService.exception();
    }

    private static void Test9(ApplicationContext ctx) {
        UserDao userDao = ctx.getBean("userDao", UserDao.class);
        OrderDao orderDao = ctx.getBean("orderDao", OrderDao.class);
        GoodsDao goodsDao = ctx.getBean("goodsDao", GoodsDao.class);
        // 调用 UserDao 中的各个方法
        userDao.add();
        userDao.delete();
        userDao.modify();
        userDao.get();
        // 调用 OrderDao 中的各个方法
        orderDao.add();
        orderDao.adds();
        orderDao.delete();
        orderDao.get();
        orderDao.modify();
        // 调用GoodsDao中的方法
        goodsDao.save();
    }

    private static void Test8(ApplicationContext ctx) {
        // 手动配置Proxy=>Beans_AOP_Manual.xml
        OrderDao orderDao = ctx.getBean("orderDaoProxy", OrderDao.class);
        orderDao.add();
        orderDao.adds();
        orderDao.delete();
        orderDao.get();
        orderDao.modify();
    }

    private static void Test7(ApplicationContext ctx) {
        UserDao userDao = ctx.getBean("userDaoProxy", UserDao.class);
        userDao.add();
        userDao.delete();
        userDao.get();
        userDao.modify();
    }

    private static void Test6(ApplicationContext ctx) {
        UserController userController = ctx.getBean("userController", UserController.class);
        userController.doStr();
    }

    private static void Test5(ApplicationContext ctx) {
        EmployeeAuto emp = ctx.getBean("employeeAuto", EmployeeAuto.class);
        logger.info(emp.toString());
    }

    private static void Test4(ApplicationContext ctx) {
        Dog dog = ctx.getBean("dog", Dog.class);
        logger.info(dog.toString());
    }

    private static void Test3(ApplicationContext ctx) {
        Employee e1 = ctx.getBean("employee", Employee.class);
        logger.info(e1.toString());
        Employee e2 = ctx.getBean("employee", Employee.class);
        logger.info(e2.toString());
        logger.info("e1=>" + e1.hashCode());
        logger.info("e2=>" + e2.hashCode());
        logger.info("e1.dept=>" + e1.getDept().hashCode());
        logger.info("e2=>" + e2);
        logger.info("e2.dept=>" + e2.getDept().hashCode());
    }

    private static void Test2(ApplicationContext ctx) {
        Student stu = ctx.getBean(Student.class);
        logger.info(stu.toString());
    }

    static void Test1(ApplicationContext ctx) {
        Helloworld obj = ctx.getBean("helloWorld", Helloworld.class);
        obj.getMessage();
    }

    static ApplicationContext GetAppContext() {
        String configFile = "Beans_Transaction_JDBC_Auto.xml";
        // String configFile = "Beans_Transaction_JDBC_Manual.xml";
        // String configFile = "Beans_JDBC.xml";
        // String configFile = "Beans_AspectJ_Manual.xml";
        // String configFile =
        // "D:/learn/java_learn/spring/hellospring/src/main/resources/Beans.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configFile);
        // ApplicationContext ctx = new FileSystemXmlApplicationContext(configFile);
        // ApplicationContext ctx = new
        // ClassPathXmlApplicationContext("Beans_Auto.xml");
        return ctx;
    }

}
