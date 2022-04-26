   import java.io.*;  
    import java.sql.*;  
import java.sql.Date;
    import javax.servlet.ServletException;  
    import javax.servlet.http.*;  
    import java.util.*;
    import javax.mail.*;
    import javax.mail.internet.*;
   import javax.mail.internet.MimeMessage;

public class Insert extends HttpServlet{

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
     
	response.setContentType("text/html");  
    PrintWriter out = response.getWriter();  
      response.setContentType("text/html");
        try{
      String username = request.getParameter("name");
      String city=request.getParameter("city");
      String email=request.getParameter("email");
      String password = request.getParameter("pwd");
      out.println(username);
      
      Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/java", "akash", "123");
      PreparedStatement pst = con.prepareStatement("insert into CUSTOMER values(?,?,?,?)");
      pst.setString(1,username);
      pst.setString(2,city);
      pst.setString(3,email);
      pst.setString(4,password);
      int i = pst.executeUpdate();
      if(i!=0){
          
          try{
            String host ="smtp.gmail.com" ;
            String user = "findridebyonline@gmail.com";
            String pass = "akashnakkawar";
            String to = email;
            String from = "findridebyonline@gmail.com";
            String subject = "This is confirmation mail";
            String messageText = "you have successfully signed in";
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
        out.println("<p style='color:red;'>YOu have succesfully signed up.<br> please login in to continue..</p>");
       
      }
      else{
        out.println("failed to insert the data");
      }
    }
    catch (Exception e){
      out.println(e);
    }
  }

    private void alert(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}