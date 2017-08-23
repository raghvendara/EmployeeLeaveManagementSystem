package com.alacriti.elm.listener;

import java.util.Calendar;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.alacriti.elm.dao.AutoUpdateHRStatusInDBDao;
import com.alacriti.elm.dao.AutoUpdatePMStatusInDBDao;
import com.alacriti.elm.dao.AutoUpdateTLStatusInDBDao;
@WebListener
public class ContextListenerForDBUpdate implements ServletContextListener{

	public void contextInitialized(ServletContextEvent event) {
		System.out.println("***************** new servlet context is created.!!!..*****************");				
		ThreadToRun t1=new ThreadToRun();
		t1.start();		
	}
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("***************** new servlet context is destroyed..!!.*****************");		
		
	}
	
}
class ThreadToRun extends Thread{

	
	public void run() {
		boolean isTLStatusUpdated=false;
		boolean isPMStatusUpdated=false;
		boolean isHRStatusUpdated=false;


		try {
			while(true){
			Thread.sleep(10*60*1000);
			java.sql.Date todaysDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			System.out.println("****************  Todays date is :"+todaysDateObject.toString()+"  ****************");
			 isTLStatusUpdated=new AutoUpdateTLStatusInDBDao().updateTLStatus(todaysDateObject);
			 System.out.println("is TL status updated  :" + isTLStatusUpdated);
			if(isTLStatusUpdated){
				isPMStatusUpdated=new AutoUpdatePMStatusInDBDao().updatePMStatus(todaysDateObject);
			 	System.out.println("is PM status updated :" + isPMStatusUpdated);
			}
			if(isPMStatusUpdated){
			 	System.out.println("is HR status updated ::" + isPMStatusUpdated);

				isHRStatusUpdated = new AutoUpdateHRStatusInDBDao().updateHRStatus(todaysDateObject);
			 	System.out.println("is HR status updated ::" + isHRStatusUpdated);
			}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
}