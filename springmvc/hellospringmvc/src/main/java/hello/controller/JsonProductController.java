package hello.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hello.dao.json.JsonProductDao;
import hello.model.json.Product;

@Controller
@RequestMapping("/json/product")
public class JsonProductController {
    private JsonProductDao productDao;

    public JsonProductController(JsonProductDao productDao) {
        this.productDao = productDao;
    }

    @ResponseBody
    @RequestMapping("/get/{productId}")
    public Product getProduct(@PathVariable("productId") String productId) {
        Product p = this.productDao.getProductById(productId);
        return p;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        this.productDao.addProduct(product);
        return product;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteProduct(String productId) {
        this.productDao.deleteProduct(productId);
        return "redirect:/json";
    }

    @ResponseBody
    @RequestMapping("/getProductList")
    public List<Product> getProductList() {
        List<Product> lst = this.productDao.getProductList();
        return lst;
    }

    @RequestMapping("/edit")
    public String updateProduct(Product product) {
        this.productDao.updateProduct(product);
        return "redirect:/json";
    }
}
