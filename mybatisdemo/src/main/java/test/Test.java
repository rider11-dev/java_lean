package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import mapper.UserMapper;
import mapper.WebsiteMapper;
import page.PageV0;
import po.User;
import po.Website;
import po.WebsitePOJO;
import po.WebsitePageQuery;

public class Test {
    private static Logger logger = LogManager.getLogger(Test.class);

    public static void main(String[] args) throws IOException {
        InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(config);
        SqlSession ss = ssf.openSession(org.apache.ibatis.session.ExecutorType.BATCH, false);
        // SqlSession ss = ssf.openSession();
        // Test1(ss);
        // Test2(ss);
        // Test3(ss);
        // Test4(ss);
        // Test5(ss);
        Test6(ss);
        ss.commit();
        ss.clearCache();
        ss.close();
    }

    static void Test1(SqlSession ss) {
        Website website = new Website();
        website.setName("rider11");
        website.setUrl("http://www.baidu.com");
        website.setAge(21);
        website.setCountry("CN");
        website.setCreatetime(new Date());
        // ss.insert("mapper.WebsiteMapper.addWebsite", website);// 方式1
        WebsiteMapper mapper = ss.getMapper(WebsiteMapper.class);// 方式2，推荐
        Integer count = mapper.addWebsite(website);
        logger.info("共插入" + count + "条数据,id=>" + website.getId());
        // update
        Website ws = new Website();
        ws.setId(website.getId());
        ws.setUrl("http://xxxxxxx.com");
        ws.setName("rider11哈哈");
        count = mapper.updateWebsiteByParam(ws);
        logger.info("共更新" + count + "条数据");
        count = mapper.deleteWebsiteById(website.getId());
        logger.info("共删除" + count + "条数据");

        List<WebsitePOJO> listWeb = mapper.selectAllWebsite();
        for (WebsitePOJO site : listWeb) {
            System.out.println(site);
        }
        // List<Website> listWeb = mapper.selectByName("rider11");

        // Website param = new Website();
        // param.setName("rider");
        // param.setAge(20);
        // List<Website> listWeb = mapper.selectByParam(param);

        // for (Website site : listWeb) {
        // System.out.println(site);
        // }

    }

    static void Test2(SqlSession ss) {
        UserMapper mapper = ss.getMapper(UserMapper.class);
        User usr = mapper.selectUserOrderById(1);
        System.out.println(usr);
    }

    static void Test3(SqlSession ss) {
        WebsiteMapper mapper = ss.getMapper(WebsiteMapper.class);
        List<Integer> ages = new ArrayList<Integer>();
        ages.add(10);
        ages.add(11);
        List<Website> sites = mapper.selectInAge(ages);
        for (Website site : sites) {
            System.out.println(site);
        }
    }

    static void Test4(SqlSession ss) {
        WebsiteMapper mapper = ss.getMapper(WebsiteMapper.class);
        WebsitePageQuery qry = new WebsitePageQuery();
        qry.setName("rider");
        // qry.setPageNum(1);
        qry.setPageNum(2);
        qry.setPageSize(10);
        int total = mapper.findPageTotal(qry);
        List<Website> data = mapper.findPageByHand(qry);

        System.out.println(new PageV0<Website>(qry.getPageSize(), qry.getPageNum(), total, data));
    }

    static void Test5(SqlSession ss) {
        WebsiteMapper mapper = ss.getMapper(WebsiteMapper.class);
        WebsitePageQuery qry = new WebsitePageQuery();
        qry.setAge(20);
        Page<Website> page = PageHelper.startPage(1, 5);
        mapper.findPageByPlugin(qry);
        // Page<Website>
        // page=PageHelper.startPage(1,5).doSelectPage(()->mapper.findPageByPlugin(qry)));
        System.out.println(
                new PageV0<Website>(page.getPageSize(), page.getPageNum(), (int) page.getTotal(), page.getResult()));
    }

    static void Test6(SqlSession ss) {
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 10000; i++) {
            User u = new User();
            u.setName("test" + i);
            u.setPwd("xxxxxxxx");
            users.add(u);
        }
        final UserMapper mapper = ss.getMapper(UserMapper.class);
        // UserMapperMP mapper = ss.getMapper(UserMapperMP.class);
        long begin = System.currentTimeMillis();
        // for (int i = 0; i < users.size(); i++) {
        // mapper.insertOne(users.get(i));
        // }
        mapper.insertMulti(users);

        long end = System.currentTimeMillis();
        // 循环插入[10000]耗时:67117ms
        // System.out.println("循环插入[" + users.size() + "]耗时:" + (end - begin) + "ms");
        // 原生批量插入[10000]耗时:4610ms
        System.out.println("原生批量插入[" + users.size() + "]耗时:" + (end - begin) + "ms");
    }
}
