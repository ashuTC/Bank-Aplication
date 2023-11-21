<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.sql.*" %>
<%@ page import="com.rcpit.connect.*" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
<% 
int id=Integer.parseInt(request.getParameter("id"));
try{
			Connection con=ConnectDB.connect();
			PreparedStatement ps1=con.prepareStatement("delete from users where id=?");
			ps1.setInt(1, id);
			int res=ps1.executeUpdate();
			
			if(res>0)
			{
				System.out.println("Amount Delete");
				response.sendRedirect("viewAll.jsp");
			}
			
			else
			{
				System.out.println("Failed");
				response.sendRedirect("404.html");
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		%>