package s06.z03;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.text.*;
import javax.swing.text.Element;
import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UserList extends ArrayList<User> {
    public static UserList loadFromXML(String fname) {
        UserList ul = new UserList();
        DocumentBuilderFactory bf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = bf.newDocumentBuilder();
            Document doc = null;
            try {
                doc = db.parse(new File(fname));
            } catch (SAXException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            org.w3c.dom.Element users = doc.getDocumentElement();
            System.out.println(users);
            NodeList nl = users.getElementsByTagName("user");

            int size = nl.getLength();
            for (int i = 0; i < size; i++)
                System.out.println(nl.item(i));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return ul;
    }

}
