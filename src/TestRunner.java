import java.util.Date;

/**
 * Created by Denis on 19.12.2016.
 */
public class TestRunner {

    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date.getDate());
        System.out.println(1 + date.getMonth());
        System.out.println(1900 + date.getYear());
        System.out.println(date);
    }

}
