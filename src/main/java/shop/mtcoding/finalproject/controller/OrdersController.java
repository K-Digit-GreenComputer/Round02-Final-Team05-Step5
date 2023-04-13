package shop.mtcoding.finalproject.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.finalproject.dto.orders.BuyAllListDto;
import shop.mtcoding.finalproject.dto.orders.BuyDto;
import shop.mtcoding.finalproject.dto.orders.BuyListDto;
import shop.mtcoding.finalproject.model.orders.OrdersRepository;
import shop.mtcoding.finalproject.model.product.Product;
import shop.mtcoding.finalproject.model.product.ProductRepository;
import shop.mtcoding.finalproject.model.user.User;

@RequiredArgsConstructor
@Controller
public class OrdersController {

    private final HttpSession session;
	private final OrdersRepository ordersRepository;
	private final ProductRepository productRepository;

    @GetMapping("/orders/ordersList" )
    public String orderlist(Model model) {
        User principal = (User) session.getAttribute("principal");
        List<BuyListDto> orderlist= ordersRepository.findAll(principal.getId());
		model.addAttribute("orderslist",orderlist);
        return "orders/ordersList"; 
    }

    @GetMapping("/orders/ordersAllList" )
    public String orderAlllist(Model model) {
        User principal = (User) session.getAttribute("principal");
		List<BuyAllListDto> orderAlllist = ordersRepository.findAllList();
		if (principal.getRole().equals("admin")) {
		model.addAttribute("orderslist",orderAlllist);
        return "orders/userOrdersList"; 
		}
		return "redirect:/";
    }

    @PostMapping("/orders/{productId}")
	public String buyOrders(@PathVariable Integer productId, BuyDto buyDto) {
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			return "redirect:/loginForm";
		}
		
		// 상품수량 - 구매수량 < 0
		Product productPS = productRepository.findById(productId);
		if (productPS.getQty() - buyDto.getOrdersQty() < 0) {
			return "redirect:/product/{productId}";
		} 

		productRepository.productQtyUpdate(buyDto);
		ordersRepository.insert(buyDto.toModel(principal.getId()));
		// 상품 구매 -> 구매목록 페이지로
		return "redirect:/orders/ordersList";
	}

    @PostMapping("/orders/{ordersId}/cancle")
	public String cancleOrders(@PathVariable Integer ordersId, BuyDto buyDto) {
		
		User principal = (User)session.getAttribute("principal");
		if (principal==null) {
			return "redirect:/loginForm";
		}

		productRepository.canclePurchase(buyDto);
		ordersRepository.delete(buyDto.getOrdersId());
		return "redirect:/";
	} 
}

