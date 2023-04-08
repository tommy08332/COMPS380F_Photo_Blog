package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.applicationForm;
import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.exception.UserNotFound;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Resource
    PhotoBlogUsersService usersService;


    @GetMapping("/create")
    public ModelAndView create(){

        return new ModelAndView("registration",
                               "createPhotoUser",
                                new applicationForm());
    }

//    @PostMapping("/create")
//    public View create(applicationForm appForm) throws IOException, UserNotFound {
//
//        usersService.createUserAccount(appForm.getUsername(),
//                                       appForm.getPassword(),
//                                       appForm.getUserRole());
//
//        return new RedirectView("/PhotoBlog/login");
//
//    }

//    For test
//    @GetMapping("/result")
//    public String result(ModelMap model){
//
//        model.addAttribute("photoUser", usersService.findAllUsers());
////        For test
//        return "redirect:/login";
//    }



}
