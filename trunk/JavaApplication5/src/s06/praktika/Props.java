package s06.praktika;

import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Props {
    public HashMap<String, String> hm = new HashMap<String, String>();

    public void load(String fname) {
        File f = new File(fname);
        String line = null;
        try {
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                Pattern pat = Pattern.compile("(.*)->*(.*)");
                Matcher mat = pat.matcher(line);
                mat.matches();
                hm.put(mat.group(1).trim(), mat.group(2).trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        return hm.get(key);
    }

    public static void main(String[] args) {
        Props p = new Props();
        p.load("src/s06/praktika/x.txt");
        System.out.println(p.get("y"));
    }

}
