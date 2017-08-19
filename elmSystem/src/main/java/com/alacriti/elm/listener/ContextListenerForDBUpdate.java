package com.alacriti.elm.listener;

import java.util.Calendar;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
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
		try {
			while(true){
			Thread.sleep(5000);
			java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			System.out.println("****************  Todays date is :"+ourJavaDateObject.toString()+"  ****************");
			new UpdateDB().updateDB(ourJavaDateObject);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
	}
	
}