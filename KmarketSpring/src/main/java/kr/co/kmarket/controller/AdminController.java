package kr.co.kmarket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.scheduler.VisitorCountScheduler;
import kr.co.kmarket.service.AdminService;
import kr.co.kmarket.vo.Bd_Cate1VO;
import kr.co.kmarket.vo.Bd_Cate2VO;
import kr.co.kmarket.vo.Bd_Notice_CateVO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.NoticeVO;
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.QnaVO;
import kr.co.kmarket.vo.VisitVO;

/*
 * 날짜 : 2023/04/28 
 * 담당 : 이민혁
 * 내용 : 관리자 컨트롤러...
 * 
 * */

@Controller
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@Autowired
	private VisitorCountScheduler visitor;
	
	// 관리자 index
	@GetMapping("admin")
	public String index (Model model) {
		
		OrderVO order = service.selectIndexCount();
		int registerCount = service.selectAdminIndexCount();
		int AdminIndexPostCount = service.selectAdminIndexPostCount();
		int selectAdminIndexDepositWaiting = service.selectAdminIndexDepositWaiting();
		VisitVO selectKmarketVisitor = service.selectKmarketVisitor();
		List<CsVO> selectAdminNoticeLimit5 = service.selectAdminNoticeLimit5();
		List<CsVO> selectAdminFaqLimit5 = service.selectAdminFaqLimit5();
		
		model.addAttribute("order", order);
		model.addAttribute("registerCount", registerCount);
		model.addAttribute("postCount", AdminIndexPostCount);
		model.addAttribute("visitor", selectKmarketVisitor);
		model.addAttribute("waiting", selectAdminIndexDepositWaiting);
		model.addAttribute("notice", selectAdminNoticeLimit5);
		model.addAttribute("faq", selectAdminFaqLimit5);
		
		return "admin/index";
	}
	
	@ResponseBody
	@GetMapping("admin/visitKmarketCount")
	public void visitKmarketCount(@RequestParam("hitCt") int hitCt) {
		visitor.setNowVisitorCount(hitCt);
	}
	
	// 관리자 faq 목록
	@GetMapping("admin/faq_list")
	public String faq_list(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountFaqTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<CsVO> lists = service.selectFaqLists(start);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		model.addAttribute("lists", lists);
		
		List<Bd_Cate1VO> cate1s = service.select_BD_cate1();
		
		model.addAttribute("cate1s",cate1s);
		
		return "admin/cs/faq_list";
	}
	
	// notice 게시글 조회 및 페이징 카테고리
	@GetMapping("admin/notice_list")
	public String notice_list(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountNoticeTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<CsVO> lists = service.selectNoticeLists(start);
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		model.addAttribute("lists", lists);
		
		List<Bd_Notice_CateVO> cate1s = service.select_Bd_Notice_Cate();
		
		model.addAttribute("cate1s", cate1s);
		
		return "admin/cs/notice_list";
	}
	
	// qna 게시글 조회 및 페이징 카테고리
	@GetMapping("admin/qna_list")
	public String qna_list(Model model, String pg) {
		
		int currentPage = service.getCurrentPage(pg);
		int start = service.getLimitStart(currentPage);
		
		int total = service.selectCountQnaTotal();
		int lastPageNum = service.getLastPageNum(total);
		int pageStartNum = service.getPageStartNum(total, start);
		int groups[] = service.getPageGroup(currentPage, lastPageNum);
		
		List<QnaVO> lists = service.selectQnaLists(start);
		
		model.addAttribute("articles", lists);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("pageStartNum", pageStartNum);
		model.addAttribute("groups", groups);
		
		List<Bd_Cate1VO> cate1s = service.select_BD_cate1();
		
		model.addAttribute("cate1s",cate1s);
		
		return "admin/cs/qna_list";
	}
	
	
	@ResponseBody
	@GetMapping("admin/select_Bd_Cate2")
	public Map<String, List<Bd_Cate2VO>> select_Bd_Cate2(int cate1) {
		
		List<Bd_Cate2VO> cate2s = service.select_Bd_Cate2(cate1);
		
		Map<String, List<Bd_Cate2VO>> map = new HashMap<>();
		
		map.put("result", cate2s);
		
		return map;
	}
	
	@ResponseBody
	@GetMapping("admin/selectArticleLists")
	public Map<String, List<CsVO>> selectArticleLists(int cate1, int cate2) {
		
		List<CsVO> lists = service.selectArticleLists(cate1, cate2);
		
		Map<String, List<CsVO>> map = new HashMap<>();
		
		map.put("result", lists);
		
		return map;
	}
	
	@ResponseBody
	@GetMapping("admin/selectNoticeArticleLists")
	public Map<String, List<NoticeVO>> selectNoticeArticleLists(String cate1) {
		
		List<NoticeVO> lists = service.selectNoticeArticleLists(cate1);
		
		Map<String, List<NoticeVO>> map = new HashMap<>();
		
		map.put("result", lists);
		
		return map;
	}
	
	@ResponseBody
	@GetMapping("admin/FaqDeleteArticles")
	public Map<String, Integer> FaqDeleteArticles(@RequestParam("arr") List<Integer> arr) {

		for(int no : arr) {
			service.deleteFaqArticle(no);
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("result", arr.size());
		
		//System.out.println(arr.size());
		
		return map;
	}
	
	@ResponseBody
	@GetMapping("admin/NoticeDeleteArticles")
	public Map<String, Integer> NoticeDeleteArticles(@RequestParam("arr") List<Integer> arr) {

		for(int no : arr) {
			service.deleteNoticeArticle(no);
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("result", arr.size());
		
		
		/*for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}*/
		
		return map;
	}
	
	@ResponseBody
	@GetMapping("admin/deleteQnaArticle")
	public Map<String, Integer> QnaDeleteArticles(@RequestParam("arr") List<Integer> arr) {

		for(int no : arr) {
			service.deleteQnaReply(no);
		}
		
		Map<String, Integer> map = new HashMap<>();
		
		map.put("result", arr.size());
		
		
		/*for(int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}*/
		
		return map;
	}
	
	
	// 관리자 faq 게시글 작성하기 화면구현
	@GetMapping("admin/faq_write")
	public String faq_write(Model model) {
		
		List<Bd_Cate1VO> cate1s = service.select_BD_cate1();
		
		model.addAttribute("cate1s",cate1s);
		
		
		return "admin/cs/faq_write";
	}
	
	// 관리자 faq 작성하기 기능구현
	@PostMapping("admin/faq_write")
	public String faq_write(CsVO vo, String cate1, String cate2) {
		
		service.insertAdminFaq(vo);
		
		service.updateFaqCount(cate1, cate2);
		
		return "redirect:/admin/faq_list";
	}
	
	// 관리자 notice 게시글 작성하기 화면구현
	@GetMapping("admin/notice_write")
	public String notice_write(Model model) {
		
		List<Bd_Notice_CateVO> cate1s = service.select_Bd_Notice_Cate();
		
		model.addAttribute("cate1s", cate1s);
		
		return "admin/cs/notice_write";
	}
	
	// 관리자 notice 게시글 작성하기 기능구현
	@PostMapping("admin/notice_write")
	public String notice_write(NoticeVO vo, String cate1) {
		
		service.insertAdminNotice(vo, cate1);
		
		return "redirect:/admin/notice_list";
	}
	
	// 관리자 faq 보기 view
	@GetMapping("admin/faq_view")
	public String faq_view(int no, Model model) {
		
		CsVO view = service.selectFaqView(no);
		
		model.addAttribute("view", view);
		
		return "admin/cs/faq_view";
	}
	
	// 관리자 notice 보기 view
	@GetMapping("admin/notice_view")
	public String notice_view(int no, Model model) {
		
		NoticeVO view = service.selectNoticeView(no);
		
		model.addAttribute("view", view);
		
		return "admin/cs/notice_view";
	}
	
	@GetMapping("admin/qna_view")
	public String qna_view(int no, Model model) {
		
		QnaVO view = service.selectQnaView(no);
		
		QnaVO reply = service.selectQnaReplyView(no);
		
		model.addAttribute("view", view);
		
		model.addAttribute("reply", reply);
		
		return "admin/cs/qna_view";
	}
	
	@GetMapping("admin/qna_reply")
	public String qna_reply(int no, Model model) {
		
		QnaVO reply = service.selectQnaReply(no);
		
		model.addAttribute("reply", reply);
		
		return "admin/cs/qna_reply";
	}
	
	@PostMapping("admin/qna_reply")
	public String qna_reply(QnaVO vo, int no) {
		
		System.out.println(no);
		
		service.insertAdminQnaReply(vo);
		
		service.updateQnaStatus(vo);
		
		return "redirect:/admin/qna_list";
	}
	
	
	
	
	@GetMapping("admin/faq_modify")
	public String faq_modify(Model model, int no) {
	
		CsVO view = service.selectFaqView(no);
		
		model.addAttribute("view", view);
		
		return "admin/cs/faq_modify";
	}
	
	@GetMapping("admin/notice_modify")
	public String notice_modify(Model model, int no) {
	
		NoticeVO view = service.selectNoticeView(no);
		
		List<Bd_Notice_CateVO> cate1s = service.select_Bd_Notice_Cate();
		
		model.addAttribute("view", view);
		
		model.addAttribute("cate1s", cate1s);
		
		return "admin/cs/notice_modify";
	}
	
	// 관리자 faq 수정하기 기능구현
	@PostMapping("admin/faq_modify")
	public String faq_modify(CsVO vo) {
		
		service.updateFaqModify(vo);
		
		return "redirect:/admin/faq_list";
	}
	
	// 관리자 notice 수정하기 기능구현
	@PostMapping("admin/notice_modify")
	public String notice_modify(NoticeVO vo, String cate1) {
		
		service.updateNoticeModify(vo, cate1);
		
		return "redirect:/admin/notice_list";
	}
	
	@GetMapping("admin/faq_delete")
	public String faq_delete(int no) {
		
		service.deleteFaqArticle(no);
		
		return "redirect:/admin/faq_list";
	}
	
	@GetMapping("admin/notice_delete")
	public String notice_delete(int no) {
		
		service.deleteNoticeArticle(no);
		
		return "redirect:/admin/notice_list";
	}
	
	
	@GetMapping("admin/qna_delete")
	public String qna_delete(int no) {
		
		int status = service.selectQnaStatus(no);
		
		if(status == 0) {
			service.deleteQnaReply(no);
		}else {
			service.deleteQnaReply(no);
			service.deleteQnaReply2(no);
		}
		
		return "redirect:/admin/qna_list";
	}
	
	@ResponseBody
	@GetMapping("/visitCount")
	public String visitCount() {
		
		
		
		return "";
	};
	

}
