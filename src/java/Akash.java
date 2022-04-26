import java.io.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import javax.servlet.*;  
import javax.servlet.http.*; 
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  
/**
 *
 * @author ADMIN
 */
public class Akash extends HttpServlet {  
  
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{  
    PrintWriter out = response.getWriter(); 
     try 
     {
       
        response.setContentType("text/html");  
        
          
        HttpSession session=request.getSession(false);  
        String n=(String)session.getAttribute("uname");  
        String toemail=(String)session.getAttribute("email"); 
        String hno=request.getParameter("hno");
        String street=request.getParameter("street");
        String city=request.getParameter("city");
        String pin=request.getParameter("pin");
        String time=request.getParameter("time");
        String date=request.getParameter("date");
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);

        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        String phno=request.getParameter("phno");
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/java", "akash", "123");
      PreparedStatement pst = con.prepareStatement("insert into ADDRESS values(?,?,?,?,?,?,?,?)");
      pst.setString(1,n);
      pst.setString(2,hno);
      pst.setString(3,street);
      pst.setString(4,city);
      pst.setString(5,pin);
      pst.setString(6, time);
      pst.setDate(7, sqlDate);
      pst.setString(8, phno);
     
      int i = pst.executeUpdate();
      if(i!=0){
          session.invalidate();
         out.println("<meta http-equiv='refresh' content='3;URL=index.html'>");//redirects after 3 seconds
        out.println("<h2 style='color:red;'>"+n+",you have placed home service</h2>");
        
         try{
            String host ="smtp.gmail.com" ;
            String user = "findridebyonline@gmail.com";
            String pass = "akashnakkawar";
            String to = toemail;
            String from = "findridebyonline@gmail.com";
            String subject = "This is confirmation mail";
            String messageText = "you have placed home pick up service.. on time:"+time+" date:"+sqlDate;
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
        
      }
      else{
        out.println("failed to insert the data");
      }
      
      
  
        
     }
     catch(Exception e)
     {
         out.print(e);
     }
}
     private void alert(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
  

}
    
