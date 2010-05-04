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
                    System.out.print("�� ");
                    break;
                case 'd':
                case 'D':
                    System.out.print("�� ");
                    break;
                case 'e':
                case 'E':
                    System.out.print("�� ");
                    break;
                case 'f':
                case 'F':
                    System.out.print("�� ");
                    break;
                case 'g':
                case 'G':
                    System.out.print("���� ");
                    break;
                case 'a':
                case 'A':
                    System.out.print("�� ");
                    break;
                case 'b':
                case 'B':
                    System.out.print("�� ");
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
                        System.out.print("������ ");
                        break;
                    case '#':
                        System.out.print("���� ");
                        break;

                }
            }*/


            int dlit = Integer.parseInt(mat.group(3));
            switch (dlit) {
                case 16:
                    System.out.println("������������ ");
                    break;
                case 8:
                    System.out.println("�������� ");
                    break;
                case 4:
                    System.out.println("�������� ");
                    break;
                case 2:
                    System.out.println("�������� ");
                    break;
            }

        }


    }
}
