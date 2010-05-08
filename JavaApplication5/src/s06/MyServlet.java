package s06;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Shadow2
 * Date: 08.05.2010
 * Time: 16:16:33
 * To change this template use File | Settings | File Templates.
 */
public class MyServlet extends HttpServlet implements SingleThreadModel {
       public void doGet (HttpServletRequest req, HttpServletResponse resp) {
           String name = req.getParameter("name");
           resp.setContentType("text/html");
           resp.setCharacterEncoding("UTF-8");
           PrintWriter pw = null;
           try {
               pw = resp.getWriter();
           } catch (IOException e) {
               e.printStackTrace();
           }
           pw.println("<html>");
           pw.println("<head><title>Hello, my little friend!</title></head>");
           pw.println("<body>");
           pw.println("Hello, my little "+name+"!");
           pw.println("</body></html>");
           pw.flush();
           pw.close();
       }
}
