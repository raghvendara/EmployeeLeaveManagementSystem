package com.alacriti.elm.resteasy.resource;

import java.util.List;

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

import com.alacriti.elm.resteasy.modelClasses.AddEmpInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveList;
import com.alacriti.elm.resteasy.modelClasses.RequestedEmployeeInfo;
import com.alacriti.elm.resteasy.resourceDeligate.DeligateAdmin;
import com.alacriti.elm.utilities.SessionUtility;

@XmlRootElement
@Path("/admin")
public class AdminResource {
	public static final Logger log= Logger.getLogger(AdminResource.class);

	DeligateAdmin deligateAdmin=new DeligateAdmin();
	SessionUtility sessionUtility=new SessionUtility();
	
		@GET
		@Path("/{designation}/{projectName}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<RequestedEmployeeInfo> getRequestedEmployeesList(@PathParam("projectName") String projectName,
				@PathParam("designation") String designation,@Context HttpServletRequest request)
		{
			log.debug("in getRequestedEmployeesList");
			//HttpSession session = request.getSession(false);
			
				return deligateAdmin.deligateRequestedEmployeeInfo(projectName,designation);
			//else return null;
		}

		@POST
		@Path("/accept/{designation}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public boolean acceptLeave(RequestedEmployeeInfo requestedEmployeeInfo,
				@PathParam("designation") String designation,@Context HttpServletRequest request)
		{
			log.debug("in acceptLeave");

			return deligateAdmin.deligateAcceptLeave(requestedEmployeeInfo, designation);

		}
		@POST
		@Path("/reject/{designation}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public boolean rejectLeave(RequestedEmployeeInfo requestedEmployeeInfo,
				@PathParam("designation") String designation,@Context HttpServletRequest request)
		{
			log.debug("in rejectLeave");

			return deligateAdmin.deligateRejectLeave(requestedEmployeeInfo, designation); 
		}
		
		@GET
		@Path("/search/{empId}")
		@Produces(MediaType.APPLICATION_JSON)
		
		public List<EmployeeLeaveInfo> getRequestedEmployeeInfoforSearch(@PathParam("empId") String emp_id,
				@Context HttpServletRequest request)
		{
			log.debug("in getRequestedEmployeeInfoforSearch");

			return deligateAdmin.deligateRequestedEmployeeInfoforSearch(emp_id);
		}
		@GET
		@Path("search/leavelist/{emp_id}")
		@Produces(MediaType.APPLICATION_JSON)
		
		public List<EmployeeLeaveList> getRequestedEmployeeLeaveList(@PathParam("emp_id") String emp_id)
		{
			log.debug("in getRequestedEmployeeLeaveList");

			return deligateAdmin.deligateRequestedEmployeeLeaveList(emp_id);
		}
		@POST
		@Path("/addEmployee")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public boolean addEmpResource(AddEmpInfo addEmpInfo,
				@Context HttpServletRequest request)
		{	
			log.debug("in addEmpResource");

			return deligateAdmin.deligateAddEmpInfo(addEmpInfo); 
		}
		@POST
		@Consumes(MediaType.TEXT_PLAIN)
		@Path("/addEmployee/validation")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean validationForEmpIDResource(String empID)
		{	
			log.debug("in validationForEmpIDResource");
			return deligateAdmin.deligateEmpIDForValidation(empID); 
		}
		@POST
		@Consumes(MediaType.TEXT_PLAIN)
		@Path("/addEmployee/validate-userName")
		@Produces(MediaType.TEXT_PLAIN)
		public boolean validationForUserNameResource(String userName)
		{	
			log.debug("in validationForUserNameResource");
			return deligateAdmin.deligateUserNameForValidation(userName); 
		}
	}	