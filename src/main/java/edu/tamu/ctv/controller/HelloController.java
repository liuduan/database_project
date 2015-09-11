package edu.tamu.ctv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marian_Mykhalchuk on 9/11/2015.
 */
@Controller
public class HelloController {
    @RequestMapping("/")
    public String hello() {
        return "index";
    }
}
