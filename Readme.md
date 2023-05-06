###Flow diagram

homepage.html -> login.html -> LoginServlet.java
	|										|
	V										V
registration.html -> RegServlet.java    -> DB

###Pages content
#####homepage.html
Login
Regitration

#####login.html
Username	- ___________
Pwd			- ___________

Submit		Reset

####LoginServlet.html
	--------------
	--Call to DB--
	--------------
if(exist)
	Welcome...
else
	Invalid User/Pwd
	

####registration.html
  
  Registration Form

First Name	_______
Last Name	_______
Username	_______
Pwd			_______

Submit		Reset


####RegServlet.java
	--------------
	--Call to DB--
	--------------
	
You have successfully 
    registered!!!
    
Login

####DB
fname | lname | uname | pwd
----------------------------
	  |		  |		  |
	  
	  
###How to Run
1. Rt. click on Project name ('NareshIT') -> Select Run as -> Run on ServerSelect
2. Tomcat (appropriate version) -> Click Next -> Move only appropriate project to RHS -> Finish

First url: http://localhost:8082/NareshIT/homepage.html

###Setting up jdbc connection: 
1.  Directly defining 
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	System.out.println("Driver loaded");
	String url, name, pwd;
	url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;";
    name = "sa";
    pwd = "reallyStrongPwd123";
	con = DriverManager.getConnection(url,name,pwd);
2.  Using initialization parameters:
	String s1 = config.getInitParameter("driver");
	String s2 = config.getInitParameter("url");
	String s3 = config.getInitParameter("username");
	String s4 = config.getInitParameter("password");
	Class.forName(s1);
	con = DriverManager.getConnection(s2,s3,s4);
	
	And define <init-param> in web.xml:
	
	<servlet>
	  	<servlet-name>login-servlet</servlet-name>
	  	<servlet-class>login.LoginServlet</servlet-class>
		<init-param>
		  <param-name>driver</param-name>
		  <param-value>com.microsoft.sqlserver.jdbc.SQLServerDriver</param-value>
		  </init-param>
		<init-param>
			<param-name>url</param-name>
			<param-value>jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;</param-value>
		</init-param>
		<init-param>
			<param-name>username</param-name>
			<param-value>sa</param-value>
		</init-param>
		<init-param>
			<param-name>password</param-name>
			<param-value>reallyStrongPwd123</param-value>
		</init-param>
  </servlet>
  
  - getInitParameter() of ServletConfig interface helps to retrieve init (also called initialization parameters) parameter in Servlets from web.xml.
  - ServletConfig object is created by web-container whenever init() method is called
  - ServletConfig is created one per Servlet
  
  3. Context parameters:
  <context-param>
  <param-name>
  <param-value>
  - These are not specific to a servlet, hence defined outside the <servlet> tag (but inside <web-app> tag).
  - getInitParameter() of ServletContext interface helps to retrieve context parameter in Servlets from web.xml.
  - ServletContext object is created by web-container whenever web-application is deployed on server.
  - ServletContext is created one per application
  - Sequence of objects created by Web-container: 
  ServletContext (when app is deployed) -> User-defined Servlet (when 1st request comes) -> ServletConfig (when init() is called, i.e., 1st request) 
  -> ServletRequest and ServletResponse (whenever request comes)