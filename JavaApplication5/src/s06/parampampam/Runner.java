package s06.parampampam;

import info.clearthought.layout.TableLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: 07Perfilova
 * Date: 14.05.2010
 * Time: 14:10:44
 */
public class Runner {
    private static ResourceBundle rb = ResourceBundle.getBundle("s06.parampampam.MyBundle");

    public static void main(String[] args) throws IOException {
        System.out.println(rb.getString("x"));
        System.out.println(rb.getString("y"));

        Image i = ImageIO.read(Runner.class.getResourceAsStream("bitter.jpg"));
        ImageIcon ic = new ImageIcon (i);

                   TableLayout tl1 = new TableLayout(new double[]{0.9, 0.1}, new double[]{0.9, 0.1});
        JFrame f = new JFrame();
        f.setSize(700, 500);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(tl1);
        JLabel jl = new JLabel(ic);

        f.add(jl,"0,0");
        f.setVisible(true);
    }

}
