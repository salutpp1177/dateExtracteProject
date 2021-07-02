package com.jyq.datedemo.mapper;

import com.jyq.datedemo.parser.Processor;
import com.jyq.datedemo.pojo.DateEntry;

import java.util.*;
import java.util.stream.Collectors;

public class TextMapper {
    private List<String> inputList;
    private List<DateEntry> outputList;

    public List<String> getInputList() {
        return inputList;
    }

    public void setInputList(List<String> inputList) {
        this.inputList = inputList;
    }

    public void setInputList(String input) {
        this.inputList = new ArrayList<>();
        if (!input.isEmpty()) {
            String[] lines = input.split("[\\r\\n]+");
            Collections.addAll(this.inputList, lines);
        }
    }


    public List<DateEntry> getOutputList() {
        return outputList;
    }

    public void setOutputList(List<DateEntry> outputList) {
        this.outputList = outputList;
    }




    public void setOutputList(Processor processor) {
        List<DateEntry> entryList = new ArrayList<>();
        // convert map into list
        if (!processor.getCountingMap().isEmpty()) {
            for (Map.Entry<Calendar, Integer> entry :   processor.getCountingMap().entrySet()) {
                if (entry.getKey()!=null) {
                    entryList.add(new DateEntry(entry.getKey(),entry.getValue()));
                }
            }
        }
        // sort list and insert
        if (this.outputList==null) {
            this.outputList = entryList.stream()
                    .sorted(Comparator.comparingLong(DateEntry::getCalendarInMillis))
                    .collect(Collectors.toList());

        } else {
            List<DateEntry> dummy = entryList.stream()
                    .sorted(Comparator.comparingLong(DateEntry::getCalendarInMillis))
                    .collect(Collectors.toList());
            this.outputList.addAll(dummy);
        }

    }

}
