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

import com.alacriti.elm.resteasy.modelClasses.ForgotPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.LeaveApplicationDetails;
import com.alacriti.elm.resteasy.modelClasses.NewPasswordInfo;
import com.alacriti.elm.resteasy.modelClasses.ResponseToForgotPassword;
import com.alacriti.elm.resteasy.modelClasses.ResponseToLoginPost;
import com.alacriti.elm.resteasy.modelClasses.UserLoginInfo;
import com.alacriti.elm.resteasy.resourceDeligate.DeligateLogin;
import com.alacriti.elm.resteasy.resourceDeligate.UserDeligate;

@XmlRootElement
@Path("/login")
public class UserResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseToLoginPost loginValidation(UserLoginInfo userLoginInfo,@Context HttpServletRequest request){
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
		SessionUtility sessionUtility=new SessionUtility();
//		HttpSession session= request.getSession(false);
//		System.out.println("session in checkSession :"+session.getId());
		return sessionUtility.checkForSession(request);
	}
	@GET
	@Path("/destroySession")
	@Produces(MediaType.TEXT_PLAIN)
	public boolean destroySessoin(@Context HttpServletRequest request)
	{
//		SessionUtility sessionUtility=new SessionUtility();
		request.getSession().invalidate();
		System.out.println("in destroy session resource : ============>>>>>"+request.getSession(false));
		if(request.getSession(false)!=null)
		{
			return true;
		}
		else
			return false;
//		System.out.println("session in checkSession :"+session.getId());
//		return sessionUtility.destroySession(request);
	}
	
	@POST
	@Path("/forgotPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseToForgotPassword forgotPasswordResourse(ForgotPasswordInfo forgotPasswordInfo){
		DeligateLogin deligateLogin=new DeligateLogin();
		return deligateLogin.deligateforgotPassword(forgotPasswordInfo);
		
		 
	}
	@POST
	@Path("/resetPassword")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean resetPasswordResourse(NewPasswordInfo newPasswordInfo){
		DeligateLogin deligateLogin=new DeligateLogin();
		return deligateLogin.deligateResetPassword(newPasswordInfo);
		
		 
	}
	@POST
	@Path("/apply-leave")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public boolean applyLeaveResourse(LeaveApplicationDetails leaveApplicationDetails){
		UserDeligate userDeligate=new UserDeligate();
		return userDeligate.deligateLeaveApplication(leaveApplicationDetails);
		
		 
	}
	
	
}
