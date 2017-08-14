package com.alacriti.elm.resteasy.modelClasses;

public class ResponseToLoginPost {
		boolean flag;
		String emp_id;
		String fullName;
		String designation;
		String projectName;

		public ResponseToLoginPost(boolean flag, String emp_id, String fullName,String designation,String projectName) {
			
			this.flag = flag;
			this.emp_id = emp_id;
			this.fullName = fullName;
			this.designation = designation;
			this.projectName=projectName;
		}
		public ResponseToLoginPost(boolean flag)
		{
			this.flag=flag;
		}
		public ResponseToLoginPost() {
			
		}

		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		

		public String getEmp_id() {
			return emp_id;
		}

		public void setEmp_id(String emp_id) {
			this.emp_id = emp_id;
		}

		public String getFullName() {
			return fullName;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}
		
	

}
