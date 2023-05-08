package kr.co.kmarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kmarket.dao.MemberDAO;
import kr.co.kmarket.repo.MemberRepo;
import kr.co.kmarket.vo.MemberVO;
import kr.co.kmarket.vo.TermsVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	@Autowired
	private MemberRepo repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 일반 회원가입
	public int insertMember(MemberVO vo) {
		// 비밀번호 암호화 설정
		vo.setPass(passwordEncoder.encode(vo.getPass2()));
		
		int result = dao.insertMember(vo);
		return result;
	}
	
	// 판매자 회원가입
	public int insertSeller(MemberVO vo) {
		vo.setPass(passwordEncoder.encode(vo.getPass2()));
		
		int result = dao.insertSeller(vo);
		return result;
	}
	
	// Id 중복체크
	public int countMember(String uid) {
		return repo.countByuid(uid);
	}
	
	// 약관
	public TermsVO selectTerms(int type) {
		if(type == 1) {
			return dao.selectTerms();
		}else {
			return dao.selectTermsSeller();
		}
	}
	
	//회원정보 가져오기
	public MemberVO selectUser(String uid) {
		return dao.selectUser(uid);
	};
}
