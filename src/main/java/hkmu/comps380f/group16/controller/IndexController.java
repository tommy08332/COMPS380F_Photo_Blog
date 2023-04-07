package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Resource
    PhotoBlogUsersService usersService;

    @GetMapping("/")
    public String index(){

        // for test create and find user

//        PhotoBlogUsersService userService = new PhotoBlogUsersService();

//        String [] roles = {"Admin", "Normal User"};
//
//        usersService.createUserAccount("helloWorld", "P@ssw0rd", roles);
//


        return "index";

    }

    @GetMapping("/photo")
    public String photo(){

        return "photo";

    }

    @GetMapping("/upload_photo")
    public String uploadPhoto(){

        return "upload_photo";
    }

    @GetMapping("/profile")
    public String profile(){

        return "profile";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/admin_page")
    public String adminPage(){
        return "admin_page";
    }

}
