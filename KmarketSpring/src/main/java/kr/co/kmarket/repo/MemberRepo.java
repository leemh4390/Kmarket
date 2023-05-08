package kr.co.kmarket.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.kmarket.entity.MemberEntity;

public interface MemberRepo extends JpaRepository<MemberEntity, String> {
	
	public int countByuid(String uid);

}