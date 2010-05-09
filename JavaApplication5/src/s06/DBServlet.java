package s06;


import javax.servlet.http.*;
import java.io.*;
import java.sql.*;


public class DBServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        try {


            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://db4free.net/perfilovajava", "perfilova", "12345678");
            Statement st = con.createStatement();

            pw.println("<html>");
            pw.println("<head><title>Hello, my little friend!</title></head>");
            pw.println("<body>");
            pw.println("<center><h1>Hello, my little " + name + "!</h1></center>");
            pw.println("<table width=\"100%\" border=\"1\">");
            ResultSet rs = st.executeQuery("SELECT*FROM user");

            while (rs.next()) {
                pw.println("<tr>");
                pw.println("<td>" + rs.getString(1) + "</td>"); //ID
                pw.println("<td>" + rs.getString(2) + "</td>"); //name
                pw.println("<td>" + rs.getString(3) + "</td>"); //surname
                pw.println("<td>" + rs.getString(4) + "</td>"); //age
                pw.println("<td>" + rs.getString(5) + "</td>"); // city
                pw.println("</tr>");
            }


            pw.println("</table></body></html>");
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        pw.flush();
        pw.close();
    }
}