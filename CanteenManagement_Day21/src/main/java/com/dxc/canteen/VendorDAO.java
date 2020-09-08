package com.dxc.canteen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorDAO {
	
	
	public Vendor searchVendor(int vendorId) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		
		String cmd = "Select * from Vendor WHERE VEN_ID=?";
		
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1, vendorId);
		ResultSet rs = pst.executeQuery();
		
		Vendor vendor  = null;
		if (rs.next()) {
			
			
			vendor = new Vendor();
			
			
			vendor.setVenId(rs.getInt("VEN_ID"));
			vendor.setVenName(rs.getString("VEN_NAME"));
			vendor.setVenPhone(rs.getString("VEN_PHN_NO"));
			vendor.setVenUsername(rs.getString("VEN_USERNAME"));
			vendor.setVenPass(rs.getString("VEN_PASSWORD"));
			vendor.setVenEmail(rs.getString("VEN_EMAIL"));
									
		}
		return vendor;
	}


	
	public Vendor[] showVendor() throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		PreparedStatement pst=con.prepareStatement("select count(*) cnt from Vendor");
		ResultSet rs=pst.executeQuery();
		rs.next(); 
		int cnt=rs.getInt("cnt"); 
		Vendor[] arr=new Vendor[cnt]; 
		pst=con.prepareStatement("select * from Vendor"); 
		rs=pst.executeQuery();
		int i=0;
		Vendor e=null;
		
		while(rs.next()) {
			
			e=new Vendor();
			
			e.setVenId(rs.getInt("VEN_ID"));
			e.setVenName(rs.getString("VEN_NAME"));
			e.setVenPhone(rs.getString("VEN_PHN_NO"));
			e.setVenUsername(rs.getString("VEN_USERNAME"));
			e.setVenPass(rs.getString("VEN_PASSWORD"));
			e.setVenEmail(rs.getString("VEN_EMAIL"));
			
			arr[i]=e;
			i++;
		}
		return arr;

	}

	
	
	
	public int authenticate(String user, String password) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt from vendor where VEN_USERNAME=? "
				+ " AND VEN_PASSWORD=?";
		
		PreparedStatement  pst = con.prepareStatement(cmd);
		pst.setString(1, user);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		int cnt = rs.getInt("cnt");
		return cnt;
		
	}


}
