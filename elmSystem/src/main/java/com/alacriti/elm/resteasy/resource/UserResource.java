package com.alacriti.elm.resteasy.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;

import com.alacriti.elm.resteasy.modelClasses.EmployeeLeaveInfo;
import com.alacriti.elm.resteasy.modelClasses.ForgotPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.LeaveApplicationDetails;
import com.alacriti.elm.resteasy.modelClasses.NewPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.ResponseToForgotPassword;
import com.alacriti.elm.resteasy.modelClasses.ResponseToLoginPost;
import com.alacriti.elm.resteasy.modelClasses.UserLoginInfo;
import com.alacriti.elm.resteasy.resourceDeligate.DeligateLogin;
import com.alacriti.elm.resteasy.resourceDeligate.UserDeligate;
import com.alacriti.elm.utilities.SessionUtility;

@XmlRootElement
@Path("/login")
public class UserResource {
	public static final Logger log= Logger.getLogger(UserResource.class);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseToLoginPost loginValidation(UserLoginInfo userLoginInfo
			,@Context HttpServletRequest request){
		log.debug("in loginValidation");
		DeligateLogin deligateLogin=new DeligateLogin();
		ResponseToLoginPost responseToPost= deligateLogin.deligateLoginValidate(userLoginInfo);
		if(responseToPost.isFlag())
		{
			System.out.println("creating session for login");
			HttpSession session = request.getSession();
			session.setAttribute("name", responseToPost.getFullName());
			session.setAttribute("emp_id", responseToPost.getEmp_id());
			session.setAttribute("designation", responseToPost.getDesignation());
			System.out.println("this how session looks like :"+session);
			System.out.println("created session for login");

		}
		return responseToPost;
	}
	@GET
	@Path("/checkSession")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean checkSessoin(@Context HttpServletRequest request)
	{
		log.debug("in checkSessoin");
		SessionUtility sessionUtility=new SessionUtility();
		return sessionUtility.checkForSession(request);
	}
	@GET
	@Path("/destroySession")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean destroySessoin(@Context HttpServletRequest request)
	{
		log.debug("in destroySessoin");

		SessionUtility sessionUtility=new SessionUtility();
		return sessionUtility.destroySession(request);

	}
	
	@POST
	@Path("/forgotPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseToForgotPassword forgotPasswordResourse(ForgotPasswordInfo forgotPasswordInfo){
		
		log.debug("in forgotPasswordResourse");
		DeligateLogin deligateLogin=new DeligateLogin();
		return deligateLogin.deligateforgotPassword(forgotPasswordInfo);
		
		 
	}
	@POST
	@Path("/resetPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean resetPasswordResourse(NewPasswordInfo newPasswordInfo){
		log.debug("in resetPasswordResourse");

		DeligateLogin deligateLogin=new DeligateLogin();
		return deligateLogin.deligateResetPassword(newPasswordInfo);
		
		 
	}
	@POST
	@Path("/apply-leave")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean applyLeaveResourse(LeaveApplicationDetails leaveApplicationDetails){
		log.debug("in applyLeaveResourse");

		UserDeligate userDeligate=new UserDeligate();
		return userDeligate.deligateLeaveApplication(leaveApplicationDetails);
	}
	@POST
	@Path("/apply-leave/leaveValidity")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public EmployeeLeaveInfo leaveValidationResource(String empId){
		log.debug("in leaveValidationResource");

		UserDeligate userDeligate=new UserDeligate();
		return userDeligate.deligateLeaveValidation(empId);
		
		 
	}
	
}
