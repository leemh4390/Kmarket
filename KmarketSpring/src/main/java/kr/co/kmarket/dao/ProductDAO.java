package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.Order_ItemVO;
import kr.co.kmarket.vo.ProductVO;

@Mapper
@Repository
public interface ProductDAO {
	
	// member 정보 조회
	public MemberVO selectMember(String uid);
	
	// 상품 list 조회
	public List<ProductVO> selectProductArticles();
	
	// 상품 view
	public ProductVO selectProductArticle(int proNo);
	
	// order 조회
	public List<OrderVO> selectOrderProducts(String uid, int no);
	
	// 장바구니 추가
	public int addCart(CartVO cart);
	
	// 목록
	public List<CartVO> selectCarts(String uid);
	
	// 장바구니 삭제
	public void deleteCart(@RequestParam("no") int no, String uid);
	
	// 주문하기 추가
	public int insertOrder(OrderVO vo);
	
	// 주문하기 상품 추가
	public void insertOrderItem(@RequestBody Order_ItemVO item);
	
	// 주문하기
	public int updateOrder(OrderVO vo);
	
	// 주문하기 후 장바구니 삭제
	public void deleteOrder(String uid);
	
	// 주문 후 선택안한 상품 삭제
	public void deleteOrderToComplete(int no, List<Integer> proNo);
	
	public List<Order_ItemVO> selectOrderItems();
	
	

}
