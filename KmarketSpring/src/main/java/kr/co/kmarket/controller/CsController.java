package kr.co.kmarket.controller;

/**
 * 날짜 : 2023/05/01 
 * 이름 : 이민혁
 * 내용 : Kmarket 고객센터 기능구현
 * 
 * */

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	public String CsIndex(Model model) {
		
		List<CsVO> qnaLists = service.selectIndexQnaLists();
		List<CsVO> noticeLists = service.selectIndexNoticeLists();
		
		model.addAttribute("qnaLists", qnaLists);
		model.addAttribute("noticeLists", noticeLists);
		
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
		List<Bd_Cate1VO> cate1s = service.selectCsCateExceptAll();
		List<CsVO> cate = service.selectFaqCate(cate1);
		
		model.addAttribute("articles", articles);
		model.addAttribute("name", name);
		model.addAttribute("cate", cate);
		model.addAttribute("cate1s", cate1s);
		
		return "cs/faq_list";
	}
	
	@GetMapping("cs/faq_view")
	public String FaqView(int no, int cate1, Model model) {
		
		CsVO article = service.selectFaqArticle(no);
		List<Bd_Cate1VO> cate1s = service.selectCsCateExceptAll();
		
		model.addAttribute("cate1", cate1);
		model.addAttribute("article", article);
		model.addAttribute("cate1s", cate1s);
		
		return "cs/faq_view";
	}
	
	@GetMapping("cs/qna_list")
	public String QnaArticleList(Model model, String pg, int cate1, Authentication auth) {
		
		if(auth != null && auth.isAuthenticated()) {
			String uid = auth.getName();
			
			model.addAttribute("uid", uid);
		}else {
			String anonymous = "anonymous";
			model.addAttribute("anonymous", anonymous);
		}
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountQnaTotal(cate1);
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		List<Bd_Cate1VO> cate1s = service.selectCsCate();
		
		List<CsVO> articles = service.selectQnaArticles(cate1, start);
		
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate1s", cate1s);
		model.addAttribute("articles", articles);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		return "cs/qna_list";
	}
	
	@GetMapping("cs/qna_view")
	public String QnaArticleView(Model model, int no, int cate1) {
		
		List<Bd_Cate1VO> cate1s = service.selectCsCate();
		CsVO article = service.selectQnaArticle(no);
		CsVO comment = service.selectQnaRply(no);
		
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate1s", cate1s);
		model.addAttribute("article", article);
		model.addAttribute("comment", comment);
		
		
		return "cs/qna_view";
	}
	
	@GetMapping("cs/qna_write")
	public String QnaWrite(Model model, int cate1) {
		
		List<Bd_Cate1VO> cate1s = service.selectCsCate();
		List<Bd_Cate2VO> cate2s = service.selectCsCates(cate1);
		
		model.addAttribute("cate1", cate1);
		model.addAttribute("cate1s", cate1s);
		model.addAttribute("cate2s", cate2s);
		
		return "cs/qna_write";
	}
	
	@PostMapping("cs/qna_write")
	public String QnaWrite(CsVO vo, HttpServletRequest req) {
		
		String regip = req.getRemoteAddr();
		
		String cate1 = vo.getCate1();
		
		vo.setRegip(regip);
		
		service.insertQna(vo);
		
		System.out.println("cate1 : "  + cate1);
		
		return "redirect:qna_list?cate1="+cate1;
	}
	
	@ResponseBody
	@GetMapping("cs/changeQnaCate")
	public Map<String, List<Bd_Cate2VO>> changeQnaCate(int cate1){
		
		List<Bd_Cate2VO> cate2 = service.selectCsCates(cate1);
		
		Map<String, List<Bd_Cate2VO>> map = new HashMap();
		
		map.put("result", cate2);
		
		return map;
	}
}