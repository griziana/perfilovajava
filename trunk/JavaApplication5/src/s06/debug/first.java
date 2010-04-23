package s06.debug;

/**
 * Created by IntelliJ IDEA.
 * User: 07Perfilova
 * Date: 09.04.2010
 * Time: 11:36:17
 */
public class first {
    public static void main(String[] args) {
        int sum = 0;
        int a[] = {1, 3, 5, 4, 2};
        for(int i=0; i<a.length; i++){
           sum=sum+a[i];
            System.out.println("a["+i+"] = "+a[i]);
        }
        System.out.println("sum = "+sum);
        goodbye();
    }

    public static void goodbye() {
        System.out.println("Goodbye!");
    }
}
