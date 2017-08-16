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

import com.alacriti.elm.resteasy.modelClasses.AddEmpInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveList;
import com.alacriti.elm.resteasy.modelClasses.RequestedEmployeeInfo;
import com.alacriti.elm.resteasy.resourceDeligate.DeligateAdmin;

@XmlRootElement
@Path("/admin")
public class AdminResource {
	DeligateAdmin deligateAdmin=new DeligateAdmin();
	SessionUtility sessionUtility=new SessionUtility();
	
		@GET
		@Path("/{designation}/{projectName}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<RequestedEmployeeInfo> getRequestedEmployeesList(@PathParam("projectName") String projectName,
				@PathParam("designation") String designation,@Context HttpServletRequest request)
		{
			
			boolean checkSessionValidity=sessionUtility.checkForSession(request);

			if(checkSessionValidity){
			List<RequestedEmployeeInfo> emp_list=null;
			emp_list=deligateAdmin.deligateRequestedEmployeeInfo(projectName,designation);
			return emp_list;
			}
			else
				return null;
		}

		@POST
		@Path("/accept/{designation}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public boolean acceptLeave(RequestedEmployeeInfo requestedEmployeeInfo,
				@PathParam("designation") String designation,@Context HttpServletRequest request)
		{
			System.out.println("in resource : -----> mail---->"+requestedEmployeeInfo.getEmail()+"<-----");
			System.out.println("in resource : -----> userName---->"+requestedEmployeeInfo.getFullName()+"<-----");

			//boolean flag=false;
			//ResponseToPost responseToPost=new ResponseToPost();
			//responseToPost.setDesignation(designation);
			//ResponseToAccept responseToAccept=null;
			boolean checkSessionValidity=sessionUtility.checkForSession(request);

			if(checkSessionValidity){
			return deligateAdmin.deligateAcceptLeave(requestedEmployeeInfo, designation);
			}
			else return false;
			 
		}
		@POST
		@Path("/reject/{designation}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public boolean rejectLeave(RequestedEmployeeInfo requestedEmployeeInfo,
				@PathParam("designation") String designation,@Context HttpServletRequest request)
		{
			boolean checkSessionValidity=sessionUtility.checkForSession(request);

			if(checkSessionValidity){
			//String emp_id=requestedEmployeeInfo.getEmp_id();
			return deligateAdmin.deligateRejectLeave(requestedEmployeeInfo, designation); 
			}
			else return false;
		}
		
		@GET
		@Path("/search/{empId}")
		@Produces(MediaType.APPLICATION_JSON)
		
		public EmployeeLeaveInfo getRequestedEmployeeInfoforSearch(@PathParam("empId") String emp_id,
				@Context HttpServletRequest request)
		{
			boolean checkSessionValidity=sessionUtility.checkForSession(request);

			if(checkSessionValidity){
			return deligateAdmin.deligateRequestedEmployeeInfoforSearch(emp_id);
			}
			else return null;
		}
		@GET
		@Path("search/leavelist/{emp_id}")
		@Produces(MediaType.APPLICATION_JSON)
		
		public List<EmployeeLeaveList> getRequestedEmployeeLeaveList(@PathParam("emp_id") String emp_id,
				@Context HttpServletRequest request)
		{
			boolean checkSessionValidity=sessionUtility.checkForSession(request);

			if(checkSessionValidity){
			return deligateAdmin.deligateRequestedEmployeeLeaveList(emp_id);
			}
			else return null;
		}
		@POST
		@Path("/addEmployee")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public boolean addEmpResource(AddEmpInfo addEmpInfo,
				@Context HttpServletRequest request)
		{	
			boolean checkSessionValidity=sessionUtility.checkForSession(request);

			if(checkSessionValidity){
			return deligateAdmin.deligateAddEmpInfo(addEmpInfo); 
			}
			else return false;
		}
	}	