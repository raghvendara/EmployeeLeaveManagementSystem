package com.alacriti.elm.resteasy.resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtility {
	public boolean checkForSession(HttpServletRequest request)
	{
		HttpSession session= request.getSession(false);
		System.out.println("printing the status of session");
		System.out.println(session);
		if(session==null)
			return false;
		else 
			return true;
	}

	public boolean destroySession(HttpServletRequest request) {
		HttpSession session= request.getSession(false);
		System.out.println("in session destroy=====>>>" + session);
		if(session!=null)
		{
		session.invalidate();
		return true;
		}
		
		else return false;
	}
}
