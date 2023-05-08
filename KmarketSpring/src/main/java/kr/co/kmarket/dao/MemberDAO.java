package kr.co.kmarket.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.TermsVO;

@Mapper
@Repository
public interface MemberDAO {
	
	// 회원가입
	public int insertMember(MemberVO vo);
	public int insertSeller(MemberVO vo);
	
	// 약관
	public TermsVO selectTerms();
	public TermsVO selectTermsSeller();
	
	//회원정보 가져오기
	public MemberVO selectUser(String uid);

}
