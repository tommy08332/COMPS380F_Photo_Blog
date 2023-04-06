package hkmu.comps380f.group16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        return "index";

    }

    @GetMapping("/photo")
    public String photo(){

        return "photo";

    }

    @GetMapping("/register")
    public String registration(){

        return "registration";

    }

    @GetMapping("/login")
    public String login(){

        return "login";

    }
}
