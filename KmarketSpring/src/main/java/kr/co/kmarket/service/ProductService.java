package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kmarket.dao.ProductDAO;
import kr.co.kmarket.vo.CartVO;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.OrderVO;
import kr.co.kmarket.vo.Order_ItemVO;
import kr.co.kmarket.vo.ProductVO;

@Service
public class ProductService {

	@Autowired
	private ProductDAO dao;
	
	public MemberVO selectMember(String uid) {
		return dao.selectMember(uid);
	}
	
	public List<ProductVO> selectProductArticles(){
		return dao.selectProductArticles();
	}
	
	public ProductVO selectProductArticle(int proNo) {
		return dao.selectProductArticle(proNo);
	}
	
	public List<Order_ItemVO> selectOrderItems(){
		return dao.selectOrderItems();
	}
	
	public List<OrderVO> selectOrderProducts(String uid, int no){
		return dao.selectOrderProducts(uid, no);
	}
	
	// 장바구니 추가
	public int addCart(CartVO cart) {
		return dao.addCart(cart);
	}
	
	// 장바구니 목록
	public List<CartVO> selectCarts(String uid){
		return dao.selectCarts(uid);
	}
	
	// 장바구니 삭제
	public void deleteCart(@RequestParam int no, String uid) {
		dao.deleteCart(no, uid);
	}
	
	// 주문하기 추가
	public int insertOrder(OrderVO vo) {
		dao.insertOrder(vo);
		return vo.getOrdNo();
	}
	
	public int updateOrder(OrderVO vo) {
		return dao.updateOrder(vo);
	}
	

	
	public void deleteOrder(String uid) {
		dao.deleteOrder(uid);
	}
	
	public void insertOrderItem(Order_ItemVO item) {
		dao.insertOrderItem(item);
	}
	
	public void deleteOrderToComplete(int no, List<Integer> proNo) {
		dao.deleteOrderToComplete(no, proNo);
	};
}
