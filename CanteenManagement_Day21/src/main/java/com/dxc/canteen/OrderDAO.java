package com.dxc.canteen;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class OrderDAO {
	
	
	public String approveDeny(int ordId, int venId, String status, String ordComment) throws SQLException {
		
		Order order = searchOrder(ordId);
		Vendor vendor = new VendorDAO().searchVendor(venId);
		
		
		Connection connection = ConnectionHelper.getConnection();
		if(vendor.getVenId()==order.getVenId()) {
			
			
			if(status.toUpperCase().equals("YES") ) {
			
				OrderStatus os = OrderStatus.ACCEPTED;

				status="ACCEPTED".trim();
					
				String cmd = "update orders set ORD_STATUS=? ,ORD_COMMENTS=? where ORD_ID=?";
				PreparedStatement pst = connection.prepareStatement(cmd);
				pst.setString(1, os.toString());
				pst.setString(2, ordComment);
				pst.setInt(3, ordId);
				
				pst.executeUpdate();
				
				
				
//				cmd = "select MEN_PRICE from Menu where MEN_ID=? ";
//				pst = connection.prepareStatement(cmd);
//				pst.setInt(1, order.ge);
//				ResultSet rs = pst.executeQuery();
//				rs.next();
//				int price = rs.getInt("MEN_PRICE");
//				
				cmd = "select ORD_BILLAMOUNT from Orders where ORD_ID=?";
				pst = connection.prepareStatement(cmd);
				pst.setInt(1, ordId);
				ResultSet rs= pst.executeQuery();
				rs.next();
				double billAmt = rs.getDouble("ORD_BILLAMOUNT");
				
				
				cmd = "select CUS_ID from Orders where ORD_ID=?";
				pst = connection.prepareStatement(cmd);
				pst.setInt(1, ordId);
				rs= pst.executeQuery();
				rs.next();
				int cusId = rs.getInt("CUS_ID");
				
				WalletSource ws = WalletSource.valueOf(order.getWalSource());
				cmd="update wallet set WAL_AMOUNT=WAL_AMOUNT-? where CUS_ID=? AND WAL_SOURCE=?";
				pst = connection.prepareStatement(cmd);
				pst.setDouble(1, billAmt);	
				pst.setInt(2,cusId);
				pst.setString(3, ws.toString());
				
				pst.executeUpdate();
				
				
				return "Order Approved...";
				
			}else if(status.toUpperCase().equals("NO") ){
				OrderStatus os = OrderStatus.DENIED;

				status="DENIED";
				String cmd = "update orders set ORD_STATUS=? ,ORD_COMMENTS=? where ORD_ID=?";
				PreparedStatement pst = connection.prepareStatement(cmd);
				pst.setString(1, os.toString());
				pst.setString(2, ordComment);
				pst.setInt(3, ordId);
				return "Order Denied...";
			
			}
		}
			
		return "Unauthorized Vendor...";
		
	}
	
	
	
	public String placeOrder(int custId, int venId,int menuId,String walSource, Date ordDate, int ordQty, String ordcmt) throws SQLException {
		
		
		Connection connection = ConnectionHelper.getConnection();
		ResultSet rs;
		String result = ""; 
		PreparedStatement pst = null;
		
		OrderStatus os = OrderStatus.ACCEPTED;
		
		int ordId = generateOrderId();
		
		String cmd = "select MEN_PRICE from Menu where MEN_ID=? ";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, menuId);
		rs = pst.executeQuery();
		rs.next();
		int price = rs.getInt("MEN_PRICE");
		
		double bill = price*ordQty;
		
		cmd = "select WAL_AMOUNT from wallet where CUS_ID=? AND WAL_SOURCE=?";
		pst = connection.prepareStatement(cmd);
		pst.setInt(1, custId);
		pst.setString(2, walSource);
		rs = pst.executeQuery();
		rs.next();
		int walletAmt = rs.getInt("WAL_AMOUNT");
		System.out.println(walletAmt);
		
		if(bill>walletAmt) {
			return "Insufficient balance in wallet...";
		}else if(bill<walletAmt) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String oDate = sdf.format(ordDate);
			 
			cmd = "insert into orders(CUS_ID,VEN_ID,WAL_SOURCE,MEN_ID,ORD_DATE,ORD_QUANTITY,ORD_BILLAMOUNT,ORD_STATUS,ORD_COMMENTS,ORD_ID) "
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			pst = connection.prepareStatement(cmd);
			pst.setInt(1, custId);
			pst.setInt(2, venId);
			//String wSource = "DEBIT_CARD";
			
			pst.setString(3, walSource);
			pst.setInt(4, menuId);
			pst.setString(5,  oDate);
			pst.setInt(6, ordQty);
			pst.setDouble(7, bill);
			
			String ostatus="PENDING";
			pst.setString(8, ostatus);
			pst.setString(9, ordcmt);
			pst.setInt(10, ordId);
			pst.executeUpdate();
			
			 }
		
		
		
		return "Order Placed...";
		}
	
	
	public Order searchOrder(int ordId) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		String cmd = "Select * from Orders WHERE ORD_ID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		
		pst.setInt(1, ordId);
		ResultSet rs = pst.executeQuery();
		Order order = null;
		
		if (rs.next()) {
			
			
			order = new Order();
			
			order.setOrdId(rs.getInt("ORD_ID"));
			order.setBillAmt(rs.getInt("ORD_BILLAMOUNT"));
			order.setOrdComments(rs.getString("ORD_COMMENTS"));
			order.setOrdDate(rs.getDate("ORD_DATE"));
			order.setOrdQty(rs.getInt("ORD_QUANTITY"));
			OrderStatus os = OrderStatus.valueOf(rs.getString("ORD_STATUS"));
			order.setOrdStatus(os);
			
			order.setWalSource(rs.getString("WAL_SOURCE"));
			order.setVenId(rs.getInt("VEN_ID"));
			
			}
		return order;
	}

				
	
	public Order[] orderHistory(int ordId) throws SQLException {
		Connection con = ConnectionHelper.getConnection();
		
		String cmd = "SELECT count(*) cnt FROM Orders WHERE ORD_ID=?";
		PreparedStatement pst = con.prepareStatement(cmd);
		pst.setInt(1, ordId);
		
		ResultSet rs = pst.executeQuery();
		rs.next();
		int cnt = rs.getInt("cnt");
		System.out.println(cnt);
		
		Order[] orders = new Order[cnt];
		cmd = "select * from Orders WHERE ORD_ID=?";
		pst = con.prepareStatement(cmd);
		pst.setInt(1, ordId);
		rs=pst.executeQuery();
		Order order = null;
				
		int i=0;
		
		while (rs.next()) {
			order = new Order();
			order.setOrdId(rs.getInt("ORD_ID"));
			order.setBillAmt(rs.getInt("ORD_BILLAMOUNT"));
			order.setOrdComments(rs.getString("ORD_COMMENTS"));
			order.setOrdDate(rs.getDate("ORD_DATE"));
			order.setOrdQty(rs.getInt("ORD_QUANTITY"));
			OrderStatus os = OrderStatus.valueOf(rs.getString("ORD_STATUS"));
			order.setOrdStatus(os);
			order.setWalSource(rs.getString("WAL_SOURCE"));
			
			orders[i]=order;
			i++;
		}
		return orders;
	}
	
	
	public int generateOrderId() throws SQLException {
		int ordid=0;
		Connection connection = ConnectionHelper.getConnection();
		PreparedStatement pst = null;
		pst=connection.prepareStatement("SELECT CASE WHEN MAX(ORD_ID) IS NULL THEN 1 " + 
				"   ELSE MAX(ORD_ID)+1 END ordid FROM Orders");
		ResultSet rs = pst.executeQuery();
		rs.next();
		ordid = rs.getInt("ordid");
		return ordid;
	} 
	

}

