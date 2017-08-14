package com.alacriti.elm.resteasy.resource;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import com.alacriti.elm.resteasy.modelClasses.EmployeeProfile;
import com.alacriti.elm.resteasy.resourceDeligate.DeligateAdmin;

@XmlRootElement
@Path("/employee")
public class EmployeeResource {
	
	SessionUtility sessionUtility=new SessionUtility();
	DeligateAdmin deligateAdmin=new DeligateAdmin();

	@GET
	@Path("/get-profile/{emp_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeProfile getEmpResorce(@PathParam("emp_id") String emp_id,@Context HttpServletRequest request){
		

		//boolean checkSessionValidity=sessionUtility.checkForSession(request);
		
		//if(checkSessionValidity){

			return deligateAdmin.deligateGetEmpProfile(emp_id);
		//}
		//else return null;
		
	}
	
	
	@POST
	@Path("/edit-profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean getEmpResorce(EmployeeProfile employeeProfile,@Context HttpServletRequest request){
		

		boolean checkSessionValidity=sessionUtility.checkForSession(request);
		
		if(checkSessionValidity){

		System.out.println("returning edit result=======>"+employeeProfile.getEmp_id()+":;;;;"+employeeProfile.getDesignation());

		
			boolean fl= deligateAdmin.deligateEditProfile(employeeProfile);
			
			System.out.println("returning edit result=======>"+fl);
			
			
			return fl;
	}
		else return false;
		
	}

}
