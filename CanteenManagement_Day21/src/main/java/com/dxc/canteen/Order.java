package com.dxc.canteen;

import java.util.Date;

public class Order {
	
	String walSource;
	Date ordDate;
	int ordQty;
	int billAmt;
	String ordComments;
	int ordId;
	
	private int cusId;
	private int venId;
	
	private OrderStatus ordStatus;
	
	
	
	public OrderStatus getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(OrderStatus ordStatus) {
		this.ordStatus = ordStatus;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getVenId() {
		return venId;
	}
	public void setVenId(int venId) {
		this.venId = venId;
	}
	public String getWalSource() {
		return walSource;
	}
	public void setWalSource(String walSource) {
		this.walSource = walSource;
	}
	public Date getOrdDate() {
		return ordDate;
	}
	public void setOrdDate(Date ordDate) {
		this.ordDate = ordDate;
	}
	public int getOrdQty() {
		return ordQty;
	}
	public void setOrdQty(int ordQty) {
		this.ordQty = ordQty;
	}
	public int getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(int billAmt) {
		this.billAmt = billAmt;
	}
	
	
	public String getOrdComments() {
		return ordComments;
	}
	public void setOrdComments(String ordComments) {
		this.ordComments = ordComments;
	}
	public int getOrdId() {
		return ordId;
	}
	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}
	
	
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String walSource, Date ordDate, int ordQty, int billAmt, String ordComments, int ordId, int cusId,
			int venId, OrderStatus ordStatus) {
		super();
		this.walSource = walSource;
		this.ordDate = ordDate;
		this.ordQty = ordQty;
		this.billAmt = billAmt;
		this.ordComments = ordComments;
		this.ordId = ordId;
		this.cusId = cusId;
		this.venId = venId;
		this.ordStatus = ordStatus;
	}
	@Override
	public String toString() {
		return "Order [walSource=" + walSource + ", ordDate=" + ordDate + ", ordQty=" + ordQty + ", billAmt=" + billAmt
				+ ", ordComments=" + ordComments + ", ordId=" + ordId + ", cusId=" + cusId + ", venId=" + venId
				+ ", ordStatus=" + ordStatus + "]";
	}
	
	

	
	
	
}
