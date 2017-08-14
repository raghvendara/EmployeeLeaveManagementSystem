# EmployeeLeaveManagementSystem
My mini project

Resources need for the project to execute:
1) jdk (1.8) 
2) wildfly server (10.1.0)
3) node server (v6.11.1)


Steps to be follwed to execute the code:


Build the java code using maven to get the war file. (mvn clean install)

Deploy the war file into wildfly server to host the application.(on port 8080) 

Open any webstrom IDE and create an angular2 project and put replace the src folder with the src folder given in elmSystem-Angular2 directory.

Now run the angular code on node server to access the application on web browser.

Make sure that your wildfly server and node server both runnig in one system. 

Change the url's in the angular files from localhost to the perticular ip address in which your runnig the wildfly server if you use another system for wildfly server.

Description about the application elmSystem(Employee Leave Management System)

	It is a web application designed for an organization which will manage the leaves of their employees using a web application.
Admin (Team Leader(TL), Project Manager(PM), and HR) people as well as employees can logs into this application. Admin people can see the leave requests and can search the employee leaves information and their leave balances.

	Login page allows users to enter into the application. Forgot Password option also available to users.
	
	HR has given the access to add new employees with some default login credentials.
	
	If an employee logs into application they can view their leave tables,status and balanced leaves and they can able to edit their profiles also.

	



