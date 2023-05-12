package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kmarket.vo.Bd_Cate1VO;
import kr.co.kmarket.vo.Bd_Cate2VO;
import kr.co.kmarket.vo.Bd_Notice_CateVO;
import kr.co.kmarket.vo.CsVO;
import kr.co.kmarket.vo.NoticeVO;
import kr.co.kmarket.vo.QnaVO;

/*
 * 날짜 : 2023/04/29
 * 담당 : 이민혁
 * 내용 : admin  기능구현 dao 작성
 * 
 * */

@Mapper
@Repository
public interface AdminDAO {
	
	// faq 게시글 작성
	public void insertAdminFaq(CsVO vo);
	
	public void updateFaqCount(String cate1, String cate2);
	
	// notice 게시글 작성
	public void insertAdminNotice(NoticeVO vo);
	
	// qna 답변하기
	public void insertAdminQnaReply(QnaVO vo);
	
	public String selectAdminNoticeCate(String cate1);
	
	// faq 게시글 조회 & 페이징
	public List<CsVO> selectFaqLists(int start);
	
	// notice 게시글 조회 & 페이징
	public List<CsVO> selectNoticeLists(int start);
	
	// qna 게시글 조회 & 페이징
	public List<QnaVO> selectQnaLists(int start);
	
	// faq 게시글 갯수 total
	public int selectCountFaqTotal();
	
	// notice 게시글 갯수 total
	public int selectCountNoticeTotal();
	
	// qna 게시글 갯수 total
	public int selectCountQnaTotal();
	
	// faq 게시글 보기
	public CsVO selectFaqView(int no);
	
	// notice 게시글 보기
	public NoticeVO selectNoticeView(int no);
	
	// qna 게시글 보기 view
	public QnaVO selectQnaView(int no);
	
	// qna 게시글 답변 보기 
	public QnaVO selectQnaReplyView(int no);
	
	// qna 게시글 보기 reply
	public QnaVO selectQnaReply(int no);
	
	// faq 게시글 update
	public void updateFaqModify(CsVO vo);
	
	// notice 게시글 update 
	public void updateNoticeModify(NoticeVO vo);
	
	// qna 상태 status update
	public void updateQnaStatus(QnaVO vo);
	
	// faq 게시글 삭제하기
	public void deleteFaqArticle(@RequestParam("no") int no);
	
	// notice 게시글 삭제하기
	public void deleteNoticeArticle(@RequestParam("no") int no);
	
	// qna 게시글 삭제하기
	public void deleteQnaReply(int no);
	
	// qna 게시글 삭제하기
	public void deleteQnaReply2(int no);
	
	// qna status
	public int selectQnaStatus(int no);
	
	// faq 1차 카테고리 조회
	public List<Bd_Cate1VO> select_Bd_Cate1();
	
	// faq 1차 카테고리를 기반으로 2차 카테고리 검색 ajax
	public List<Bd_Cate2VO> select_Bd_Cate2(@RequestParam("cate1") int cate1);
	
	// notice 카테고리 검색
	public List<Bd_Notice_CateVO> select_Bd_Notice_Cate();
	
	// faq 카테고리를 기반으로 게시글 검색 
	public List<CsVO> selectArticleLists(@RequestParam("cate1") int cate1, @RequestParam("cate2") int cate2);
	
	// notice 카테고리를 기반으로 게시글 검색
	public List<NoticeVO> selectNoticeArticleLists(@RequestParam("cate1") String cate1);

}
