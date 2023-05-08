package kr.co.kmarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.AdminProductService;
import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_Cate1VO;
import kr.co.kmarket.vo.Product_Cate2VO;
/*
 * 날짜 : 2023/05/01
 * 담당 : 이민혁
 * 내용 : 관리자 상품 컨트롤러
 * 
 * */

@Controller
public class AdminProductController {
	
	@Autowired
	private AdminProductService service;
	
	@GetMapping("admin/product/list")
	public String list(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<ProductVO> articles = service.selectProductArticles(start);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		model.addAttribute("articles", articles);
		
		return "admin/product/list";
	}
	
	@GetMapping("admin/product/register")
	public String register(Model model) {
		
		List<Product_Cate1VO> cate1s = service.selectProductCate1();
		
		model.addAttribute("cate1s", cate1s);
		
		return "admin/product/register";
	}
	
	@PostMapping("admin/product/register")
	public String register(ProductVO vo, HttpServletRequest req) {
		
		String ip = req.getRemoteAddr();
		
		vo.setIp(ip);
		
		service.insertAdminProductRegister(vo);
		
		return "redirect:list";
	}
	
	// admin product register 2차 카테고리 출력..
	@ResponseBody
	@GetMapping("admin/selectCate2")
	public Map<String, List<Product_Cate2VO>> selectCate2(String cate1) {
		
		//System.out.println("1차 카테고리 value : " + cate1);
		
		List<Product_Cate2VO> cate2s = service.selectProductCate2(cate1);
		
		Map<String, List<Product_Cate2VO>> map = new HashMap<>();
		
		map.put("result", cate2s);
		
		return map;
	}
	
	
	@ResponseBody
	@GetMapping("admin/product/AdminProductDeleteArticles")
	public Map<String, Integer> AdminProductDeleteArticles(@RequestParam("arr") List<Integer> arr) {
		
		for(int proNo : arr) {
			service.deleteAdminProductArticles(proNo);
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("result", arr.size());
		
		return map;
	}
}
