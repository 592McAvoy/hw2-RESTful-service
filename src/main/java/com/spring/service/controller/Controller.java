package com.spring.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.service.domain.WordLadder;

@RestController
public class Controller {

    @RequestMapping
    public String index(){
        return "Hello World!";
    }

    @RequestMapping(value="/run")
    public String Run(@RequestParam(required=false,defaultValue="dog")String w1,
                      @RequestParam(required=false,defaultValue="cat")String w2){
        WordLadder wl = new WordLadder();
        wl.setWord(w1,w2);
        wl.run();
        return wl.getLadder();
    }

}
