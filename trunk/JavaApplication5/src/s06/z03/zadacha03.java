package s06.z03;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class zadacha03 {

    public static void main(String[] args) {
        try {
            UserList ul = UserList.loadFromXML("I:/java/JavaApplication5/src/s06/z03/usersdb.xml");
            for (User user:ul) {
                System.out.println(user);
            }
        } catch (IOException e) {
            System.out.println("failed to load XML");
        }

        TableLayout tl = new TableLayout(new double[]{0.25, 0.25, 0.25, 0.25},
                new double[]{0.25, 0.25, 0.25, 0.25});
        final JFrame f = new JFrame();
        f.setSize(640, 480);
        f.setLocationRelativeTo(null);
        f.setLayout(tl);
        f.setResizable(false);

        JButton loadxml = new JButton("Load XML");
        JButton savelist = new JButton("Save list");
        JButton deluser = new JButton("Delete user"); //and redraw table

        JTable table = new JTable();

        f.add(new JScrollPane(table), "0 0");

        table.setModel(new AbstractTableModel() {

            public int getRowCount() {
                return 10;
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
