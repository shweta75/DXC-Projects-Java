package com.dxc.canteen;

public class Wallet {

	
	private int cusId;
	private int walId;
	private int walAmt;
	String walSource;
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public int getWalId() {
		return walId;
	}
	public void setWalId(int walId) {
		this.walId = walId;
	}
	public int getWalAmt() {
		return walAmt;
	}
	public void setWalAmt(int walAmt) {
		this.walAmt = walAmt;
	}
	public String getWalSource() {
		return walSource;
	}
	public void setWalSource(String walSource) {
		this.walSource = walSource;
	}
	public Wallet(int cusId, int walId, int walAmt, String walSource) {
		super();
		this.cusId = cusId;
		this.walId = walId;
		this.walAmt = walAmt;
		this.walSource = walSource;
	}
	public Wallet() {
	
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Wallet [cusId=" + cusId + ", walId=" + walId + ", walAmt=" + walAmt + ", walSource=" + walSource + "]";
	}
	
	
	
	
}
