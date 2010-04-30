package s06.z03;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class zadacha03 {

    public static void main(String[] args) {
        try {
            UserList ul = UserList.loadFromXML("usersdb.xml");
            for (User user:ul) {
                System.out.println(user);
            }
        } catch (IOException e) {
            System.out.println("failed to load XML");
        }

        TableLayout tl = new TableLayout(new double[]{0.5, 0.5},
                new double[]{0.3, 0.3, 0.3});
        final JFrame f = new JFrame();
        f.setSize(350, 300);
        f.setLocationRelativeTo(null);
        f.setLayout(tl);
        f.setResizable(false);

        JButton loadxml = new JButton("Load XML");
        JButton savelist = new JButton("Save list");
        JButton deluser = new JButton("Delete user"); //and redraw table

        JTable table = new JTable();

        f.add(new JScrollPane(table), "0,0,0,2");
        f.add(loadxml, "1,0");
        f.add(savelist, "1,1");
        f.add(deluser, "1,2");

        table.setModel(new AbstractTableModel() {

            public int getRowCount() {
                return 5;
            }

            public int getColumnCount() {
                return 3;
            }

            public Object getValueAt(int rowIndex, int columnIndex) {
                return rowIndex * columnIndex;
            }
        });

        f.setVisible(true);
    }
}
