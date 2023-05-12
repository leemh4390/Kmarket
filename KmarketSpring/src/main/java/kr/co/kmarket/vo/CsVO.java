package kr.co.kmarket.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CsVO {

	private int no;
	private int parent;
	private int comment;
	private String cate1;
	private String cate2;
	private String title;
	private String content;
	private int file;
	private int hit;
	private String uid;
	private String regip;
	private String rdate;
	
	public String getRdate() {
		return rdate.substring(2, 10);
	}
	
	// 추가필드
	private int count;
	private String c1Name;
	private String c2Name;
	
}