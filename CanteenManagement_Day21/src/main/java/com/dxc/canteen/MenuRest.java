package com.dxc.canteen;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("menu")
public class MenuRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Menu[] showMenu() throws SQLException {
		Menu[] result = new MenuDAO().showMenu();
		return result;
	}
	
	@GET
	@Path("/{menuId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Menu searchMenu(@PathParam("menuId") int menuId) throws SQLException {
		Menu result = new MenuDAO().searchMenu(menuId);
		return result;
	}
	
	
	

}
