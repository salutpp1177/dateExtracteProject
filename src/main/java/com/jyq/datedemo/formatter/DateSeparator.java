package com.jyq.datedemo.formatter;

public enum DateSeparator {
    /**
     * Using regular expression to split the date String for each date format
     */
    YMD_SEPARATOR_EXP("[^\\S\\r\\n]|[\\-\\./]"),
    DMY_SEPARATOR_EXP("[^\\S\\r\\n]|[\\-/]|\\.[^\\S\\r\\n]?"),
    MDY_SEPARATOR_EXP(",?[^\\S\\r\\n]|[\\-/]"),
    ;
    private final String expression;

    DateSeparator(String expression) {
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }
}
