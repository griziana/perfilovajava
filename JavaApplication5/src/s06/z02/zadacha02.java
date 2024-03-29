package s06.z02;

import info.clearthought.layout.TableLayout;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
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

        TableLayout tl1 = new TableLayout(new double[]{0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125, 0.125},
                new double[]{0.25, 0.25, 0.25, 0.25});
        JFrame f = new JFrame();
        f.setSize(800, 200);
        f.setLocationRelativeTo(null);

        f.setLayout(tl1);


        final JTextField field1 = new JTextField();
        final JTextField field2 = new JTextField();
        final JTextField field3 = new JTextField();
        final JButton findb = new JButton("Find regex");
        final JTextPane editpane = new JTextPane();
        final JButton uploadb = new JButton("Upload text"); //into TextPane
        final JButton saveb = new JButton("Save regex");
        final JButton loadb = new JButton("Load regex");
        final JFileChooser fc = new JFileChooser();
        f.addWindowListener(new WindowAdapter() {
            public void windowOpened(WindowEvent e) {
                loadb.doClick(1);
            }

            public void windowClosing(WindowEvent e) {
                saveb.doClick(1);
            }
        });

        // ���������� ��������� ������ � ������
        findb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                StyledDocument sd = editpane.getStyledDocument();
                SimpleAttributeSet emptySas = new SimpleAttributeSet();
                sd.setCharacterAttributes(0, sd.getLength(), emptySas, true);

                Pattern pat1 = Pattern.compile(field1.getText());
                Matcher mat1 = pat1.matcher(editpane.getText());
                while (mat1.find()) {
                    SimpleAttributeSet sas = new SimpleAttributeSet();
                    sas.addAttribute(StyleConstants.Bold, true);
                    sd.setCharacterAttributes(mat1.start(), mat1.end() - mat1.start(), sas, false);
                }

                Pattern pat2 = Pattern.compile(field2.getText());
                Matcher mat2 = pat2.matcher(editpane.getText());
                while (mat2.find()) {
                    SimpleAttributeSet sas = new SimpleAttributeSet();
                    sas.addAttribute(StyleConstants.Background, new Color(100, 120, 222));
                    sd.setCharacterAttributes(mat2.start(), mat2.end() - mat2.start(), sas, false);
                }

                Pattern pat3 = Pattern.compile(field3.getText());
                Matcher mat3 = pat3.matcher(editpane.getText());
                while (mat3.find()) {
                    SimpleAttributeSet sas = new SimpleAttributeSet();
                    sas.addAttribute(StyleConstants.Italic, true);
                    sd.setCharacterAttributes(mat3.start(), mat3.end() - mat3.start(), sas, false);
                }
            }
        });

        // �������� ������ � JTextPane �� �����
        uploadb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int returnv = fc.showOpenDialog(null);
                if (returnv == JFileChooser.APPROVE_OPTION) {
                    try {

                        FileReader fr = new FileReader(fc.getSelectedFile().getPath());
                        StringBuffer sb = new StringBuffer();
                        int symbol;
                        while ((symbol = fr.read()) > -1) {
                            sb.append((char) symbol);
                        }
                        fr.close();
                        editpane.setText(sb.toString());
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println(ex);
                    }
                    catch (IOException ex) {
                        System.out.println(ex);
                    }

                } else {
                    editpane.setText("Type the text by yourself");
                }

            }
        });

        // ������ ���������� ��������� � ����
        saveb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                PrintStream ps = null;
                try {
                    ps = new PrintStream("../expressions.txt");
                    ps.println(field1.getText());
                    ps.println(field2.getText());
                    ps.println(field3.getText());
                } catch (FileNotFoundException e1) {
                    System.out.println("Can't write into the file");
                } finally {
                    try {
                        ps.close();
                    }
                    catch (NullPointerException ex0) {
                        System.out.println(ex0);
                    }
                }
            }
        });

        // ������ ���������� ��������� �� �����
        loadb.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                BufferedReader br = null;
                String line;
                try {
                    br = new BufferedReader(new FileReader("../expressions.txt"));
                    line = br.readLine();
                    if (line != null)
                        field1.setText(line);

                    line = br.readLine();
                    if (line != null)
                        field2.setText(line);

                    line = br.readLine();
                    if (line != null)
                        field3.setText(line);

                } catch (IOException e2) {
                    System.out.println("Can't load from the file");
                }
                finally {
                    try {
                        if (br != null)
                            br.close();
                    } catch (IOException ex) {
                        System.out.println("ex");
                    }

                }
            }
        });

        f.add(editpane,
                "0,0,3,3");

        f.add(field1,
                "4,0,7,0");
        f.add(field2,
                "4,1,7,1");
        f.add(field3,
                "4,2,7,2");

        f.add(findb,
                "4,3");
        f.add(uploadb,
                "5,3");
        f.add(saveb,
                "6,3");
        f.add(loadb,
                "7,3");
        f.setVisible(
                true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
/*        class WindowListener extends WindowAdapter {

            public void windowOpened(WindowEvent e) {
                loadb.doClick(1);
            }

            public void windowClosed(WindowEvent e) {
                saveb.doClick();
            }

        }*/

    }
}
