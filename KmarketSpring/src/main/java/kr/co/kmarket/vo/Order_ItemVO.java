package kr.co.kmarket.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order_ItemVO {
	
	private int ordNo;
	private int proNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	
	// 추가 필드
	private String proName;
	private String descript;
	private String thumb1;
	
}
