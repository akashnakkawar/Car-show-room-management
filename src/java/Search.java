    import java.io.*;  
    import java.sql.*;  
    import javax.servlet.ServletException;  
    import javax.servlet.http.*;  

      
    public class Search extends HttpServlet {  
      
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
      
    
              
    String email=request.getParameter("email");  
    String password=request.getParameter("pwd");
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
              
    try{  
    Class.forName("org.apache.derby.jdbc.ClientDriver");  
    Connection con=DriverManager.getConnection( "jdbc:derby://localhost:1527/java","akash","123");  
                  
    PreparedStatement ps=con.prepareStatement("select * from CUSTOMER where email=? and password=?");  
        
    ps.setString(1,email); 
    ps.setString(2, password);
    ResultSet rs=ps.executeQuery();  
      if(rs.next()){
          String n=rs.getString("NAME");
           String m=rs.getString("EMAIL");
          
          HttpSession session=request.getSession();  
          session.setAttribute("uname",n);  
          session.setAttribute("email",m);
          response.sendRedirect("login1.html");
      }
      else{
        out.println("<meta http-equiv='refresh' content='3;URL=login.html'>");//redirects after 3 seconds
        out.println("<h2 style='color:red;'>User or password incorrect!</h2>");
      }
              
}catch (Exception e2) {e2.printStackTrace();}  
          
finally{out.close();}  
  
    }
  } 
    
     