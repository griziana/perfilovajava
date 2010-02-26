package s06.z00;

import info.clearthought.layout.TableLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class zadacha00 {

    public static void main(String[] args) {
        TableLayout tl1 = new TableLayout(new double[]{0.143, 0.143, 0.143, 0.143, 0.143, 0.143, 0.143}, new double[]{0.2, 0.2, 0.2, 0.2, 0.2});
        JFrame f = new JFrame();
        f.setSize(700, 500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(tl1);
        f.setVisible(true);

        JButton[] buttons = new JButton[12];
        for (int i = 0; i < 12; i++) {
            buttons[i] = new JButton("Task" + (i + 1));
            buttons[i].setBackground(Color.getHSBColor(280, 100, 100));
        }

        f.add(buttons[0], "0, 1");
        f.add(buttons[1], "2, 1");
        f.add(buttons[2], "4, 1");
        f.add(buttons[3], "6, 1");
        f.add(buttons[4], "1, 2");
        f.add(buttons[5], "3, 2");
        f.add(buttons[6], "5, 2");
        f.add(buttons[7], "0, 3");
        f.add(buttons[8], "2, 3");
        f.add(buttons[9], "4, 3");
        f.add(buttons[10], "6, 3");
        JTextField txt0 = new JTextField("by Daria Perfilova");
        txt0.setHorizontalAlignment(JTextField.CENTER);
        txt0.setFont(new Font("Georgia", Font.BOLD, 12));
        f.add(txt0, "0, 0, 6, 0");
        txt0.setBackground(Color.getHSBColor(280, 100, 100));

        JTextArea txt1 = new JTextArea();
        f.add(txt1, "0, 4, 6, 4");
        txt1.setBackground(Color.getHSBColor(280, 100, 100));

        buttons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                main (new String[0]);
            }
        });

        f.setVisible(true);
    }


}
