package hello.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import hello.model.Product;

@Repository
public class ProductDao {
    private static Map<String, Product> products = null;
    static {
        products = new HashMap<String, Product>();
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("茅台");
        product.setPrice(new BigDecimal(9999));
        product.setStock(1000);
        product.setIntroduction(
                "茅台酒是大曲酱香型酒的鼻祖,它具有色清透明、酱香突出、醇香馥郁、幽雅细腻、入口柔绵、清冽甘爽、酒体醇厚丰满、回味悠长的特点、人们把茅台酒独有的香味称为\"茅香\",是中国酱香型风格的典范。");
        Product product1 = new Product();
        product1.setProductId("2");
        product1.setProductName("五粮液");
        product1.setPrice(new BigDecimal(8888));
        product1.setStock(1000);
        product1.setIntroduction("五粮液，四川省宜宾市特产，中国国家地理标志产品。以五粮液为代表的中国白酒，有着4000多年的酿造历史，堪称世界最古老、最具神秘特色的食品制造产业之一。");
        Product product2 = new Product();
        product2.setProductId("3");
        product2.setProductName("信阳毛尖");
        product2.setPrice(new BigDecimal(7777));
        product2.setStock(1000);
        product2.setIntroduction("信阳毛尖又称豫毛峰，属绿茶类，是中国十大名茶之一，也是河南省著名特产之一；其主要产地在信阳市浉河区（原信阳市）、平桥区（原信阳县）和罗山县。");
        Product product3 = new Product();
        product3.setProductId("4");
        product3.setProductName("深州大蜜桃");
        product3.setPrice(new BigDecimal(6666));
        product3.setStock(1000);
        product3.setIntroduction(
                "深州蜜桃，河北省深州市特产，中国国家地理标志产品。深州蜜桃个头硕大，果型秀美，色鲜艳，皮薄肉细，汁甜如蜜。2014年10月8日，国家质检总局正式批准“深州蜜桃”为原产地域保护产品（即地理标志保护产品）。");

        products.put(product.getProductId(), product);
        products.put(product1.getProductId(), product1);
        products.put(product2.getProductId(), product2);
        products.put(product3.getProductId(), product3);
    }

    public List<Product> getProductList() {
        List<Product> list = new ArrayList<Product>();
        Set<String> keys = products.keySet();
        for (String key : keys) {
            list.add(products.get(key));
        }
        return list;
    }

    public Product getProductById(String id) {
        return products.get(id);
    }

    public void addProduct(Product p) {
        products.put(p.getProductId(), p);
    }

    public void updateProduct(Product p) {
        products.put(p.getProductId(), p);
    }

    public void deleteProduct(String id) {
        products.remove(id);
    }
}
