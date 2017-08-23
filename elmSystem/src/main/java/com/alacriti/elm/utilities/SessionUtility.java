package com.alacriti.elm.utilities;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

public class SessionUtility {
	public static final Logger log= Logger.getLogger(SessionUtility.class);

	public boolean checkForSession(HttpServletRequest request)
	{
		log.debug("in checkForSession");
		HttpSession session= request.getSession(false);
		System.out.println("printing the status of session");
		System.out.println(session);
		if(session==null)
			return false;
		else 
			return true;
	}

	public boolean destroySession(HttpServletRequest request) {
		log.debug("in destroySession");

		HttpSession session= request.getSession();
		session.invalidate();
		return checkForSession(request);
	}
}
