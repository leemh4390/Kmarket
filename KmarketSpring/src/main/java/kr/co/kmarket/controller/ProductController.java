package kr.co.kmarket.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.ProductService;
import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.Order_ItemVO;
import kr.co.kmarket.vo.ProductVO;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("product/list")
	public String list(Model model, int cate1, int cate2) {
		
		List<ProductVO> articles = service.selectProductArticles(cate1, cate2);
		
		model.addAttribute("articles", articles);
		
		return "product/list";
	}
	
	@GetMapping("product/view")
	public String view(int proNo, Model model, Authentication authentication) {
		
		ProductVO article = service.selectProductArticle(proNo);
		
		model.addAttribute("article", article);
		
		int price = article.getPrice();
		
		int discount = article.getDiscount();
		
		int newPrice = price * (100 - discount) / 100;
		
		double roundPrice = Math.ceil(newPrice/10.0) * 10;
		
		int intRoundPrice = (int) roundPrice;
		
		model.addAttribute("roundPrice", intRoundPrice);
		
		String uid = authentication.getName();
		
		model.addAttribute("uid", uid);
		
		return "product/view";
	}
	
	@PostMapping("product/cart/add")
	@ResponseBody
	public String addCart(CartVO cart) {
		
		System.out.println("addCar : " + cart);
		//장바구니 추가
		int result = service.addCart(cart);
		return result+"";
	}
	
	@GetMapping("product/cart")
	public String cart(Authentication authentication, Model model) {
		
		String uid = authentication.getName();
		
		List<CartVO> carts = service.selectCarts(uid);
		
		model.addAttribute("carts",carts);
		
		return "product/cart";
	}
	

	
	@ResponseBody
	@GetMapping("product/cart/delete")
	public Map<String, Integer> DeleteCart(@RequestParam("arr") List<Integer> arr, Authentication authentication) {
		
		String uid = authentication.getName();
		
		for(int no : arr) {
			service.deleteCart(no, uid);
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("result", arr.size());
		
		System.out.println("진입 확인");
		
		return map;
	}
	
	
	@GetMapping("product/order")
	public String order(Authentication authentication, Model model, MemberVO vo, int no) {
		
		String uid = authentication.getName();
		MemberVO member = service.selectMember(uid);
		
		List<OrderVO> products = service.selectOrderProducts(uid, no);
		
		model.addAttribute("member", member);
		model.addAttribute("products", products);
		model.addAttribute("no", no);
		
		return "product/order";
		
	}
	
	@ResponseBody
	@PostMapping("product/order/add")
	public int order(Authentication authentication,@RequestBody OrderVO vo) {
		
		
		
		String uid = authentication.getName();
		
		vo.setUid(uid);
		
		int ordPayment = vo.getOrdPayment();
		
		if(ordPayment == 4) {
			vo.setOrdComplete(0);
		}else{
			vo.setOrdComplete(1);
		}
		
		int point = vo.getUsedPoint();
		
		if(point > 0) {
			service.insertPoint(vo);
		}
		
		int no = vo.getNo();
		
		int result = service.updateOrder(vo);
		
		List<Integer> proNo = vo.getArr();
		
		service.deleteOrderToComplete(no, proNo);
		
		service.deleteOrder(uid);
		
		return result;
			
	}
	
	@ResponseBody
	@PostMapping("product/cart")
	public int cart(@RequestBody OrderVO vo, Authentication authentication) {
		
		String uid = authentication.getName();
		vo.setUid(uid);
		
		int ordNo = service.insertOrder(vo);
		List<Order_ItemVO> items = vo.getItems();
		
		for(Order_ItemVO item : items) {
			item.setOrdNo(ordNo);
			service.insertOrderItem(item);
		}
		
		return ordNo;
	}
	
	@ResponseBody
	@PostMapping("product/cart/direct")
	public int directOrder(OrderVO vo) {
		
		int ordNo = service.insertOrder(vo);
		
		vo.setOrdNo(ordNo);
		
		service.insertOrderItemDirect(vo);
		
		System.out.println("진입확인");
		
		return ordNo;
	}
	
	
	@GetMapping("product/complete")
	public String complete(Model model, Authentication authentication, int ordNo) {
		
		String uid = authentication.getName();
		
		OrderVO complete = service.selectOrdComplete(ordNo, uid);
		List<Order_ItemVO> lists = service.selectOrdCompleteList(ordNo);
		
		model.addAttribute("complete", complete);
		model.addAttribute("lists", lists);
		
		return "product/complete";
	}
}
