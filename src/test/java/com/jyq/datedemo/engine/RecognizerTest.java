package com.jyq.datedemo.engine;

import com.jyq.datedemo.pojo.DateEntry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecognizerTest {

    @Test
    public void recogonizeEnglishTest() {
        String text = "Marvin Lee Minsky at the Mathematics Genealogy Project; 20 May 2014\n" +
                "Marvin Lee Minsky at the AI Genealogy Project. {reprint 18 September 2011)\n" +
                "\"Personal page for Marvin Minsky\". web.media.mit.edu. Retrieved 23 June 2016.\n" +
                "Admin (January 27, 2016). \"Official Alcor Statement Concerning Marvin Minsky\".\n" +
                "\tAlcor Life Extension Foundation. Retrieved 2016-04-07.\n" +
                "\"IEEE Computer Society Magazine Honors Artificial Intelligence Leaders\".\n" +
                "\tDigitalJournal.com. August 24, 2011. Retrieved September 18, 2011.\n" +
                "\tPress release source: PRWeb (Vocus).\n" +
                "\"Dan David prize 2014 winners\". May 15, 2014. Retrieved May 20, 2014.";
        Recogonizer recogonizer = new Recogonizer();
        recogonizer.recogonizeText(text);
        List<DateEntry> list = recogonizer.getTextMapper().getOutputList();
        Assert.assertEquals(list.get(0).getCalendar(),new GregorianCalendar(2011,8-1,24));
        Assert.assertEquals(list.get(1).getCalendar(),new GregorianCalendar(2011,9-1,18));
        Assert.assertEquals(list.get(2).getCalendar(),new GregorianCalendar(2014,5-1,15));
        Assert.assertEquals(list.get(3).getCalendar(),new GregorianCalendar(2014,5-1,20));
        Assert.assertEquals(list.get(4).getCalendar(),new GregorianCalendar(2016,1-1,27));
        Assert.assertEquals(list.get(5).getCalendar(),new GregorianCalendar(2016,4-1,7));
        Assert.assertEquals(list.get(6).getCalendar(),new GregorianCalendar(2016,6-1,23));

        Assert.assertEquals(list.get(0).getTimes(),1);
        Assert.assertEquals(list.get(1).getTimes(),2);
        Assert.assertEquals(list.get(2).getTimes(),1);
        Assert.assertEquals(list.get(3).getTimes(),2);
        Assert.assertEquals(list.get(4).getTimes(),1);
        Assert.assertEquals(list.get(5).getTimes(),1);
        Assert.assertEquals(list.get(6).getTimes(),1);
    }



}
