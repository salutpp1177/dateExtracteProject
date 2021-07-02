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
public class DatePatternTest {

    @Test
    public void test_YMD_NUMERIC_EXP_LONG() {
        String str = "YMD_NUMERIC_4_SHORT(\"yyyy/M/d\"),    // ex. 2021/6/25";
        Matcher m  = Pattern.compile(DatePattern.YMD_NUMERIC_EXP_LONG.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }

    @Test
    public void test_YMD_NUMERIC_EXP_SHORT() {
        String str = "YMD_NUMERIC_7_LONG(\"yy/MM/dd\"), // ex. 21/06/25";
        Matcher m = Pattern.compile(DatePattern.YMD_NUMERIC_EXP_SHORT.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }

    @Test
    public void test_YMD_CLASSIC_EXP_EN() {
        String str = "YMD_CLASSIC_2_LONG(\"yyyy-MMMM-dd\"), // ex. 2021-June-12";
        Matcher m = Pattern.compile(DatePattern.YMD_CLASSIC_EXP_EN.getPattern()).matcher(str);
        Assert.assertTrue(m.find());

        String s2 = "1998 July 16";
        Matcher m2 = Pattern.compile(DatePattern.YMD_CLASSIC_EXP_EN.getPattern()).matcher(s2);
        Assert.assertTrue(m2.find());
    }

    @Test
    public void test_YMD_NUMERIC_EXP_ONLY() {
        String str = "YMD_NUMERIC_1(\"yyyyMMdd\"),  // ex. 20210625 ";
        Matcher m = Pattern.compile(DatePattern.YMD_NUMERIC_EXP_ONLY.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }

    @Test
    public void test_DMY_NUMERIC_EXP_CONS() {
        String str = "DMY_NUMERIC_1_LONG(\"d/M/yyyy\"),   // ex. 15/6/2000";
        Matcher m = Pattern.compile(DatePattern.DMY_NUMERIC_EXP_CONS.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }

    @Test
    public void test_DMY_NUMERIC_EXP_DIFF() {
        String str = "DMY_NUMERIC_5(\"d/M yyyy\"),  // 15/11 1999";
        Matcher m = Pattern.compile(DatePattern.DMY_NUMERIC_EXP_DIFF.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }

    @Test
    public void test_DMY_CLASSIC_EXP_EN() {
        String str = "DMY_CLASSIC_1_LONG(\"d MMMM yyyy\"),  // ex. 6 July 2001";
        Matcher m = Pattern.compile(DatePattern.DMY_CLASSIC_EXP_EN.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }

    @Test
    public void test_MDY_CLASSIC_EXP_EN() {
        String str = "MDY_CLASSIC_2_LONG(\"MMMM d, yyyy\"),    // ex. January 1, 2001";
        Matcher m = Pattern.compile(DatePattern.MDY_CLASSIC_EXP_EN.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }

    @Test
    public void test_YMD_CLASSIC_EXP_FR() {
        String str = "YMD_CLASSIC_2_LONG(\"yyyy-MMMM-dd\"), // ex. 2021-janvier-1";
        Matcher m = Pattern.compile(DatePattern.YMD_CLASSIC_EXP_FR.getPattern()).matcher(str);
        Assert.assertTrue(m.find());

        String s2 = "1998 Févr 16";
        Matcher m2 = Pattern.compile(DatePattern.YMD_CLASSIC_EXP_FR.getPattern()).matcher(s2);
        Assert.assertTrue(m2.find());
    }

    @Test
    public void test_DMY_CLASSIC_EXP_FR() {
        String str = "DMY_CLASSIC_1_LONG(\"d MMMM yyyy\"),  // ex. 6 Mai 2001";
        Matcher m = Pattern.compile(DatePattern.DMY_CLASSIC_EXP_FR.getPattern()).matcher(str);
        Assert.assertTrue(m.find());

        String s2 = "16 févr. 1999";
        Matcher m2 = Pattern.compile(DatePattern.DMY_CLASSIC_EXP_FR.getPattern()).matcher(s2);
        Assert.assertTrue(m2.find());
    }

    @Test
    public void test_MDY_CLASSIC_EXP_FR() {
        String str = "MDY_CLASSIC_2_LONG(\"MMMM d, yyyy\"),    // ex. Mai 1, 2001";
        Matcher m = Pattern.compile(DatePattern.MDY_CLASSIC_EXP_FR.getPattern()).matcher(str);
        Assert.assertTrue(m.find());
    }




}
