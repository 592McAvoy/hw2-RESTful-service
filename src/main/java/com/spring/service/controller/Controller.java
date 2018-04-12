package com.spring.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.service.domain.WordLadder;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

    @RequestMapping(value="/")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return model;
    }

    @RequestMapping(value="/run")
    public String Run(@RequestParam(required=false,defaultValue="dog")String w1,
                      @RequestParam(required=false,defaultValue="cat")String w2){
        WordLadder wl = new WordLadder();
        wl.setWord(w1,w2);
        wl.run();
        return wl.getLadder();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "不正确的用户名和密码");
        }
        if (logout != null) {
            model.addObject("msg", "你已经成功退出");
        }
        model.setViewName("login");
        return model;
    }

}
