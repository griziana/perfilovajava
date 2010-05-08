package s06;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Ilya
 * Date: 18.03.2010
 * Time: 12:04:42
 */
public class TestServlet {

    /**
     * ���� � �������������� �������
     */
    private static final String TOMCAT_PATH = "G:/java/Tomcat";
    
    /**
     * ��� ��� ����������. ����� ��������� ������. ��� ���������� ����������� � �����
     *   [tomcat]/webapps/[���]
     * � �������� �� ������
     *   http://localhost:8080/[���]
     */
    private static final String APP_NAME = "TestApp";

    /**
     * ������� � ���������� ���-����������.
     */
    private static final String WEB_CONTENT = "WebContent";

    private static final String LOGIN = "ad";
    private static final String PASSWORD = "pw";

    private static final String APP_PATH = TOMCAT_PATH + "/webapps/" + APP_NAME;

    public static void main(String[] args) throws IOException {
        //System.out.println("================================================");
        System.out.println(new Date());

        copyWebInfToTomcat();
        copyOutputToClasses();

        reloadServlet();
    }

    private static void copyWebInfToTomcat() {
        System.out.println("Copy WebContent to Tomcat");

        try {
            copyDirectory(new File(WEB_CONTENT), new File(APP_PATH));
        } catch (IOException e) {
            System.out.println("Failed to copy directory");
            System.out.println(e.getMessage());
        }
    }

    private static void copyOutputToClasses() {
        System.out.println("Copy classes to /WEB-INF/classes");

        try {
            copyDirectory(new File("build\\classes"), new File(APP_PATH + "/WEB-INF/classes"));
        } catch (IOException e) {
            System.out.println("Failed to copy directory");
            System.out.println(e.getMessage());
        }
    }

    public static void copyDirectory(File sourceLocation, File targetLocation) throws IOException {       
        System.out.println("  --> copy " + sourceLocation + " to " + targetLocation);

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists())
                if (!targetLocation.mkdir())
                    throw new IOException("failed to create Dir " + targetLocation);

            String[] children = sourceLocation.list();
            for (String child : children)
                copyDirectory(new File(sourceLocation, child), new File(targetLocation, child));
        } else {
            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);                        

            // Copy the bits from instream to outstream
            byte[] buf = new byte[4096];
            int len;
            while ((len = in.read(buf)) > 0)
                out.write(buf, 0, len);
            
            in.close();
            out.close();
        }

    }

    private static String tomcatRequest(String url) throws IOException {
        URL u = new URL(url);
        HttpURLConnection hurl = (HttpURLConnection) u.openConnection();
        String loginAndPassword = LOGIN + ":" + PASSWORD;
        hurl.setRequestProperty("Authorization", "Basic " + new BASE64Encoder().encode(loginAndPassword.getBytes()));
        BufferedReader br = new BufferedReader(new InputStreamReader(hurl.getInputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null)
            sb.append(line);
        return sb.toString();
    }


    private static void reloadServlet() throws IOException {
        String req = "http://localhost:8080/manager/reload?path=/" + APP_NAME;
        String result = tomcatRequest(req);
        System.out.println("-----------------------------------------");
        System.out.println("Reload request:");
        System.out.println(req);
        System.out.println();
        System.out.println(result);

        if (result.startsWith("FAIL")) {
            System.out.println();
            System.out.println("Reload failed, try to deploy:");
            req = "http://localhost:8080/manager/deploy?path=/" + APP_NAME /*+ "&war=" + APP_NAME*/;
            System.out.println(req);
            result = tomcatRequest(req);
            System.out.println();           
            System.out.println(result);
        }
    }

}
