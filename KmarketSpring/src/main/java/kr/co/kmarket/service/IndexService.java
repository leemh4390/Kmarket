package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kmarket.dao.IndexDAO;
import kr.co.kmarket.vo.ProductVO;

@Service
public class IndexService {
	
	@Autowired
	private IndexDAO dao;
	
	public List<ProductVO> selectIndexProductByHit(){
		return dao.selectIndexProductByHit();
	};

}
