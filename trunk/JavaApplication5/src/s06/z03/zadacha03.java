package s06.z03;

import info.clearthought.layout.TableLayout;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: 07Perfilova
 * Date: 09.04.2010
 * Time: 12:11:53
 * To change this template use File | Settings | File Templates.
 */
public class zadacha03 {
    public static void main(String[] args) {
        UserList.loadFromXML("usersdb.xml");

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
    }
}
