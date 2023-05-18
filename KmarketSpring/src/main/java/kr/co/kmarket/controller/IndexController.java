package kr.co.kmarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.co.kmarket.service.IndexService;
import kr.co.kmarket.vo.ProductVO;

@Controller
public class IndexController {
	
	@Autowired
	private IndexService service;
	
	@GetMapping(value = {"", "index"})
	public String index(Model model) {
		
		List<ProductVO> hits = service.selectIndexProductByHit();
		
		model.addAttribute("hits", hits);
		
		return "index";
	}
}
