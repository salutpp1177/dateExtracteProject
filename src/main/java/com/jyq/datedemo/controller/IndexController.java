package com.jyq.datedemo.controller;

import com.jyq.datedemo.engine.Recogonizer;
import com.jyq.datedemo.pojo.DateEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class IndexController {



    @GetMapping
    public String index() {
        return "index";
    }
    @PostMapping
    public ModelAndView inputText(@RequestParam(value = "inputtext")String text, HttpSession session) {
        Recogonizer recogonizer = new Recogonizer();
        recogonizer.recogonizeText(text);
        List<DateEntry> list = recogonizer.getTextMapper().getOutputList();
        ModelAndView res = new ModelAndView("result");
        res.addObject("datelist",list);
        return res;
    }

    @RequestMapping
    public String result(Model model) {
        return "result";
    }
}
