package kr.co.kmarket.dao;

/**
 * 날짜 : 2023/05/01 
 * 이름 : 이민혁
 * 내용 : Kmarket 고객센터 기능구현
 * 
 * */

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.Bd_Cate1VO;
import kr.co.kmarket.vo.Bd_Cate2VO;
import kr.co.kmarket.vo.Bd_Notice_CateVO;
import kr.co.kmarket.vo.CsVO;

@Mapper
@Repository
public interface CsDAO {
	
	// Qna 게시글 작성하기
	public void insertQna(CsVO vo);
	
	// Notice 게시글
	public List<CsVO> selectNoticeArticles(int cate1, int start);
	
	// Faq 게시글
	public List<CsVO> selectFaqArticles(int cate1);
	
	// Faq 게시글 갯수
	public List<CsVO> selectFaqCate(int cate1);
	
	// Qna 게시글
	public List<CsVO> selectQnaArticles(int cate1, int start);
	
	// Notice 게시글 보기
	public CsVO selectNoticeArticle(int no);
	
	// Faq 게시글 보기
	public CsVO selectFaqArticle(int no);
	
	// Qna 게시글 보기
	public CsVO selectQnaArticle(int no);
	
	// Qna 게시글 일부 보기
	public List<CsVO> selectIndexQnaLists();
	
	// Qna 게시글 일부 보기
	public List<CsVO> selectIndexNoticeLists();
	
	// Qna Reply
	public CsVO selectQnaRply(int no);
	
	// Notice 게시글 total
	public int selectCountNoticeTotal(int cate1);
	
	// Qna 게시글 total
	public int selectCountQnaTotal(int cate1);
	
	// Notice 카테고리
	public List<Bd_Notice_CateVO> selectNoticeCate();
	
	// Notice 카테고리 이름
	public String selectCateName(int cate1);
	
	// Cs 1차 카테고리
	public List<Bd_Cate1VO> selectCsCate();
	
	// Cs 2차 카테고리
	public List<Bd_Cate2VO> selectCsCates(int cate1);
	
	// CS all 제외
	public List<Bd_Cate1VO> selectCsCateExceptAll();
	
}