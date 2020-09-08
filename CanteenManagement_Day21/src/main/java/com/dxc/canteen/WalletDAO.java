package com.dxc.canteen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletDAO {
	
	public Wallet searchWallet(int walId) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		
		String cmd = "Select * from Wallet WHERE WAL_ID=?";
		
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1, walId);
		ResultSet rs = pst.executeQuery();
		
		Wallet wallet = null;
		if (rs.next()) {
			wallet = new Wallet();
			
			wallet.setCusId(rs.getInt("CUS_ID"));
			wallet.setWalId(rs.getInt("WAL_ID"));
			wallet.setWalAmt(rs.getInt("WAL_AMOUNT"));
			wallet.setWalSource(rs.getNString("WAL_SOURCE"));
			
						
		}
		return wallet;
	}
	
//	public Wallet[] showWalletWal() throws SQLException {
//		Connection con = ConnectionHelper.getConnection();
//		PreparedStatement pst=con.prepareStatement("Select * from Wallet WHERE WAL_ID=?");
//		ResultSet rs=pst.executeQuery();
//		rs.next(); 
//		int cnt=rs.getInt("cnt"); 
//		Menu[] arr=new Menu[cnt]; 
//		pst=con.prepareStatement("select * from Menu"); 
//		rs=pst.executeQuery();
//		int i=0;
//		Menu e=null;
//		while(rs.next()) {
//			e=new Menu();
//			e.setMenId(rs.getInt("MEN_ID"));
//			e.setMenItem(rs.getString("MEN_ITEM"));
//			e.setMenPrice(rs.getInt("MEN_PRICE"));
//			e.setMenCalories(rs.getInt("MEN_CALORIES"));
//			e.setMenSpeciality(rs.getString("MEN_SPECIALITY"));
//			arr[i]=e;
//			i++;
//		}
//		return arr;
//
//	}


}
