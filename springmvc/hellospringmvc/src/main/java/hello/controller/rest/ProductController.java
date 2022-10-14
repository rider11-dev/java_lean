package hello.controller.rest;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hello.dao.ProductDao;
import hello.model.Product;

@Controller
@RequestMapping("/rest/product")
public class ProductController {

    private ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping("/list")
    public String getProducts(Model model) {
        List<Product> list = this.productDao.getProductList();
        model.addAttribute("products", list);
        return "rest/product_list";
    }

    @RequestMapping("/{action}/{productId}")
    public String getProduct(@PathVariable("action") String action, @PathVariable("productId") String productId,
            Model model) {
        Product p = this.productDao.getProductById(productId);
        model.addAttribute("product", p);
        if (action.equals("get")) {
            return "rest/product_info";
        } else {
            return "rest/product_update";
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addProduct(Product product) {
        this.productDao.addProduct(product);
        return "redirect:/rest/product/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String updateProduct(Product product) {
        this.productDao.updateProduct(product);
        return "redirect:/rest/product/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteProduct(String productId) {
        this.productDao.deleteProduct(productId);
        return "redirect:/rest/product/list";
    }
}
