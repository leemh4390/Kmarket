package kr.co.kmarket.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderVO {
	
	private int ordNo;
	private String uid;
	private int ordCount;
	private int ordPrice;
	private int ordDiscount;
	private int ordDelivery;
	private int savePoint;
	private int usedPoint;
	private int ordTotPrice;
	private String recipName;
	private String recipHp;
	private String recipZip;
	private String recipAddr1;
	private String recipAddr2;
	private int ordPayment;
	private int ordComplete;
	private String ordDate;

	// 추가필드
	private List<Order_ItemVO> items;
	private List<Integer> arr;
	private int proNo;
	private int count;
	private int price;
	private int discount;
	private int point;
	private int delivery;
	private int total;
	
	private String proName;
	private String descript;
	private String thumb1;
	private int no;
}
