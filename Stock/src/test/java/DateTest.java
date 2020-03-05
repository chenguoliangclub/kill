import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
public class DateTest {

    @Test
    public void test(){
        Date parse = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-02-20 20:20:20", new ParsePosition(0));
        System.out.println(parse);
    }
}
