package s06.z04;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


import org.w3c.dom.*;


public class zadacha04 {

    private static UserList ul = new UserList();
    private static int row = -1;
    private static JTable table;

    public static void main(String[] args) {

        TableLayout tl = new TableLayout(new double[]{0.25, 0.25, 0.25, 0.25},
                new double[]{0.8, 0.1, 0.1});
        final JFrame f = new JFrame();
        f.setSize(500, 300);
        f.setLocationRelativeTo(null);
        f.setLayout(tl);
        f.setResizable(false);

        final JButton loadxml = new JButton("Load XML");
        final JButton savelist = new JButton("Save list");
        final JButton deluser = new JButton("Delete user"); //and redraw table
        final JButton loaddb = new JButton("Load DB");
        final JButton savedb = new JButton("Save DB");

//        final JFileChooser fc = new JFileChooser();
        table = new JTable(ul.getTableModel());

//        table.setModel(ul.getTableModel());

        loadxml.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML-���� (*.xml)", "xml");
                JFileChooser fc = new JFileChooser(".");
                fc.addChoosableFileFilter(filter);

                int returnv = fc.showOpenDialog(fc);

                if (returnv == JFileChooser.APPROVE_OPTION) {
                    ul.loadFromXML(fc.getSelectedFile().getPath());
                    table();
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

                    Element root = xmldoc.createElement("users");
                    xmldoc.appendChild(root);

                    for (int s = 0; s < ul.size(); s++) {

                        Element student = xmldoc.createElement("user");
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
                    table();
                } else {
                    System.out.println("Choose a row to delete");
                }

            }
        });

        loaddb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ul.loadFromDataBase("jdbc:mysql://db4free.net/perfilovajava", "perfilova", "12345678");
                table();
            }
        });

        savedb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ul.saveToDataBase("jdbc:mysql://db4free.net/perfilovajava", "perfilova", "12345678");
                table();
            }
        });

        f.add(new JScrollPane(table), "0,0,3,0");
        f.add(loadxml, "0,1");
        f.add(savelist, "1,1,2,1");
        f.add(deluser, "3,1");
        f.add(loaddb, "0,2,1,2");
        f.add(savedb, "2,2,3,2");
        f.setVisible(true);
    }

    private static void table() {
        table.setModel(ul.getTableModel());

        for (int i = 0; i < 5; i++) {
            String[] columnNames = {"ID", "Name", "Surname", "Age", "City"};
            table.getColumnModel().getColumn(i).setHeaderValue(columnNames[i]);
        }

        ListSelectionModel tipListSelectionModel = table.getSelectionModel();
        tipListSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent ev) {
                ListSelectionModel lsm = (ListSelectionModel) ev.getSource();
                row = lsm.getMinSelectionIndex();
            }
        });
    }

}

