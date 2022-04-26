import java.io.*;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.*;  
import javax.servlet.http.*;  
  
/**
 *
 * @author ADMIN
 */
public class Admin extends HttpServlet {  
  
@Override
public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{  
    PrintWriter out = response.getWriter(); 
     try 
     {
       
        response.setContentType("text/html");  
        
          
        String name=request.getParameter("uname");
        String pwd=request.getParameter("pwd");
     
        Class.forName("org.apache.derby.jdbc.ClientDriver");
Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/java", "akash", "123");
      PreparedStatement pst = con.prepareStatement("select * from admin where name=? and password=?");
      pst.setString(1,name);
      pst.setString(2,pwd); 
      
      
 
    ResultSet rs=pst.executeQuery(); 
    PreparedStatement ps = con.prepareStatement("select * from address order by date desc");
    ps.setMaxRows(7);
        ResultSet r=ps.executeQuery();
        
        out.print("<!DOCTYPE html>\n" +
"<html >\n" +
"<head>\n" +
"  <!-- Site made with Mobirise Website Builder v4.8.1, https://mobirise.com -->\n" +
"  <meta charset=\"UTF-8\">\n" +
"  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
"  <meta name=\"generator\" content=\"Mobirise v4.8.1, mobirise.com\">\n" +
"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, minimum-scale=1\">\n" +
"  <link rel=\"shortcut icon\" href=\"assets/images/logo-122x34.png\" type=\"image/x-icon\">\n" +
"  <meta name=\"description\" content=\"\">\n" +
"  <title>Home</title>\n" +
"  <link rel=\"stylesheet\" href=\"assets/web/assets/mobirise-icons/mobirise-icons.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/tether/tether.min.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap.min.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap-grid.min.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/bootstrap/css/bootstrap-reboot.min.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/dropdown/css/style.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/socicon/css/styles.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/theme/css/style.css\">\n" +
"  <link rel=\"stylesheet\" href=\"assets/mobirise/css/mbr-additional.css\" type=\"text/css\">\n" +
"  <link rel = \"stylesheet\" type=\"text/css\" href=\"Form.css\">\n" +
"  \n" +
"  \n" +
"  \n" +
"</head>\n" +
"<body>\n" +
"  <section class=\"menu cid-r1rYqMp37C\" once=\"menu\" id=\"menu1-7\">\n" +
"\n" +
"    \n" +
"\n" +
"    <nav class=\"navbar navbar-expand beta-menu navbar-dropdown align-items-center navbar-fixed-top navbar-toggleable-sm\">\n" +
"        <button class=\"navbar-toggler navbar-toggler-right\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
"            <div class=\"hamburger\">\n" +
"                <span></span>\n" +
"                <span></span>\n" +
"                <span></span>\n" +
"                <span></span>\n" +
"            </div>\n" +
"        </button>\n" +
"        <div class=\"menu-logo\">\n" +
"            <div class=\"navbar-brand\">\n" +
"                <span class=\"navbar-logo\">\n" +
"                    <a href=\"index.html\">\n" +
"                         <img src=\"assets/images/logo-122x34.png\" alt=\"Mobirise\" title=\"\" style=\"height: 3.8rem;\">\n" +
"                    </a>\n" +
"                </span>\n" +
"                <span class=\"navbar-caption-wrap\"><a class=\"navbar-caption text-white display-7\" href=\"login.html\"><span class=\"mbri-users mbr-iconfont mbr-iconfont-btn\"></span>\n" +
"                        LOGIN\n" +
"                    </a></span>\n" +
"            </div>\n" +
"        </div>\n" +
"        <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
"            <ul class=\"navbar-nav nav-dropdown nav-right\" data-app-modern-menu=\"true\"><li class=\"nav-item\">\n" +
"                    <a class=\"nav-link link text-white display-4\" href=\"\">\n" +
"                        <span class=\"mbri-home mbr-iconfont mbr-iconfont-btn\"></span>\n" +
"                        Services\n" +
"                    </a>\n" +
"                </li>\n" +
"                <li class=\"nav-item\">\n" +
"                    <a class=\"nav-link link text-white display-4\" href=\"\">\n" +
"                        <span class=\"mbri-search mbr-iconfont mbr-iconfont-btn\"></span>\n" +
"                        About Us\n" +
"                    </a>\n" +
"                </li></ul>\n" +
"            \n" +
"        </div>\n" +
"    </nav>\n" +
"</section>\n" +
"<section class=\"engine\"><a href=\"\"></a></section><section class=\"header12 cid-r1rYvNK8nk mbr-fullscreen mbr-parallax-background\" id=\"header12-8\">\n" +
"\n" +
"    \n" +
"\n" +
"    <div class=\"mbr-overlay\" style=\"opacity:0.9; background-color: rgb(15, 118, 153);\">");
        out.println("<br><br><br><br><br>");
               out.print("<table align='center' border='2px' cellpadding='8px' ><tr><th>Name</th><th>PhNo.</th><th>date</th><th>Time</th><th>Adress</th></tr>"); 
              out.println( "<font color='red'>");
        if(rs.next())
        {
           
            while(r.next())
            {
                out.println("<tr><td>"+r.getString("name")+"</td><td> "+r.getString("phno")+"</td><td> "+r.getDate("date")+"</td><td> "+r.getString("time")+"</td><td>"+r.getString("HNO")+","+r.getString("street")+"<br>"+r.getString("city")+","+r.getString("pin")+"</td></tr>");
            }
            out.println("</font></table>");
        }
   
     }
     catch(Exception e)
     {
         out.println(e);
     }
     out.println("</div>\n" +
"      \n" +
"	  \n" +
"</section>\n" +
"\n" +
"<section class=\"footer4 cid-r1s2899T7l\" id=\"footer4-a\">\n" +
"\n" +
"    \n" +
"\n" +
"    \n" +
"\n" +
"    <div class=\"container\">\n" +
"        <div class=\"media-container-row content mbr-white\">\n" +
"            <div class=\"col-md-3 col-sm-4\">\n" +
"                <div class=\"mb-3 img-logo\">\n" +
"                    <a href=\"\">\n" +
"                        <img src=\"assets/images/logo-192x53.png\" alt=\"Mobirise\" title=\"\">\n" +
"                    </a>\n" +
"                </div>\n" +
"                <p class=\"mb-3 mbr-fonts-style foot-title display-7\">\n" +
"                    \n" +
"                </p>\n" +
"                <p class=\"mbr-text mbr-fonts-style mbr-links-column display-7\">\n" +
"                    <a href=\"#\" class=\"text-white\">About Us</a>\n" +
"                    <br><a href=\"#\" class=\"text-white\">Services</a>\n" +
"                    <br><a href=\"#\" class=\"text-white\">Selected Work</a>\n" +
"                    <br><a href=\"#\" class=\"text-white\">Get In Touch</a>\n" +
"                </p>\n" +
"            </div>\n" +
"            <div class=\"col-md-4 col-sm-8\">\n" +
"                <p class=\"mb-4 foot-title mbr-fonts-style display-7\">\n" +
"                    RECENT NEWS\n" +
"                </p>\n" +
"                <p class=\"mbr-text mbr-fonts-style foot-text display-7\">\n" +
"                    Footer with solid color background and a contact form, Easily add subscribe and contact forms without any server-side integration.\n" +
"                    <br>\n" +
"                    <br>Mobirise helps you cut down development time by providing you with a flexible website editor with a drag and drop interface.\n" +
"                </p>\n" +
"            </div>\n" +
"            <div class=\"col-md-4 offset-md-1 col-sm-12\">\n" +
"                <p class=\"mb-4 foot-title mbr-fonts-style display-7\">\n" +
"                    SUBSCRIBE\n" +
"                </p>\n" +
"                <p class=\"mbr-text mbr-fonts-style form-text display-7\">\n" +
"                    Get monthly updates and free resources.\n" +
"                </p>\n" +
"                <div class=\"media-container-column\" data-form-type=\"formoid\">\n" +
"                    <div data-form-alert=\"\" hidden=\"\" class=\"align-center\">\n" +
"                        Thanks for filling out the form!\n" +
"                    </div>\n" +
"\n" +
"                    <form class=\"form-inline\" action=\"\" method=\"post\" data-form-title=\"Mobirise Form\">\n" +
"                        <input type=\"hidden\" value=\"fwEIAapMDPBsGKNefgevVKxxLGOeGzeO+Ertl+TnIrqo/bmlGbURwk4jbp1OrTia7hdveNOU5br5F3eVSE2RLZGtQWuLzEht7Pxa9AnzCyXg2I0DgJ0KqvAls1TVlQoG\" data-form-email=\"true\">\n" +
"                        <div class=\"form-group\">\n" +
"                            <input type=\"email\" class=\"form-control input-sm input-inverse my-2\" name=\"email\" required=\"\" data-form-field=\"Email\" placeholder=\"Email\" id=\"email-footer4-a\">\n" +
"                        </div>\n" +
"                        <div class=\"input-group-btn m-2\"><button href=\"\" class=\"btn btn-primary display-4\" type=\"submit\" role=\"button\">Subscribe</button></div>\n" +
"                    </form>\n" +
"                </div>\n" +
"                <p class=\"mb-4 mbr-fonts-style foot-title display-7\">\n" +
"                    CONNECT WITH US\n" +
"                </p>\n" +
"                <div class=\"social-list pl-0 mb-0\">\n" +
"                        <div class=\"soc-item\">\n" +
"                            <a href=\"https://twitter.com/\" target=\"_blank\">\n" +
"                                <span class=\"socicon-twitter socicon mbr-iconfont mbr-iconfont-social\"></span>\n" +
"                            </a>\n" +
"                        </div>\n" +
"                        <div class=\"soc-item\">\n" +
"                            <a href=\"https://www.facebook.com/\" target=\"_blank\">\n" +
"                                <span class=\"socicon-facebook socicon mbr-iconfont mbr-iconfont-social\"></span>\n" +
"                            </a>\n" +
"                        </div>\n" +
"                        <div class=\"soc-item\">\n" +
"                            <a href=\"https://www.youtube.com/\" target=\"_blank\">\n" +
"                                <span class=\"socicon-youtube socicon mbr-iconfont mbr-iconfont-social\"></span>\n" +
"                            </a>\n" +
"                        </div>\n" +
"                        <div class=\"soc-item\">\n" +
"                            <a href=\"https://instagram.com/\" target=\"_blank\">\n" +
"                                <span class=\"socicon-instagram socicon mbr-iconfont mbr-iconfont-social\"></span>\n" +
"                            </a>\n" +
"                        </div>\n" +
"                        <div class=\"soc-item\">\n" +
"                            <a href=\"https://plus.google.com/\" target=\"_blank\">\n" +
"                                <span class=\"socicon-googleplus socicon mbr-iconfont mbr-iconfont-social\"></span>\n" +
"                            </a>\n" +
"                        </div>\n" +
"                        <div class=\"soc-item\">\n" +
"                            <a href=\"https://www.behance.net/\" target=\"_blank\">\n" +
"                                <span class=\"socicon-behance socicon mbr-iconfont mbr-iconfont-social\"></span>\n" +
"                            </a>\n" +
"                        </div>\n" +
"                </div>\n" +
"            </div>\n" +
"        </div>\n" +
"        <div class=\"footer-lower\">\n" +
"            <div class=\"media-container-row\">\n" +
"                <div class=\"col-sm-12\">\n" +
"                    <hr>\n" +
"                </div>\n" +
"            </div>\n" +
"            <div class=\"media-container-row mbr-white\">\n" +
"                <div class=\"col-sm-12 copyright\">\n" +
"                    <p class=\"mbr-text mbr-fonts-style display-7\">&nbsp; &nbsp;<br><br></p>\n" +
"                </div>\n" +
"            </div>\n" +
"        </div>\n" +
"    </div>\n" +
"</section>\n" +
"\n" +
"\n" +
"  <script src=\"assets/web/assets/jquery/jquery.min.js\"></script>\n" +
"  <script src=\"assets/popper/popper.min.js\"></script>\n" +
"  <script src=\"assets/tether/tether.min.js\"></script>\n" +
"  <script src=\"assets/bootstrap/js/bootstrap.min.js\"></script>\n" +
"  <script src=\"assets/smoothscroll/smooth-scroll.js\"></script>\n" +
"  <script src=\"assets/dropdown/js/script.min.js\"></script>\n" +
"  <script src=\"assets/touchswipe/jquery.touch-swipe.min.js\"></script>\n" +
"  <script src=\"assets/vimeoplayer/jquery.mb.vimeo_player.js\"></script>\n" +
"  <script src=\"assets/parallax/jarallax.min.js\"></script>\n" +
"  <script src=\"assets/bootstrapcarouselswipe/bootstrap-carousel-swipe.js\"></script>\n" +
"  <script src=\"assets/mbr-clients-slider/mbr-clients-slider.js\"></script>\n" +
"  <script src=\"assets/theme/js/script.js\"></script>\n" +
"  <script src=\"assets/formoid/formoid.min.js\"></script>\n" +
"  \n" +
"  \n" +
"</body>\n" +
"</html>");
}
}
    
