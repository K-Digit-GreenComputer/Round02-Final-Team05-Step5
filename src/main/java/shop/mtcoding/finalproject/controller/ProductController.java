package shop.mtcoding.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.finalproject.dto.ResponseDto;
import shop.mtcoding.finalproject.dto.product.ProductReqDto.ProductAddReqDto;
import shop.mtcoding.finalproject.dto.product.ProductReqDto.productSameReqDto;
import shop.mtcoding.finalproject.handler.ex.CustomApiException;
import shop.mtcoding.finalproject.model.product.Product;
import shop.mtcoding.finalproject.model.product.ProductRepository;
import shop.mtcoding.finalproject.model.user.User;

@RequiredArgsConstructor
@Controller
public class ProductController {

    
    private final ProductRepository productRepository;
    private final HttpSession session;

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
    public @ResponseBody ResponseDto<?> add(@RequestBody ProductAddReqDto productAddReqDto) {
        if (productAddReqDto.getProductname() == null || productAddReqDto.getProductname().isEmpty()) {
            throw new CustomApiException("productname을 작성해주세요");
            // return new ResponseDto<>(-1, "username이 입력되지 않았습니다.", null);
        }
        if (productAddReqDto.getQty() == null) {
            throw new CustomApiException("재고를 입력해주세요");
            // return new ResponseDto<>(-1, "username이 입력되지 않았습니다.", null);
        }
        if (productAddReqDto.getPrice() == null ) {
            throw new CustomApiException("가격을 입력해주세요");
            // return new ResponseDto<>(-1, "username이 입력되지 않았습니다.", null);
        }
       
        int result = productRepository.insert(productAddReqDto);
        if (result == 1) {
            return new ResponseDto<>(1, "제품등록 성공", null);
        } else {
            return new ResponseDto<>(-1, "제품등록 실패", null);
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
        User principal = (User)session.getAttribute("principal");
        if (principal.getRole().equals("admin")) {
            return "product/updateForm";
        } else {
            return "redirect:/";
        }
       
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
    public @ResponseBody ResponseDto<?> check(String productname, productSameReqDto SameReqDto) {
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
