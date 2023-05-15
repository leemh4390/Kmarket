package kr.co.kmarket.service;

/**
 * 날짜 : 2023/05/01 
 * 이름 : 이민혁
 * 내용 : Kmarket 고객센터 기능구현
 * 
 * */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.CsDAO;
import kr.co.kmarket.vo.Bd_Cate1VO;
import kr.co.kmarket.vo.Bd_Cate2VO;
import kr.co.kmarket.vo.Bd_Notice_CateVO;
import kr.co.kmarket.vo.CsVO;

@Service
public class CsService {

	@Autowired
	private CsDAO dao;
	
	// Qna 게시글 작성하기
	public void insertQna(CsVO vo) {
		dao.insertQna(vo);
	};
	
	// Notice 게시글 목록
	public List<CsVO> selectNoticeArticles (int cate1, int start){
		return dao.selectNoticeArticles(cate1, start);
	}
	
	// Qna 게시글 목록
	public List<CsVO> selectQnaArticles(int cate1, int start){
		return dao.selectQnaArticles(cate1, start);
	}
	
	// Faq 게시글 목록
	public List<CsVO> selectFaqArticles(int cate1){
		return dao.selectFaqArticles(cate1);
	}
	
	// Faq 게시글 갯수
	public List<CsVO> selectFaqCate(int cate1) {
		return dao.selectFaqCate(cate1);
	};
	
	// Notice 게시글 보기
	public CsVO selectNoticeArticle(int no) {
		return dao.selectNoticeArticle(no);
	}
	
	// Faq 게시글 보기
	public CsVO selectFaqArticle(int no) {
		return dao.selectFaqArticle(no);
	};
	
	// Qna 게시글 보기
	public CsVO selectQnaArticle(int no) {
		return dao.selectQnaArticle(no);
	};
	
	// Qna 게시글 일부 보기
	public List<CsVO> selectIndexQnaLists() {
		return dao.selectIndexQnaLists();
	};
	
	// Qna 게시글 일부 보기
	public List<CsVO> selectIndexNoticeLists() {
		return dao.selectIndexNoticeLists();
	};
	
	// Qna 답변 보기
	public CsVO selectQnaRply(int no) {
		return dao.selectQnaRply(no);
	}
	
	// Notice 게시글 total
	public int selectCountNoticeTotal(int cate1) {
		return dao.selectCountNoticeTotal(cate1);
	};
	
	// Qna 게시글 total
	public int selectCountQnaTotal(int cate1) {
		return dao.selectCountQnaTotal(cate1);
	};
	
	// Notice 카테고리
	public List<Bd_Notice_CateVO> selectNoticeCate(){
		return dao.selectNoticeCate();
	};
	
	// Notice 카테고리 
	public String selectCateName(int cate1) {
		return dao.selectCateName(cate1);
	};
	
	// CS 카테고리
	public List<Bd_Cate1VO> selectCsCate(){
		return dao.selectCsCate();
	};
	
	// CS 카테고리
	public List<Bd_Cate2VO> selectCsCates(int cate1){
		return dao.selectCsCates(cate1);
	};
	
	// CS 카테고리
	public List<Bd_Cate1VO> selectCsCateExceptAll(){
		return dao.selectCsCateExceptAll();
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
		
		return groups;
	}
}