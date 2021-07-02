package com.jyq.datedemo.formatter;

import java.util.regex.Pattern;

public enum DatePattern {
    /**
     * for YMD-numeric-format date,
     * long means year is presented by yyyy, short means year is presented by yy
     * YMD_NUMERIC_EXP_LONG supports the following date formats :
     * "yyyy MM dd",   "yyyy-MM-dd",  "yyyy/MM/dd",    "yyyy/M/d", "yyyy.MM.dd"
     * YMD_NUMERIC_EXP_SHORT supports the following date formats :
     * "yy m d"),   "yy/MM/dd", "yy/M/d"
     */
    YMD_NUMERIC_EXP_LONG("(?:[1-2][0-9]{3})((?:[\\-/\\.]|[^\\S\\r\\n]))(?:1[0-2]|0?[1-9])\\1(?:3[01]|2[0-9]|1[0-9]|0?[1-9])"),
    YMD_NUMERIC_EXP_SHORT("\\d{2}(/|[^\\S\\r\\n])(?:1[0-2]|0?[1-9])\\1(?:3[01]|2[0-9]|1[0-9]|0?[1-9])"),
    YMD_NUMERIC_EXP_ONLY("\\d{8}"), // "yyyyMMdd"
    /**
     * for YMD-classic-format, month words are always different according to each language
     * EN : English month words
     * YMD_CLASSIC_EXP_EN supports the following date formats
     * "yyyy MMM dd",    "yyyy MMMM d",  "yyyy-MMM-dd",    "yyyy-MMMM-d",
     * "yyyy/MMM/dd",    "yyyy/MMMM/dd",   "yyyy.MMM.dd"
     */
    YMD_CLASSIC_EXP_EN("(?:[1-2][0-9]{3})([\\-/\\.]|[^\\S\\r\\n])(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|(Nov|Dec)(?:ember)?)\\1(?:3[01]|2[0-9]|1[0-9]|0?[1-9])"),
    YMD_CLASSIC_EXP_FR("(?:[1-2][0-9]{3})([\\-/\\.]|[^\\S\\r\\n])(?:(?:[jJ]anvier|[jJ]anv\\.?)|(?:[fF]évrier|[fF]évr\\.?)|[mM]ars|(?:[aA]vril|[aA]vr\\.?)|[mM]ai|[jJ]uin|(?:[jJ]uillet|[jJ]uill?\\.?)|[aA]oût|(?:[sS]eptembre|[sS]ept\\.?)|(?:[oO]ctobre|[oO]ct\\.?)|(?:[nN]ovembre|[nN]ov\\.?)|(?:[dD]écembre|[dD]éc\\.?))\\1(?:3[01]|2[0-9]|1[0-9]|0?[1-9])"),
    /**
     * for DMY-numeric-format
     * EXP_CONS  the separators between day&month and month&year are consistent
     * EXP_DIFF  the separators between day&month and month&year are different
     */
    DMY_NUMERIC_EXP_CONS("(?:3[01]|2[0-9]|1[0-9]|0?[1-9])(([\\-/])|\\.[^\\S\\r\\n]?)(?:1[0-2]|0?[1-9])\\1(?:[1-2][0-9]{3})"),
    DMY_NUMERIC_EXP_DIFF("(?:3[01]|2[0-9]|1[0-9]|0?[1-9])/(?:1[0-2]|0?[1-9])(?:-\\d{2}|[^\\S\\r\\n][1-2][0-9]{3})"),

    /**
     * for DMY-classic-format, month words are always different according to each language
     * EN   English month words
     */
    DMY_CLASSIC_EXP_EN("(?:3[01]|2[0-9]|1[0-9]|0?[1-9])(-|\\.?[^\\S\\r\\n])(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|(Nov|Dec)(?:ember)?)(,?[^\\S\\r\\n]|[\\-])(?:[1-2][0-9]{3})"),
    DMY_CLASSIC_EXP_FR("(?:3[01]|2[0-9]|1[0-9]|0?[1-9])(-|\\.?[^\\S\\r\\n])(?:(?:[jJ]anvier|[jJ]anv\\.?)|(?:[fF]évrier|[fF]évr\\.?)|[mM]ars|(?:[aA]vril|[aA]vr\\.?)|[mM]ai|[jJ]uin|(?:[jJ]uillet|[jJ]uill?\\.?)|[aA]oût|(?:[sS]eptembre|[sS]ept\\.?)|(?:[oO]ctobre|[oO]ct\\.?)|(?:[nN]ovembre|[nN]ov\\.?)|(?:[dD]écembre|[dD]éc\\.?))(,?[^\\S\\r\\n]|[\\-])(?:[1-2][0-9]{3})"),
    /**
     * for MDY-numeric-format
     */
    MDY_NUMERIC_EXP("(?:1[0-2]|0?[1-9])(([\\-/])|\\.[^\\S\\r\\n]?)(?:3[01]|2[0-9]|1[0-9]|0?[1-9])\\1(?:[1-2][0-9]{3})"),
    /**
     * for MDY-classic-format, month words are always different according to each language
     * EN   English month words
     * FR French
     */
    MDY_CLASSIC_EXP_EN("(?:Jan(?:uary)?|Feb(?:ruary)?|Mar(?:ch)?|Apr(?:il)?|May|Jun(?:e)?|Jul(?:y)?|Aug(?:ust)?|Sep(?:tember)?|Oct(?:ober)?|(Nov|Dec)(?:ember)?)(?:[^\\S\\r\\n]|[\\-/])(?:3[01]|2[0-9]|1[0-9]|0?[1-9])(?:,?[^\\S\\r\\n]|[\\-/])[1-2][0-9]{3}"),
    MDY_CLASSIC_EXP_FR("(?:(?:[jJ]anvier|[jJ]anv\\.?)|(?:[fF]évrier|[fF]évr\\.?)|[mM]ars|(?:[aA]vril|[aA]vr\\.?)|[mM]ai|[jJ]uin|(?:[jJ]uillet|[jJ]uill?\\.?)|[aA]oût|(?:[sS]eptembre|[sS]ept\\.?)|(?:[oO]ctobre|[oO]ct\\.?)|(?:[nN]ovembre|[nN]ov\\.?)|(?:[dD]écembre|[dD]éc\\.?))(?:[^\\S\\r\\n]|[\\-/])(?:3[01]|2[0-9]|1[0-9]|0?[1-9])(?:,?[^\\S\\r\\n]|[\\-/])[1-2][0-9]{3}"),
    ;

    private final String pattern;

    DatePattern(String exp) {
        this.pattern = exp;
    }

    public String getPattern() {
        return pattern;
    }
}
