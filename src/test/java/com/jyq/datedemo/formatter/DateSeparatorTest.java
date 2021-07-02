package com.jyq.datedemo.formatter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateSeparatorTest {

    @Test
    public void test_YMD_SEPARATOR_EXP() {
        String str = "2021/6/25";
        Matcher m  = Pattern.compile(DateSeparator.YMD_SEPARATOR_EXP.getExpression()).matcher(str);
        m.find();
        Assert.assertEquals(m.group(0),"/");
    }


    @Test
    public void test_DMY_SEPARATOR_EXP() {
        String str = "25.6.2021";
        Matcher m  = Pattern.compile(DateSeparator.DMY_SEPARATOR_EXP.getExpression()).matcher(str);
        m.find();
        Assert.assertEquals(m.group(0),".");
    }


    @Test
    public void test_MDY_SEPARATOR_EXP() {
        String str = "6-25-2021";
        Matcher m  = Pattern.compile(DateSeparator.MDY_SEPARATOR_EXP.getExpression()).matcher(str);
        m.find();
        Assert.assertEquals(m.group(0),"-");

    }

}
