/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package s06.z01;

import info.clearthought.layout.TableLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author Shadow2
 */
public class zadacha01 {

    public static void main(String[] args) {
        TableLayout t1 = new TableLayout(new double[]{0.5, 0.5}, new double[]{TableLayout.FILL});
        JFrame f = new JFrame();
        f.setSize(700, 300);
        f.setLayout(t1);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextArea vyvod = new JTextArea();
        JButton knopka = new JButton("I want to get the list of txt-files! FOR FREE and NOW!");
        knopka.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                File[] files = new File("c:/windows").listFiles(
                        new FileFilter() {

                            public boolean accept(File f) {
                                if (f.isFile()) {
                                    String a = f.getName();
                                    return a.matches(".*\\.txt");
                                } else {
                                    return false;
                                }
                            }
                        });

                for (File fs : files) {
                    String a = fs.getName();
                    vyvod.append(a);
                    vyvod.append("\n");
                }

            }
        });

        f.add(vyvod, "0,0");
        f.add(knopka, "1,0");
        f.setVisible(true);
    }
}
