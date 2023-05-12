package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kmarket.dao.AdminDAO;
import kr.co.kmarket.vo.Bd_Cate1VO;
import kr.co.kmarket.vo.Bd_Cate2VO;
import kr.co.kmarket.vo.Bd_Notice_CateVO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.NoticeVO;
import kr.co.kmarket.vo.QnaVO;

@Service
public class AdminService {
	
	@Autowired
	private AdminDAO dao;
	
	// faq 게시글 작성
	public void insertAdminFaq(CsVO vo) {
		dao.insertAdminFaq(vo);
	}
	
	public void updateFaqCount(String cate1, String cate2) {
		dao.updateFaqCount(cate1, cate2);
	};
	
	// notice 게시글 작성
	public void insertAdminNotice(NoticeVO vo, String cate1) {
		
		String cate2 = dao.selectAdminNoticeCate(cate1);
		
		System.out.println(cate1 + " ," + cate2 );
		
		vo.setCate2(cate2);
		
		dao.insertAdminNotice(vo);
	}

	// qna 답뱐하기
	public void insertAdminQnaReply(QnaVO vo) {
		dao.insertAdminQnaReply(vo);
	}
	
	public List<CsVO> selectFaqLists(int start){
		return dao.selectFaqLists(start);
	}
	
	public List<CsVO> selectNoticeLists(int start){
		return dao.selectNoticeLists(start);
	} 
	
	public List<QnaVO> selectQnaLists(int start){
		return dao.selectQnaLists(start);
	}
	
	public int selectCountFaqTotal() {
		return dao.selectCountFaqTotal();
	};
	
	public int selectCountNoticeTotal() {
		return dao.selectCountNoticeTotal();
	}
	
	public int selectCountQnaTotal() {
		return dao.selectCountQnaTotal();
	};
	
	// faq 게시글 보기
	public CsVO selectFaqView(int no){
		return dao.selectFaqView(no);
	}
	
	// notice 게시글 보기
	public NoticeVO selectNoticeView(int no) {
		return dao.selectNoticeView(no);
	}
	
	// qna 게시글 보기 view
	public QnaVO selectQnaView(int no) {
		return dao.selectQnaView(no);
	}	
	
	// qna 게시글 답변보기
	public QnaVO selectQnaReplyView(int no) {
		return dao.selectQnaReplyView(no);
	}
	
	// qna 게시글 보기 reply
	public QnaVO selectQnaReply(int no) {
		return dao.selectQnaReply(no);
	}
	
	// fqa 게시글 업데이트
	public void updateFaqModify(CsVO vo) {
		
		System.out.println("진입..?");
		
		dao.updateFaqModify(vo);
	}
	
	// notice update
	public void updateNoticeModify(NoticeVO vo, String cate1) {
		
		String cate2 = dao.selectAdminNoticeCate(cate1);
		
		vo.setCate2(cate2);
		
		System.out.println("진입 확인...1");
		
		dao.updateNoticeModify(vo);
	}
	
	// qna 상태 status 업데이트
	public void updateQnaStatus(QnaVO vo) {
		dao.updateQnaStatus(vo);
	}
	
	public void deleteFaqArticle(int no) {
		dao.deleteFaqArticle(no);
	}
	
	public void deleteNoticeArticle(int no) {
		dao.deleteNoticeArticle(no);
	}
	
	// qna 게시글 삭제하기
	public void deleteQnaReply(int no) {
		System.out.println("진입 확인...1");
		dao.deleteQnaReply(no);
	}
	
	// qna 게시글 삭제하기
	public void deleteQnaReply2(int no) {
		System.out.println("진입 확인...2");
		dao.deleteQnaReply2(no);
	}
	
	// qna status
	public int selectQnaStatus(int no) {
		return dao.selectQnaStatus(no);
	}
	
	public List<Bd_Cate1VO> select_BD_cate1(){
		return dao.select_Bd_Cate1();
	};
	
	public List<Bd_Cate2VO> select_Bd_Cate2(int cate1){
		return dao.select_Bd_Cate2(cate1);
	}
	
	// notice 카테고리 검색
	public List<Bd_Notice_CateVO> select_Bd_Notice_Cate(){
		return dao.select_Bd_Notice_Cate();
	}
	
	// faq 카테고리를 기반으로 게시글 검색
	public List<CsVO> selectArticleLists(int cate1, int cate2){
		return dao.selectArticleLists(cate1, cate2);
	}
	
	// notice 카테고리를 기반으로 게시글 검색
	public List<NoticeVO> selectNoticeArticleLists(String cate1){
		return dao.selectNoticeArticleLists(cate1);
	};
	
	// 현재 페이지 번호
	public int getCurrentPage(String pg) {
		int currentPage = 1;
		
		if(pg != null) {
			currentPage = Integer.parseInt(pg);
		}
		return currentPage;
	}
	
	// 페이지 시작값
	public int getLimitStart(int currentPage) {
		return (currentPage - 1) * 10;
	}
	
	// 마지막 페이지 번호
	public int getLastPageNum(int total) {
		int lastPageNum = 0;
		
		if(total % 10 == 0) {
			lastPageNum = total / 10;
		}else {
			lastPageNum = total / 10 + 1;
		}
		return lastPageNum;
	}
	
	// 페이지 시작 번호
	public int getPageStartNum(int total, int start) {
		return total - start;
	}
	
	// 페이지 그룹
	public int[] getPageGroup(int currentPage, int lastPageNum) {
		
		int groupCurrent = (int) Math.ceil(currentPage / 10.0);
		int groupStart = (groupCurrent - 1) * 10 + 1;
		int groupEnd = groupCurrent * 10;
		
		if(groupEnd > lastPageNum) {
			groupEnd = lastPageNum;
		}
		
		int[] groups = {groupStart, groupEnd};
		
		/*System.out.println("groupCurrent : " + groupCurrent );
		System.out.println("groupStart : " + groupStart);
		System.out.println("groupEnd : " + groupEnd);
		System.out.println("groups[0] : " + groups[0]);
		System.out.println("groups[1] : " + groups[1]);*/
		
		return groups;
	}

}
