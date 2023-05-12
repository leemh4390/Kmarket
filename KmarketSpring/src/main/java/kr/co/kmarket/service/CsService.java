package kr.co.kmarket.service;

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
	
	public List<CsVO> selectNoticeArticles (int cate1, int start){
		return dao.selectNoticeArticles(cate1, start);
	}
	
	public CsVO selectNoticeArticle(int no) {
		return dao.selectNoticeArticle(no);
	}
	
	
	public int selectCountNoticeTotal(int cate1) {
		return dao.selectCountNoticeTotal(cate1);
	};
	
	public List<Bd_Notice_CateVO> selectNoticeCate(){
		return dao.selectNoticeCate();
	};
	
	public List<CsVO> selectFaqArticles(int cate1){
		return dao.selectFaqArticles(cate1);
	}
	
	public String selectCateName(int cate1) {
		return dao.selectCateName(cate1);
	};

	public List<Bd_Cate1VO> selectFaqCate(){
		return dao.selectFaqCate();
	};
	
	public List<Bd_Cate2VO> selectFaqCates(int cate1){
		return dao.selectFaqCates(cate1);
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