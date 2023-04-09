package hkmu.comps380f.group16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/user")
public class UserController {

    // for testing
    @GetMapping("/profile")
    // for testing

    // for testing
//    @GetMapping("/show/{username:.+}")
//    // for testing
//
//    public String profile(@PathVariable("username") String username){

    public String profile(){


        System.out.println("Running profile");
        System.out.println();
        return "profile";


    }


}
