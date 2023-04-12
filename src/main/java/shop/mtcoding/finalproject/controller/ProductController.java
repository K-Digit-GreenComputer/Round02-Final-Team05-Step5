package shop.mtcoding.finalproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.finalproject.dto.ResponseDto;
import shop.mtcoding.finalproject.dto.product.ProductReqDto.SameReqDto;
import shop.mtcoding.finalproject.model.product.Product;
import shop.mtcoding.finalproject.model.product.ProductRepository;

@Controller
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping({ "/", "/product" })
    public String main(Model model) { // model = request
        List<Product> productList = productRepository.findAll();
        model.addAttribute("productList", productList);
        return "product/main"; // request 새로 만들어짐 - 덮어쒸움(프레임워크)
    }

    @GetMapping("/product/{id}")
    public String findOne(@PathVariable int id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @GetMapping("/product/addForm")
    public String addForm() {
        return "product/addForm";
    }

    @PostMapping("/product/add")
    public String add(String name, Integer price, Integer qty) {
        int result = productRepository.insert(name, price, qty);
        if (result == 1) {
            return "redirect:/product";
        } else {
            return "redirect:product/addForm";
        }
    }

    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable int id) {
        int result = productRepository.delete(id);
        if (result == 1) {
            return "redirect:/";
        } else {
            return "redirect:/product/" + id;
        }
    }

    @GetMapping("product/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product/updateForm";
    }

    @PostMapping("/product/{id}/update")
    public String update(@PathVariable int id, String name, int price, int qty) {
        int result = productRepository.update(id, name, price, qty);
        if (result == 1) {
            return "redirect:/product/" + id;
        } else {
            return "redirect:/product/" + id + "/updateForm";
        }
    }
    
    @GetMapping("/product/productnameSameCheck")
    public @ResponseBody ResponseDto<?> check(String productname, SameReqDto SameReqDto) {
        if (productname == null || productname.isEmpty()) {
            return new ResponseDto<>(-1, "제품이름이 입력되지 않았습니다.", null);
        }
        Product sameProduct = productRepository.findByName(SameReqDto.getProductname());
        if (sameProduct != null) {
            return new ResponseDto<>(1, "동일한 제품이 존재합니다.", false);
        } else {
            return new ResponseDto<>(1, "해당 제품으로 등록이 가능합니다.", true);
        }
    }

}
