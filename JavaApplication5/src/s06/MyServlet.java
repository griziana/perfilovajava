package s06;

import javax.servlet.http.*;
import java.io.*;


public class MyServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
            pw.println("<html>");
            pw.println("<head><title>Hello, my little friend!</title></head>");
            pw.println("<body>");
            pw.println("<center><h1>Hello, my little " + name + "!</h1></center>");
            pw.println("</body></html>");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
