package s06;

import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class DBServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/perfilovajava", "perfilova", "12345678");
            Statement st = con.createStatement();

            pw.println("<html>");
            pw.println("<head><title>Hello, my little friend!</title></head>");
            pw.println("<body>");
            pw.println("<center><h1>Hello, my little " + name + "!</h1>");
            pw.println("<table width=\"100%\" border=\"1\">");
            ResultSet rs = st.executeQuery("SELECT*FROM user");

            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getString(1) + "</td>"); //ID
                pw.println("<td>" + rs.getString(2) + "</td>"); //NAME
                pw.println("<td>" + rs.getString(3) + "</td>"); //SURNAME
                pw.println("<td>" + rs.getString(4) + "</td>"); //AGE
                pw.println("<td>" + rs.getString(5) + "</td>"); // CITY
                pw.println("</tr>");
            }
            st.close();
            
            pw.println("/table>");
            pw.println("/center>");
            pw.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.flush();
        pw.close();
    }
}