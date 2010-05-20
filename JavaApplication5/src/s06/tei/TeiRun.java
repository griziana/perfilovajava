package s06.tei;

import info.clearthought.layout.TableLayout;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TeiRun {

    private static String title = "";
    private static String author = "";
    private static String number = "";
    private static String date = "";

    public static void main(String[] args) {


        final JFrame frame = new JFrame("HTML to TBE");
        frame.setBounds(0, 0, 400, 500);
        final double border = 10;
        double size[][] = {{border, TableLayout.FILL, border, TableLayout.FILL, border, TableLayout.FILL, border},
                {border, 30, border, TableLayout.FILL, border}};

        frame.setLayout(new TableLayout(size));
        frame.setResizable(false);

        JButton Load = new JButton("Load");
        JButton Save = new JButton("Save");
        JButton Convert = new JButton("Convert");
        final JTextPane textPane = new JTextPane();

        Load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String jtptext = "";
                FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.htm)", "htm", "*.html", "html");
                JFileChooser fc = new JFileChooser(".");
                fc.addChoosableFileFilter(filter);
                fc.showOpenDialog(fc);
                try {
                    File file = fc.getSelectedFile();

                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String s = "";
                    int c;
                    while ((c = br.read()) != -1) s += (char) c;
                    jtptext = jtptext.concat(s + "\n");
                    br.close();

                } catch (Exception ioe) {
                    System.out.println(ioe.getMessage());
                }
                textPane.setText(jtptext);
            }
        });

        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("(*.txt)", "txt");
                JFileChooser fc = new JFileChooser(".");
                fc.addChoosableFileFilter(filter);
                int option = fc.showSaveDialog(fc);
                try {
                    File file = fc.getSelectedFile();
                    BufferedWriter br = new BufferedWriter(new FileWriter(file));
                    br.write(textPane.getText());
                    br.close();
                } catch (Exception ioe) {
                    System.out.println(ioe.getMessage());
                }
                if (option == JFileChooser.APPROVE_OPTION) {
                }
            }
        });

        Convert.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                try {

                    String content0 = textPane.getText();
                    Pattern p1 = Pattern.compile("(<table(.*)><tr><td bgcolor=\"#e4e1d9\"><b>)(.*)(\\s*;\\s*</b>)(.*)(\\s*;\\s*)(.*)(\\s*;\\s*</td></tr></table><h3>)(.*)(\\s*</h3>)");
                    Matcher m1 = p1.matcher(content0);
                    while (m1.find()) {

                        title = m1.group(9);
                        author = m1.group(3);
                        number = m1.group(7);
                        date = m1.group(5);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }


                converter(textPane, "(<table(.*)><tr><td bgcolor=\"#e4e1d9\"><b>)(.*)(\\s*;\\s*</b>)(.*)(\\s*;\\s*)(.*)(\\s*;\\s*</td></tr></table><h3>)(.*)(\\s*</h3>)",
                        "<teiHeader>\n<fileDesc>\n<<teiHeader>\n" +
                                " <fileDesc>\n" +
                                " <titleStmt>\n" +
                                " <title>" + title + "</title>\n" +
                                " <author>" + author + "</author>\n" +
                                " </titleStmt>\n" +
                                " <publicationStmt>\n" +
                                "<idno type=\"number\">" + number + "</idno>\n" +
                                " <publisher/>\n" +
                                " <date>" + date + "</date>\n" +
                                " </publicationStmt>\n" +
                                " <sourceDesc>\n" +
                                " <p/>\n" +
                                " </sourceDesc>\n" +
                                " </fileDesc>\n" +
                                " </teiHeader>");


                converter(textPane, "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">",
                        "<TEI xmlns=\"http://www.tei-c.org/ns/1.0\">");
                converter(textPane, "(<head>)(.*)(</head>)", "\n");
                converter(textPane, "<html>", "");
                converter(textPane, "</html>", "</TEI>");
                converter(textPane, "<body>", "\n");
                converter(textPane, "</body>", "\n");
                converter(textPane, "<pre>", "\n\n<text>\n<body>\n<p>");
                converter(textPane, "</pre>", "\n\n</p>\n</body>\n</text>");
                converter(textPane, "<b>", "");
                converter(textPane, "</b>", "");
                converter(textPane, "<font(.*)>", "<hi>");
                converter(textPane, "</font>", "</hi>");


            }
        });

        JScrollPane scr = new JScrollPane(textPane);
        frame.getContentPane().add(scr, "1, 3, 5, 3");

        frame.add(Load, "1, 1");
        frame.add(Convert, "3, 1");
        frame.add(Save, "5, 1");


        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static void converter(JTextPane textPane, String oldTag, String newTag) {
        String content = textPane.getText();
        Pattern p4 = Pattern.compile(oldTag);
        Matcher m4 = p4.matcher(content);
        while (m4.find()) {
            content = m4.replaceAll(newTag);
        }
        textPane.setText(content);
    }


}


