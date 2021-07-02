package com.jyq.datedemo.pojo;

import java.util.Calendar;

public class DateEntry {
    private Calendar calendar;
    private int times;

    public DateEntry(Calendar calendar, int times) {
        this.calendar = calendar;
        this.times = times;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public long getCalendarInMillis() {
        return this.getCalendar().getTimeInMillis();
    }
}
