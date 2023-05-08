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
	
	public List<ProductVO> selectProductArticles(int cate1, int cate2){
		return dao.selectProductArticles(cate1, cate2);
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
	
	public OrderVO selectOrdComplete(int ordNo, String uid) {
		return dao.selectOrdComplete(ordNo, uid);
	}
	
	public List<Order_ItemVO> selectOrdCompleteList(int ordNo){
		return dao.selectOrdCompleteList(ordNo);
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
	
	// 주문하기 포인트
	public void insertPoint(OrderVO vo) {
		dao.insertPoint(vo);
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
	
	public void insertOrderItemDirect(OrderVO vo) {
		dao.insertOrderItemDirect(vo);
	}
	
	public void deleteOrderToComplete(int no, List<Integer> proNo) {
		dao.deleteOrderToComplete(no, proNo);
	};
}
