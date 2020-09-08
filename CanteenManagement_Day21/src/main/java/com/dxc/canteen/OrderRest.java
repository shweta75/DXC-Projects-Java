package com.dxc.canteen;

import java.sql.Date;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("order")
public class OrderRest {

	@POST
	@Path("place/{custId}/{venId}/{menuId}/{walSource}/{ordDate}/{ordQty}/{ordcmt}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String place(@PathParam("custId") int custId, @PathParam("venId") int venId, @PathParam("menuId") int menuId, 
			@PathParam("walSource") String walSource, @PathParam("ordDate") Date ordDate, @PathParam("ordQty") int ordQty, 
			@PathParam("ordcmt") String ordcmt) throws SQLException {
		OrderDAO dao = new OrderDAO();
		return dao.placeOrder(custId, venId, menuId, walSource, ordDate, ordQty, ordcmt);
		
	
	}
	
	@GET
	@Path("/{ordId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Order[] orderHistory(@PathParam("ordId") int ordId) throws SQLException {
		
		Order[] result = new OrderDAO().orderHistory(ordId);
		return result;
	}
	
	
	@POST
	@Path("{ORD_ID }/{VEN_ID}/{status}/{ordComment}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String approvedeny(@PathParam("ORD_ID") int ORD_ID, @PathParam("VEN_ID") int VEN_ID, 
			@PathParam("status") String status, @PathParam("ordComment") String ordComment) throws SQLException {
		OrderDAO dao = new OrderDAO();
		return dao.approveDeny(ORD_ID, VEN_ID, status, ordComment);
		
	//	1/1/YES/GoAhead
	
	}
	
	
	@GET
	@Path("search/{ordId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Order searchOrder(@PathParam("ordId") int ordId) throws SQLException {
		Order result = new OrderDAO().searchOrder(ordId);
		return result;
	}


}
