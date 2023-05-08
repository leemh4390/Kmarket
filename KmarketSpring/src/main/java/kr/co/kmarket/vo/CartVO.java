package kr.co.kmarket.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartVO {

	private int cartNo;
	private String uid;
	private int proNo;
	private int count;
	private int price;
	private int discount;
	private int disPrice;
	private int point;
	private int delivery;
	private int total;
	private String rdate;
	
	//추가
	private String proName;
	private String descript;
	private String thumb1;
	private int sum;
	private int multiply;
	private int countTotal;
	
}