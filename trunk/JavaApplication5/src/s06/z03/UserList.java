package s06.z03;

import org.w3c.dom.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class UserList extends ArrayList<User> {

    //   public static UserList loadFromXML(String fname) throws IOException {
    public void loadFromXML(String fname) {
        //       UserList ul = new UserList();
        UserList.this.clear();

        try {
            DocumentBuilderFactory bf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = bf.newDocumentBuilder();
            Document doc = db.parse(fname);
            doc.getDocumentElement().normalize();


            //           Element users = doc.getDocumentElement();
//            NodeList nl = users.getElementsByTagName("user");
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


/*                Element element = (Element) nl.item(i);
NodeList idnl = element.getElementsByTagName("id");
Element element2 = (Element) idnl.item(0); //элемент в id
Text idtxt = (Text) element2.getFirstChild(); //достал то, что внутри id
String id = idtxt.getTextContent(); //превратил в строку

NodeList namenl = element.getElementsByTagName("name");
Element element3 = (Element) namenl.item(0);
Text nametxt = (Text) element3.getFirstChild();
String name = nametxt.getTextContent();

NodeList surnamenl = element.getElementsByTagName("surname");
Element element4 = (Element) surnamenl.item(0);
Text surnametxt = (Text) element4.getFirstChild();
String surname = surnametxt.getTextContent();

NodeList agenl = element.getElementsByTagName("age");
Element element5 = (Element) agenl.item(0);
Text agetxt = (Text) element5.getFirstChild();
String age = agetxt.getTextContent();

NodeList citynl = element.getElementsByTagName("city");
Element element6 = (Element) citynl.item(0);
Text citytxt = (Text) element6.getFirstChild();
String city = citytxt.getTextContent();

User user = new User(
        Integer.parseInt(id),
        name,
        surname,
        Integer.parseInt(age),
        city
);
ul.add(user);*/

                }
            }
        } /*catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        }*/
        catch (Exception e) {
            e.printStackTrace();
        }


        //       return ul;
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
}
