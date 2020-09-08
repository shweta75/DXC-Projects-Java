package com.dxc.canteen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	
	
	public Customer searchCustomer(int custId) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		
		String cmd = "Select * from Customer WHERE CUS_ID=?";
		
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1, custId);
		ResultSet rs = pst.executeQuery();
		
		Customer customer = null;
		if (rs.next()) {
			
			
			customer = new Customer();
			customer.setCusId(rs.getInt("CUS_ID"));
			customer.setCusName(rs.getString("CUS_NAME"));
			customer.setCusPhno(rs.getString("CUS_PHN_NO"));
			customer.setCusUserName(rs.getString("CUS_USERNAME"));
			customer.setCusPassword(rs.getString("CUS_PASSWORD"));
			customer.setCusEmail(rs.getString("CUS_EMAIL"));
						
		}
		return customer;
	}
	

	
	public Customer[] showCustomer() throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		PreparedStatement pst=con.prepareStatement("select count(*) cnt from Customer");
		ResultSet rs=pst.executeQuery();
		rs.next(); 
		int cnt=rs.getInt("cnt"); 
		Customer[] arr=new Customer[cnt]; 
		pst=con.prepareStatement("select * from Customer"); 
		rs=pst.executeQuery();
		int i=0;
		Customer e=null;
		
		while(rs.next()) {
			
			e=new Customer();
			e.setCusId(rs.getInt("CUS_ID"));
			e.setCusName(rs.getString("CUS_NAME"));
			e.setCusPhno(rs.getString("CUS_PHN_NO"));
			e.setCusUserName(rs.getString("CUS_USERNAME"));
			e.setCusPassword(rs.getString("CUS_PASSWORD"));
			e.setCusEmail(rs.getString("CUS_EMAIL"));
			
			arr[i]=e;
			i++;
		}
		return arr;

	}
	

	public int authenticate(String user, String password) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt from Customer where CUS_USERNAME=? "
				+ " AND CUS_PASSWORD=?";
		PreparedStatement  pst = con.prepareStatement(cmd);
		pst.setString(1, user);
		pst.setString(2, password);
		ResultSet rs = pst.executeQuery();
		rs.next();
		
		int cnt = rs.getInt("cnt");
		return cnt;
		
	}
}
