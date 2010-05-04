package s06.z03;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import org.w3c.dom.*;


public class zadacha03 {

    private static UserList ul = new UserList();
    private static int row = -1;
    private static JTable table;

    public static void main(String[] args) {

        TableLayout tl = new TableLayout(new double[]{0.5, 0.5},
                new double[]{0.3, 0.3, 0.3});
        final JFrame f = new JFrame();
        f.setSize(350, 300);
        f.setLocationRelativeTo(null);
        f.setLayout(tl);
        f.setResizable(false);

        final JButton loadxml = new JButton("Load XML");
        final JButton savelist = new JButton("Save list");
        final JButton deluser = new JButton("Delete user"); //and redraw table
        final JFileChooser fc = new JFileChooser();
        table = new JTable(ul.getTableModel());

//        table.setModel(ul.getTableModel());

        loadxml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int returnv = fc.showOpenDialog(null);
                if (returnv == JFileChooser.APPROVE_OPTION) {
                    try {
                        ul = UserList.loadFromXML(fc.getSelectedFile().getName());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    Table();


                } else {
                    System.out.println("To continue work choose the XML file.");
                }
            }
        });

        savelist.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                    DocumentBuilder db = dbf.newDocumentBuilder();
                    Document xmldoc = db.newDocument();

                    Element root = xmldoc.createElement("students");
                    xmldoc.appendChild(root);

                    for (int s = 0; s < ul.size(); s++) {

                        Element student = xmldoc.createElement("student");
                        student.setAttribute("id", Integer.toString(ul.get(s).getID()));
                        student.setAttribute("age", Integer.toString(ul.get(s).getAge()));
                        root.appendChild(student);

                        Element name = xmldoc.createElement("name");
                        student.appendChild(name);
                        Text text_name = xmldoc.createTextNode(ul.get(s).getName());
                        name.appendChild(text_name);

                        Element surname = xmldoc.createElement("surname");
                        student.appendChild(surname);
                        Text text_surname = xmldoc.createTextNode(ul.get(s).getSurname());
                        surname.appendChild(text_surname);

                        Element city = xmldoc.createElement("city");
                        student.appendChild(city);
                        Text text_city = xmldoc.createTextNode(ul.get(s).getCity());
                        city.appendChild(text_city);
                    }

                    xmldoc.getDocumentElement().normalize();
                    ul.saveToXML("userlist.xml", xmldoc);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        deluser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (row != -1) {
                    ul.remove(row);
                    Table();
                } else {
                    System.out.println("Choose a row to delete");
                }

            }
        });

        f.add(new JScrollPane(table), "0,0,0,2");
        f.add(loadxml, "1,0");
        f.add(savelist, "1,1");
        f.add(deluser, "1,2");
        f.setVisible(true);
    }

    private static void Table() {
        table.setModel(ul.getTableModel());

        for (int i = 0; i < 5; i++) {
            String[] columnNames = {"ID", "Name", "Surname", "Age", "City"};
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }

        ListSelectionModel tipListSelectionModel = table.getSelectionModel();
        tipListSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                ListSelectionModel lsm = (ListSelectionModel) evt.getSource();
                row = lsm.getMinSelectionIndex();
            }
        });
    }

}
