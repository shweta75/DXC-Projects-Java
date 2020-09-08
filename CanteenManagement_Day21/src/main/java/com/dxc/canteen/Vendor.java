package com.dxc.canteen;

public class Vendor {

	private int venId;
	private String venName;
	private String venPhone;
	private String venUsername;
	private String venPass;
	private String venEmail;
	public int getVenId() {
		return venId;
	}
	public void setVenId(int venId) {
		this.venId = venId;
	}
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public String getVenPhone() {
		return venPhone;
	}
	public void setVenPhone(String venPhone) {
		this.venPhone = venPhone;
	}
	public String getVenUsername() {
		return venUsername;
	}
	public void setVenUsername(String venUsername) {
		this.venUsername = venUsername;
	}
	public String getVenPass() {
		return venPass;
	}
	public void setVenPass(String venPass) {
		this.venPass = venPass;
	}
	public String getVenEmail() {
		return venEmail;
	}
	public void setVenEmail(String venEmail) {
		this.venEmail = venEmail;
	}
	public Vendor(int venId, String venName, String venPhone, String venUsername, String venPass, String venEmail) {
		super();
		this.venId = venId;
		this.venName = venName;
		this.venPhone = venPhone;
		this.venUsername = venUsername;
		this.venPass = venPass;
		this.venEmail = venEmail;
	}
	public Vendor() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Vendor [venId=" + venId + ", venName=" + venName + ", venPhone=" + venPhone + ", venUsername="
				+ venUsername + ", venPass=" + venPass + ", venEmail=" + venEmail + "]";
	}
	
	
	
}
