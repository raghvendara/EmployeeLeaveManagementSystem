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

import org.apache.log4j.Logger;

import com.alacriti.elm.resteasy.modelClasses.EmployeeProfile;
import com.alacriti.elm.resteasy.resourceDeligate.DeligateAdmin;
import com.alacriti.elm.utilities.SessionUtility;

@XmlRootElement
@Path("/employee")
public class EmployeeResource {
	public static final Logger log= Logger.getLogger(EmployeeResource.class);
	
	SessionUtility sessionUtility=new SessionUtility();
	DeligateAdmin deligateAdmin=new DeligateAdmin();

	@GET
	@Path("/get-profile/{emp_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeProfile getEmpResorce(@PathParam("emp_id") String emp_id,@Context HttpServletRequest request){
		
		log.debug("in getEmpResorce");
	
		return deligateAdmin.deligateGetEmpProfile(emp_id);
		
	}
	
	
	@POST
	@Path("/edit-profile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean editProfileResorce(EmployeeProfile employeeProfile,@Context HttpServletRequest request){
		
		log.debug("in editProfileResorce");
		
		return deligateAdmin.deligateEditProfile(employeeProfile);				
	}

}
