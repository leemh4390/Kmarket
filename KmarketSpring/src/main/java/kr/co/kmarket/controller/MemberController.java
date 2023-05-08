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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.service.MemberService;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.TermsVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("member/login")
	public String login() {
		return "member/login";
	}
	
	@GetMapping("member/join")
	public String join() {
		return "member/join";
	}
	
	@GetMapping("/member/signup")
	public String signup(Model model, int type) {
		TermsVO vo = service.selectTerms(type);
		model.addAttribute("vo", vo);
		model.addAttribute("type", type);
		
		return "/member/signup";
	}	
	
	@GetMapping("member/register")
	public String register() {
		return "member/register";
	}
	
	@PostMapping("/member/register")
	public String register(MemberVO vo, HttpServletRequest req) {
		// Ip 설정
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		
		int result = service.insertMember(vo);
		return "redirect:/member/login?success"+result;
	}
	
	@GetMapping("/member/registerSeller")
	public String registerSeller() {
		return "/member/registerSeller";
	}
	
	@PostMapping("/member/registerSeller")
	public String registerSeller(MemberVO vo, HttpServletRequest req) {
		// Ip 설정
		String regip = req.getRemoteAddr();
		vo.setRegip(regip);
		
		int result = service.insertSeller(vo);
		return "redirect:/member/login?success"+result;
	}
	
	// ID 중복체크
	@ResponseBody
	@GetMapping("/member/checkUid")
	public Map<String, Integer> checkUid(String uid) {
		int result = service.countMember(uid);
		
		Map<String, Integer> map = new HashMap<>();
		map.put("result", result);
		
		return map;
	}

}
