package s06.z06;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class zadacha06 extends JPanel {
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLUE);

        Calendar c = new GregorianCalendar();
        double m = c.get(GregorianCalendar.MINUTE);
        double h = c.get(GregorianCalendar.HOUR);
        double s = c.get(GregorianCalendar.SECOND);

        int x0 = 150;
        int y0 = 150;
        int R = 100;

        for (int i = 0; i < 12; i++) {
            g.drawLine(
                    evalX(x0, y0, 100, 30*i),
                    evalY(x0, y0, 100, 30*i),
                    evalX(x0, y0, 90, 30*i),
                    evalY(x0, y0, 90, 30*i)
            );
        }


        g.drawOval(x0 - R, y0 - R, 2 * R, 2 * R);
        g.drawLine(
                x0,
                y0,
                evalX(x0, y0, 40, (h * 60 + m) / 2),
                evalY(x0, y0, 40, (h * 60 + m) / 2)
                //(int) Math.round(x0 + 40 * Math.sin(((h * 60 + m) / 2) / 180 * Math.PI)),
                //(int) Math.round(y0 - 40 * Math.cos(((h * 60 + m) / 2) / 180 * Math.PI))
        );

        g.drawLine(
                x0,
                y0,
                evalX(x0, y0, 70, (s * 6)),
                evalY(x0, y0, 70, (s * 6))
                /*(int) Math.round(x0 + 70 * Math.sin((s * 6) / 180 * Math.PI)),
                (int) Math.round(y0 - 70 * Math.cos((s * 6) / 180 * Math.PI))*/
        );
        g.drawLine(
                x0,
                y0,
                evalX(x0, y0, 60, (m * 6)),
                evalY(x0, y0, 60, (m * 6))
                /*(int) Math.round(x0 + 60 * Math.sin((m * 6) / 180 * Math.PI)),
                (int) Math.round(y0 - 60 * Math.cos((m * 6) / 180 * Math.PI))*/
        );
    }

    public static void main(String[] args) {
        TableLayout t1 = new TableLayout(new double[]{TableLayout.FILL},
                new double[]{TableLayout.FILL});
        final zadacha06 c1 = new zadacha06();
        JFrame f = new JFrame();
        f.setLayout(t1);
        f.setSize(300, 350);
        f.add(c1, "0,0");
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                c1.repaint();
            }
        });
        t.start();

    }

    private int evalX(int x0, int y0, int r, double ang) {
        return (int) Math.round(x0 + r * Math.sin(ang / 180 * Math.PI));
    }

    private int evalY(int x0, int y0, int r, double ang) {
        return (int) Math.round(y0 - r * Math.cos(ang / 180 * Math.PI));
    }
}
