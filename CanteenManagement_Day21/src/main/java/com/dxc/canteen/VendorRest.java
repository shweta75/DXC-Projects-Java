package com.dxc.canteen;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("vendor")
public class VendorRest {
	
	
	@GET
	@Path("showVendor")
	@Produces(MediaType.APPLICATION_JSON)
	public Vendor[] showVendor() throws SQLException {
		Vendor[] result = new VendorDAO().showVendor();
		return result;
	}
	
	
	@GET
	@Path("search/{vendorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Vendor searchVendor(@PathParam("vendorId") int vendorId) throws SQLException {
		Vendor result = new VendorDAO().searchVendor(vendorId);
		return result;
	}

	
	@GET
	@Path("validate/{user}/{passcode}")
	@Produces(MediaType.APPLICATION_JSON)
	public int validate(@PathParam("user") String user, @PathParam("passcode") String passcode) throws SQLException {
		VendorDAO dao = new VendorDAO();
		return dao.authenticate(user, passcode);
		
	}
	
}
