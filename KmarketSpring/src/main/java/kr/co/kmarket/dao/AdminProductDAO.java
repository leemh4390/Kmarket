package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kmarket.vo.ProductVO;
import kr.co.kmarket.vo.Product_Cate1VO;
import kr.co.kmarket.vo.Product_Cate2VO;

/*
 * 날짜 : 2023/05/01
 * 담당 : 이민혁
 * 내용 : 관리자 상품 기능구현
 * 
 * */

@Mapper
@Repository
public interface AdminProductDAO {
	
	// admin product reigster 기능구현
	public void insertAdminProductRegister(ProductVO vo);
	
	// admin product register 1차 cate
	public List<Product_Cate1VO> selectProductCate1();
	
	// admin product register 2차 cate
	public List<Product_Cate2VO> selectProductCate2(@RequestParam("cate1") String cate1);
	
	// admin product list
	public List<ProductVO> selectProductArticles(int start);
	
	// admin product list total
	public int selectCountTotal();
	
	// admin product delete
	public void deleteAdminProductArticles(@RequestParam("proNo") int proNo);

}
