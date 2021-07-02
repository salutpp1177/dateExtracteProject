package com.jyq.datedemo.engine;


import com.jyq.datedemo.mapper.TextMapper;
import com.jyq.datedemo.parser.TextProcessor;
import com.jyq.datedemo.parser.Processor;

public class Recogonizer {
    private TextMapper textMapper;

    public TextMapper getTextMapper() {
        return textMapper;
    }

    public void setTextMapper(TextMapper textMapper) {
        this.textMapper = textMapper;
    }

    public void recogonizeText(String text) {
        // mapping input text
        this.textMapper = new TextMapper();
        textMapper.setInputList(text);
        this.recogonizeEnglish();
    }

    /**
     * Recogonizer all the dates from English text
     * @return
     */
    private void recogonizeEnglish(){
        // extract date from text, save into a map
        Processor processor = new TextProcessor();
        processor.processText(textMapper.getInputList());

        // convert map into list, sort it by date
        textMapper.setOutputList(processor);
    }



}
