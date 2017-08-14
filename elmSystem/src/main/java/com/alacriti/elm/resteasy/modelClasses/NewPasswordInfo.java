package com.alacriti.elm.resteasy.modelClasses;

public class NewPasswordInfo {
	String passWd;
	String cPassWd;
	String empID;
	
	public NewPasswordInfo() {}
	
	public NewPasswordInfo(String passWd, String empID) {
		super();
		this.passWd = passWd;
		this.empID = empID;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public String getcPassWd() {
		return cPassWd;
	}
	public void setcPassWd(String cPassWd) {
		this.cPassWd = cPassWd;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	
	

}
