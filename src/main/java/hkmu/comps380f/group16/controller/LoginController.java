package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.applicationForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/user")
    public ModelAndView create(){

        return new ModelAndView("login",
                "loginPhotoUser",
                new applicationForm());
    }

    @PostMapping("/user")
    public View create(applicationForm appForm) throws IOException {
        System.out.println("Running login");
//        usersService.createUserAccount(appForm.getUsername(),
//                appForm.getPassword(),
//                appForm.getUserRole());

        return new RedirectView("/PhotoBlog/login");

    }

}
