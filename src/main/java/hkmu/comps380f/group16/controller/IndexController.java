package hkmu.comps380f.group16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {



//    @Resource
//    PhotoBlogUsersService usersService;

//    @Resource
//    UsersRepository rep;

    @GetMapping("/")
    public String index(){



        return "index";




    }



    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }


}
