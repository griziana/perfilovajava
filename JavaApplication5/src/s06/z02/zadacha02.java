package s06.z02;

import info.clearthought.layout.TableLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * @author Shadow2
 */
public class zadacha02 {

    public static void main(String[] args) {

        TableLayout tl1 = new TableLayout(new double[]{0.3, 0.3, 0.3, 0.127}, new double[]{0.25, 0.25, 0.25, 0.25});
        JFrame f = new JFrame();
        f.setSize(650, 350);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(tl1);


        final JTextField field1 = new JTextField();
        final JTextField field2 = new JTextField();
        final JTextField field3 = new JTextField();
        JButton findb = new JButton("Find");
        final JTextPane editb = new JTextPane();
        JButton uploadb = new JButton("Upload"); //into TextPane
        JButton saveb = new JButton("Save");
        JButton loadb = new JButton("Load");

        findb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                StyledDocument sd = editb.getStyledDocument();
                SimpleAttributeSet emptySas = new SimpleAttributeSet();
                sd.setCharacterAttributes(0, sd.getLength(), emptySas, true);

                Pattern pat1 = Pattern.compile(field1.getText());
                Matcher mat1 = pat1.matcher(editb.getText());
                while (mat1.find()) {
                    SimpleAttributeSet sas = new SimpleAttributeSet();
                    sas.addAttribute(StyleConstants.Bold, true);
                    sd.setCharacterAttributes(mat1.start(), mat1.end() - mat1.start(), sas, false);
                }

                Pattern pat2 = Pattern.compile(field2.getText());
                Matcher mat2 = pat2.matcher(editb.getText());
                while (mat2.find()) {
                    SimpleAttributeSet sas = new SimpleAttributeSet();
                    sas.addAttribute(StyleConstants.Background, new Color(100, 120, 222));
                    sd.setCharacterAttributes(mat2.start(), mat2.end() - mat2.start(), sas, false);
                }

                Pattern pat3 = Pattern.compile(field3.getText());
                Matcher mat3 = pat3.matcher(editb.getText());
                while (mat3.find()) {
                    SimpleAttributeSet sas = new SimpleAttributeSet();
                    sas.addAttribute(StyleConstants.Italic, true);
                    sd.setCharacterAttributes(mat3.start(), mat3.end() - mat3.start(), sas, false);
                }
            }
        });

        saveb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    PrintStream ps = new PrintStream("G:/java/expressions.txt");
                    ps.println(field1.getText());
                    ps.println(field2.getText());
                    ps.println(field3.getText());
                } catch (FileNotFoundException e1) {
                    System.out.println("Can't write into the file");
                }
            }
        });

        loadb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {               
                try {
                    BufferedReader br = new BufferedReader(new FileReader("G:/java/expressions.txt"));
                    String line= br.readLine();
                    while (line != null) {
                        System.out.println(line);
                    }
                    br.close();
                } catch (IOException e2) {
                    System.out.println("Can't load from the file");
                }
            }
        });

        f.add(field1,
                "0,3");
        f.add(field2,
                "1,3");
        f.add(field3,
                "2,3");
        f.add(findb,
                "3,0");

        f.add(editb,
                "0,0,2,2");

        f.add(uploadb,
                "3,1");
        f.add(saveb,
                "3,2");
        f.add(loadb,
                "3,3");
        f.setVisible(
                true);

    }
}
