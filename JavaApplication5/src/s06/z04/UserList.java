package s06.z04;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import s06.z03.User;

import javax.swing.table.AbstractTableModel;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.sql.*;


public class UserList extends ArrayList<User> {


    public void loadFromXML(String fname) {

        UserList.this.clear();

        try {
            DocumentBuilderFactory bf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = bf.newDocumentBuilder();
            Document doc = db.parse(fname);
            doc.getDocumentElement().normalize();


            NodeList nl = doc.getElementsByTagName("user");

            int size = nl.getLength();
            for (int i = 0; i < size; i++) {
                Node firstStNode = nl.item(i);
                if (firstStNode.getNodeType() == Node.ELEMENT_NODE) {

                    User user = new User();

                    Element firstStElement = (Element) firstStNode;

                    user.setID(Integer.parseInt(firstStElement.getAttribute("id")));
                    user.setAge(Integer.parseInt(firstStElement.getAttribute("age")));

                    NodeList nameList = firstStElement.getElementsByTagName("name");
                    Element firstNameElement = (Element) nameList.item(0);
                    NodeList textNameList = firstNameElement.getChildNodes();
                    user.setName(textNameList.item(0).getNodeValue().trim());

                    NodeList surnameList = firstStElement.getElementsByTagName("surname");
                    Element lastNameElement = (Element) surnameList.item(0);
                    NodeList textSurnameList = lastNameElement.getChildNodes();
                    user.setSurname(textSurnameList.item(0).getNodeValue().trim());

                    NodeList cityList = firstStElement.getElementsByTagName("city");
                    Element cityElement = (Element) cityList.item(0);
                    NodeList textCityList = cityElement.getChildNodes();
                    user.setCity(textCityList.item(0).getNodeValue().trim());


                    UserList.this.add(user);


                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void saveToXML(String fname, Document xmldoc) {

        try {
            File xmlOutputFile = new File(fname);
            FileOutputStream fos = new FileOutputStream(xmlOutputFile);

            //создать и настроить преобразователь
            TransformerFactory transfac = TransformerFactory.newInstance();
            Transformer trans = transfac.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            //запустить преобразователь
            StreamResult result = new StreamResult(fname);
            DOMSource source = new DOMSource(xmldoc);
            trans.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public AbstractTableModel getTableModel() {
        return new AbstractTableModel() {
            public int getRowCount() {
                return UserList.this.size();
            }

            public int getColumnCount() {
                return 5;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                Object[][] userlist_data = new Object[UserList.this.size()][5];
                Object[] user_data = new Object[]{UserList.this.get(rowIndex).getID(),
                        UserList.this.get(rowIndex).getName(),
                        UserList.this.get(rowIndex).getSurname(),
                        UserList.this.get(rowIndex).getAge(),
                        UserList.this.get(rowIndex).getCity()};
                for (int i = 0; i < UserList.this.size(); i++) {
                    System.arraycopy(user_data, 0, userlist_data[i], 0, 5);
                }
                return userlist_data[rowIndex][columnIndex];

            }
        };
    }

    public void loadFromDataBase(String url, String login, String password) {
        try {
            UserList.this.clear();
            Connection con = DriverManager.getConnection(url, login, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT*FROM user");
            while (rs.next()) {
                User newUser = new User();
                newUser.setID(Integer.parseInt(rs.getString(1)));
                newUser.setName(rs.getString(2));
                newUser.setSurname(rs.getString(3));
                newUser.setAge(Integer.parseInt(rs.getString(4)));
                newUser.setCity(rs.getString(5));
                UserList.this.add(newUser);

            }
            st.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveToDataBase(String url, String login, String password) {
        try {
            Connection con = DriverManager.getConnection(url, login, password);
            Statement stm = con.createStatement();
            for (User user : this) {
                String savecom = "INSERT INTO user VALUES ('" + user.getID() + "', '" + user.getName() + "', '" + user.getSurname() + "', '" + user.getAge() + "', '" + user.getCity() + "')";
//                System.out.println(savecom);
                stm.execute(savecom);
            }
            stm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}