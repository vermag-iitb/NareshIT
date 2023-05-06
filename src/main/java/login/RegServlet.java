package login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//@WebServlet("/reg")
public class RegServlet extends GenericServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    public RegServlet() {
        super();
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {	
    	try
    	{
//    		Way 1: 
//    		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	    	String url, name, pwd;
//	    	url = "jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;";
//	        name = "sa";
//	        pwd = "reallyStrongPwd123";
//	    	con = DriverManager.getConnection(url,name,pwd);
    		
//    		Way 2: 
//    		String s1 = config.getInitParameter("driver");
//    		String s2 = config.getInitParameter("url");
//    		String s3 = config.getInitParameter("username");
//    		String s4 = config.getInitParameter("password");
//    		Class.forName(s1);
//    		con = DriverManager.getConnection(s2,s3,s4);
    		
//    		Way 3: 
    		ServletContext sc = config.getServletContext();
    		String s1 = sc.getInitParameter("driver");
    		String s2 = sc.getInitParameter("url");
    		String s3 = sc.getInitParameter("username");
    		String s4 = sc.getInitParameter("password");
    		Class.forName(s1);
    		con = DriverManager.getConnection(s2,s3,s4);
    		
    		
	    	if (con != null)
	            System.out.println("Connection Established. \nHashcode => "+con);
	        else
	            System.out.println("Connection could not establish");
    	}
    	catch(ClassNotFoundException | SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
	
    public void destroy()
    {
    	try 
    	{
    		con.close();
    	}
    	catch(SQLException e)
    	{
    		e.printStackTrace();
    	}
    }
    
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
    	try 
    	{
    		
    		System.out.println("Inside service()");
    		String s1 = request.getParameter("fname");
    		String s2 = request.getParameter("lname");
    		String s3 = request.getParameter("uname");
    		String s4 = request.getParameter("pwd");
    		PreparedStatement pstmt1 = con.prepareStatement("insert into [Demo2Conn].ID_1.uinfo values(?,?,?,?);");
    		pstmt1.setString(1,s1);
    		pstmt1.setString(2,s2);
    		pstmt1.setString(3,s3);
    		pstmt1.setString(4,s4);
    		pstmt1.executeUpdate();
    		PrintWriter pw = response.getWriter();
    		pw.println("<html><body bgcolor=red text=yellow><h1>");
            pw.println("You have successfully registered. Proceed to login!!!");
            pw.println("</h1><br><a href=login.html> Login </a><br></body></html>");
    	}
    	catch (SQLException | IOException e)
    	{
    		e.printStackTrace();
    	}
	}
}