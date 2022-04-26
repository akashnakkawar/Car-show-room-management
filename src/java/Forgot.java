    import java.io.*;  
    import java.sql.*;  
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
    import javax.servlet.ServletException;  
    import javax.servlet.http.*;  

      
    public class Forgot extends HttpServlet {  
      
    public void doGet(HttpServletRequest request, HttpServletResponse response)  
                throws ServletException, IOException {  
      
    
              
    String email=request.getParameter("email");  
    
    response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
              
    try{  
    Class.forName("org.apache.derby.jdbc.ClientDriver");  
    Connection con=DriverManager.getConnection( "jdbc:derby://localhost:1527/java","akash","123");  
                  
    PreparedStatement ps=con.prepareStatement("select * from CUSTOMER where email=?");  
        
    ps.setString(1,email); 
  
    ResultSet rs=ps.executeQuery();  
      if(rs.next()){
          String n=rs.getString("PASSWORD");
          String toemail=rs.getString("EMAIL");
          
          
          try{
            String host ="smtp.gmail.com" ;
            String user = "findridebyonline@gmail.com";
            String pass = "akashnakkawar";
            String to = toemail;
            String from = "findridebyonline@gmail.com";
            String subject = "This is password mail";
            String messageText = "your account password is "+n;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            javax.mail.Session mailSession = javax.mail.Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); 
            msg.setText(messageText);

           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
         out.println("<meta http-equiv='refresh' content='3;URL=login.html'>");//redirects after 3 seconds
        out.println("<h2 style='color:red;'>Password of your account has been sent to your mail id..</h2>"); 
         
          
      }
      else{
        out.println("<meta http-equiv='refresh' content='3;URL=login.html'>");//redirects after 3 seconds
        out.println("<h2 style='color:red;'>This email id is not registered with us..</h2>");
      }
              
}catch (Exception e2) {e2.printStackTrace();}  
          
finally{out.close();}  
  
    }
  } 
    
     