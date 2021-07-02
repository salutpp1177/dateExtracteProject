package com.jyq.datedemo.parser;

import com.jyq.datedemo.formatter.DatePattern;
import com.jyq.datedemo.formatter.DateSeparator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessor extends Processor {
    private final static int ARRAY_LENGTH = 3;
    private final static int CENTURY = 100;
    public TextProcessor() {
        super();
    }

    @Override
    public void processText(List<String> list) {
        //Parse text into util.Extractor
        if (!list.isEmpty()) {
            for (String str : list) {
                for (final DatePattern pattern : DatePattern.values()) {
                    final Matcher m = Pattern.compile(pattern.getPattern()).matcher(str);
                    this.insertCountingMap(pattern.name(), m);
                }
            }
        }
    }

    /**
     * According to different pattern, insert into counting map
     * @param patternName name of pattern
     * @param m
     */
    private void insertCountingMap(String patternName, Matcher m) {
        String type = patternName.substring(0,3);
        switch (type) {
            case "YMD":
                if (patternName.startsWith("YMD_NUMERIC")) {
                    if (patternName.endsWith("LONG")) {
                        // YMD_NUMERIC_EXP_LONG
                        while (m.find()) {
                            String s = m.group(0);
                            Calendar cal = this.convertNumericLongDateString(s, DateSeparator.YMD_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        }
                    } else if (patternName.endsWith("SHORT")) {
                        // YMD_NUMERIC_EXP_SHORT
                        while (m.find()) {
                            String s = m.group(0);
                            Calendar cal = this.convertNumericShortDateString(s, DateSeparator.YMD_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        }
                    } else if (patternName.endsWith("ONLY")) {
                        // YMD_NUMERIC_EXP_ONLY
                        while (m.find()) {
                            String s = m.group(0);
                            Calendar cal = new GregorianCalendar(Integer.parseInt(s.substring(0, 4)), Integer.parseInt(s.substring(4, 6)) - 1, Integer.parseInt(s.substring(6)));
                            this.setCountingMap(cal);
                        }
                    }
                } else {
                    while (m.find()) {
                        // YMD_CLASSIC_EXP_EN
                        if (patternName.endsWith("EN")) {
                            String s = m.group(0);
                            Calendar cal = this.convertClassicDateStringEnglish(s, DateSeparator.YMD_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        } else if (patternName.endsWith("FR")) {
                            // YMD_CLASSIC_EXP_FR
                            String s = m.group(0);
                            Calendar cal = this.convertClassicDateStringFrench(s, DateSeparator.YMD_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        }
                    }

                }
                break;
            case "DMY":
                if (patternName.startsWith("DMY_NUMERIC")) {
                    // DMY_NUMERIC_EXP_CONS, MY_NUMERIC_EXP_DIFF
                    while (m.find()) {
                        String s = m.group(0);
                        Calendar cal = this.convertNumericLongDateString(s, DateSeparator.DMY_SEPARATOR_EXP.getExpression(),type);
                        this.setCountingMap(cal);
                    }
                } else {
                    if (patternName.endsWith("EN")) {
                        while (m.find()) {
                            // DMY_CLASSIC_EXP_EN
                            String s = m.group(0);
                            Calendar cal = this.convertClassicDateStringEnglish(s, DateSeparator.DMY_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        }
                    } else if (patternName.endsWith("FR")) {
                        while (m.find()) {
                            // DMY_CLASSIC_EXP_FR
                            String s = m.group(0);
                            Calendar cal = this.convertClassicDateStringFrench(s, DateSeparator.DMY_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        }
                    }

                }
                break;
            case "MDY":
                if (patternName.startsWith("MDY_NUMERIC")) {
                    while (m.find()) {
                        String s = m.group(0);
                        Calendar cal = this.convertNumericLongDateString(s, DateSeparator.MDY_SEPARATOR_EXP.getExpression(),type);
                        this.setCountingMap(cal);
                    }
                } else {
                    if (patternName.endsWith("EN")) {
                        //MDY_CLASSIC_EN
                        while (m.find()) {
                            String s = m.group(0);
                            Calendar cal = this.convertClassicDateStringEnglish(s, DateSeparator.MDY_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        }
                    } else if (patternName.endsWith("FR")){
                        //MDY_CLASSIC_FR
                        while (m.find()) {
                            String s = m.group(0);
                            Calendar cal = this.convertClassicDateStringEnglish(s, DateSeparator.MDY_SEPARATOR_EXP.getExpression(),type);
                            this.setCountingMap(cal);
                        }
                    }

                }
                break;
            default:break;
        }
    }

    /**
     * Convert long numeric string into a date, yyyy for year
     * @param str origin numeric string who presents the date information
     * @param sep_exp separator's regular expression
     * @param type string's type YMD,MDY,DMY
     * @return
     */
    public Calendar convertNumericLongDateString(String str, String sep_exp, String type) {
        String[] date = str.split(sep_exp);
        Calendar calendar = null;
        try {
            if (date.length == ARRAY_LENGTH) {
                switch (type) {
                    case "YMD":
                        calendar = new GregorianCalendar(Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
                        break;
                    case "DMY":
                        calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
                        break;
                    case "MDY":
                        calendar = new GregorianCalendar(Integer.parseInt(date[2]),Integer.parseInt(date[0]) - 1,Integer.parseInt(date[1]));
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong data input");
        }

        return calendar;
    }

    /**
     * Convert long numeric string into a date, yy for year
     * @param str origin numeric string who presents the date information
     * @param sep_exp separator's regular expression
     * @param type string's type YMD,MDY,DMY
     * @return
     */
    public Calendar convertNumericShortDateString(String str, String sep_exp, String type) {
        String[] date = str.split(sep_exp);
        Calendar calendar = null;
        try {
            if (date.length == ARRAY_LENGTH) {
                switch (type) {
                    case "YMD":
                        calendar = new GregorianCalendar(formatYearNumber(Integer.parseInt(date[0])), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]));
                        break;
                    case "DMY":
                        calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, formatYearNumber(Integer.parseInt(date[0])));
                        break;
                    case "MDY":
                        calendar = new GregorianCalendar(Integer.parseInt(date[2]),Integer.parseInt(date[0]) - 1, formatYearNumber(Integer.parseInt(date[1])));
                        break;
                    default:
                        break;
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Wrong data input");
        }

        return calendar;
    }

    /**
     * format YEAR numbers, yy into yyyy
     * @param y
     * @return
     */
    private int formatYearNumber(int y) {
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        if (y <= cal.get(Calendar.YEAR) % CENTURY) {
            y += (cal.get(Calendar.YEAR) / CENTURY) * CENTURY;
        } else {
            y += (cal.get(Calendar.YEAR) / CENTURY - 1) * CENTURY;
        }

        return y;
    }


    /**
     * Convert classic string into a date
     * @param str origin classic string who presents the date information
     * @param sep_exp separator's regular expression
     * @param type string's type YMD,MDY,DMY
     * @return
     */
    public Calendar convertClassicDateStringEnglish(String str, String sep_exp, String type) {
        String[] strings = str.split(sep_exp);
        Calendar calendar = null;
        try {
            if (strings.length == ARRAY_LENGTH) {
                StringBuilder sb = new StringBuilder();
                switch (type) {
                    case "YMD":
                        sb.append(strings[0]).append('-').append(strings[1].substring(0,3)).append('-').append(strings[2]);
                        break;
                    case "DMY":
                        sb.append(strings[2]).append('-').append(strings[1].substring(0,3)).append('-').append(strings[0]);
                        break;
                    case "MDY":
                        sb.append(strings[2]).append('-').append(strings[0].substring(0,3)).append('-').append(strings[1]);
                        break;
                    default:
                        break;
                }
                Date date = new SimpleDateFormat("yyyy-MMM-dd",Locale.ENGLISH).parse(sb.toString());
                calendar = Calendar.getInstance();
                calendar.setTime(date);
            }
        }   catch (ParseException e) {
            System.out.println("Wrong data input");
        }
        return calendar;
    }


    public Calendar convertClassicDateStringFrench(String str, String sep_exp, String type) {
        String[] strings = str.split(sep_exp);
        Calendar calendar = null;
        try {
            if (strings.length == ARRAY_LENGTH) {
                StringBuilder sb = new StringBuilder();
                switch (type) {
                    case "YMD":
                        sb.append(strings[0]).append('-').append(this.getFrenchMonth(strings[1])).append('-').append(strings[2]);
                        break;
                    case "DMY":
                        sb.append(strings[2]).append('-').append(this.getFrenchMonth(strings[1])).append('-').append(strings[0]);
                        break;
                    case "MDY":
                        sb.append(strings[2]).append('-').append(this.getFrenchMonth(strings[0])).append('-').append(strings[1]);
                        break;
                    default:
                        break;
                }
                Date date = new SimpleDateFormat("yyyy-MMMM-dd",Locale.FRENCH).parse(sb.toString());
                calendar = Calendar.getInstance();
                calendar.setTime(date);
            }
        }   catch (ParseException e) {
            System.out.println("Wrong data input");
        }
        return calendar;
    }

    private String getFrenchMonth(String str) {
        String[] arr = str.toLowerCase(Locale.FRENCH).trim().split("[\\-\\/\\.,]");
        String[] months = {"janvier","février","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","décembre"};
        String head = arr[0].substring(0,3);
        String res = null;
        switch (head){
            case "jan":res = months[0]; break;
            case "fév":res = months[1]; break;
            case "mar":res = months[2]; break;
            case "avr":res = months[3]; break;
            case "mai":res = months[4]; break;
            case "jui": res =  head.startsWith("juil") ? months[6] : months[5]; break;
            case "aoû":res = months[7]; break;
            case "sep":res = months[8]; break;
            case "oct":res = months[9]; break;
            case "nov":res = months[10]; break;
            case "déc":res = months[11]; break;
            default:break;
        }
        return res;
    }




}
