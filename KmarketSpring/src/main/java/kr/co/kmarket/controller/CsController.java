package kr.co.kmarket.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.kmarket.service.CsService;
import kr.co.kmarket.vo.Bd_Cate1VO;
import kr.co.kmarket.vo.Bd_Cate2VO;
import kr.co.kmarket.vo.Bd_Notice_CateVO;
import kr.co.kmarket.vo.CsVO;

/*
 * 담당 : 이민혁
 * 내용기록
 * 2023-05-08
 * 고객센터 기능구현 작업완료
*/
@Controller
@MapperScan("kr.co.kmarket.dao")
public class CsController {

	@Autowired
	private CsService service;
	
	@GetMapping(value = {"cs" , "cs/index"} )
	public String CsIndex() {
		return "cs/index";
	}
	
	@GetMapping("cs/notice_list")
	public String NoticeArticles(Model model, String pg, int cate1) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountNoticeTotal(cate1);
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<CsVO> articles = service.selectNoticeArticles(cate1, start);
		List<Bd_Notice_CateVO> cate1s = service.selectNoticeCate();
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		model.addAttribute("articles", articles);
		model.addAttribute("cate1s", cate1s);
		model.addAttribute("cate1", cate1);
		
		return "cs/notice_list";
	}
	
	@GetMapping("cs/notice/view")
	public String NoticeArticle(int no, Model model) {
		
		CsVO article = service.selectNoticeArticle(no);
		
		model.addAttribute("article", article);
		
		return "cs/notice_view";
	}
	
	
	@GetMapping("cs/faq_list")
	public String FaqArticles(Model model, String pg, int cate1) {
		
		List<CsVO> articles = service.selectFaqArticles(cate1);
		String name = service.selectCateName(cate1);
		List<Bd_Cate1VO> cate1s = service.selectFaqCate();
		List<Bd_Cate2VO> cate2s = service.selectFaqCates(cate1);
		
		
		model.addAttribute("articles", articles);
		model.addAttribute("name", name);
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate1s", cate1s);
		model.addAttribute("cate2s", cate2s);
		
		return "cs/faq_list";
	}
}