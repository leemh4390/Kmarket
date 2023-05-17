package kr.co.kmarket.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.ProductVO;

@Mapper
@Repository
public interface IndexDAO {
	
	public List<ProductVO> selectIndexProductByHit();

}
