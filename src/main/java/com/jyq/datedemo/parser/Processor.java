package com.jyq.datedemo.parser;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

public abstract class Processor {
    private HashMap<Calendar, Integer> countingMap;

    public Processor()  {
        this.countingMap = new HashMap<>();
    }

    public HashMap<Calendar, Integer> getCountingMap() {
        return countingMap;
    }

    public void setCountingMap(HashMap<Calendar, Integer> countingMap) {
        this.countingMap = countingMap;
    }

    public void setCountingMap(Calendar calendar) {
        if (this.countingMap.containsKey(calendar)) {
            this.countingMap.put(calendar,this.countingMap.get(calendar)+1);
        } else {
            this.countingMap.put(calendar,1);
        }
    }

    public abstract void processText(List<String> list);

}
