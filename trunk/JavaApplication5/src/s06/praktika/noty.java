package s06.praktika;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class noty {
    public static void main(String[] args) {
        String m = "C#8 db16 A8";
        Pattern pat = Pattern.compile("([a-gA-G])(b|#|)(\\d+)");
        Matcher mat = pat.matcher(m);
        while (mat.find()) {
            char letter = mat.group(1).charAt(0);
            switch (letter) {
                case 'c':
                case 'C':
                    System.out.print("до ");
                    break;
                case 'd':
                case 'D':
                    System.out.print("ре ");
                    break;
                case 'e':
                case 'E':
                    System.out.print("ми ");
                    break;
                case 'f':
                case 'F':
                    System.out.print("фа ");
                    break;
                case 'g':
                case 'G':
                    System.out.print("соль ");
                    break;
                case 'a':
                case 'A':
                    System.out.print("ля ");
                    break;
                case 'b':
                case 'B':
                    System.out.print("си ");
                    break;
            }

            if (mat.group(2).equals("#"))
                System.out.print("");
            else if (mat.group(2).equals("b"))
                System.out.print("");

            /*if (mat.group(2).length() != 0) {
                char znak = mat.group(2).charAt(0);
                switch (znak) {
                    case 'b':
                        System.out.print("бемоль ");
                        break;
                    case '#':
                        System.out.print("диез ");
                        break;

                }
            }*/


            int dlit = Integer.parseInt(mat.group(3));
            switch (dlit) {
                case 16:
                    System.out.println("шестнадцатая ");
                    break;
                case 8:
                    System.out.println("четверть ");
                    break;
                case 4:
                    System.out.println("четверть ");
                    break;
                case 2:
                    System.out.println("четверть ");
                    break;
            }

        }


    }
}
