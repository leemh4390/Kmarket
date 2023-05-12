package kr.co.kmarket.dao;

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

	public List<CsVO> selectNoticeArticles(int cate1, int start);
	
	public CsVO selectNoticeArticle(int no);
	
	public int selectCountNoticeTotal(int cate1);
	
	public List<Bd_Notice_CateVO> selectNoticeCate();
	
	public List<CsVO> selectFaqArticles(int cate1);
	
	public String selectCateName(int cate1);
	
	public List<Bd_Cate1VO> selectFaqCate();
	
	public List<Bd_Cate2VO> selectFaqCates(int cate1);
	
	
}