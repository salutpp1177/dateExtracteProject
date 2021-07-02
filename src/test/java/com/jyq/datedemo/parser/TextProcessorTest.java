package com.jyq.datedemo.parser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TextProcessorTest {

    @Test
    public void testProcessText() {
        TextProcessor ep = new TextProcessor();
        String[] arr = {"YMD_NUMERIC_3(\"yyyy-MM-dd\"),    // ex. 2021-06-25",
                "DMY_CLASSIC_4_LONG(\"d MMMM, yyyy\"),  // ex. 6 June, 2000",
                "MDY_CLASSIC_2_LONG(\"MMMM d, yyyy\"),    // ex. January 1, 2001",
                "MDY_CLASSIC_1_SHORT(\"MMM-dd-yyyy\"),   //  ex. Jan-01-2001" };
        List<String> list = Arrays.asList(arr);
        ep.processText(list);

        Assert.assertEquals(3,ep.getCountingMap().size());
        Calendar c1 = new GregorianCalendar(2021,6-1,25);
        Assert.assertTrue(ep.getCountingMap().containsKey(c1));
        Calendar c2 = new GregorianCalendar(2025,6-1,21);
        Assert.assertFalse(ep.getCountingMap().containsKey(c2));
        Calendar c3 = new GregorianCalendar(2000,6-1,6);
        Assert.assertTrue(ep.getCountingMap().get(c3) == 1);
        Calendar c4 = new GregorianCalendar(2001,1-1,1);
        Assert.assertTrue(ep.getCountingMap().get(c4) == 2);
    }

}
