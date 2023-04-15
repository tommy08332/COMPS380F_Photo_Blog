package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private PhotoBlogUsersService usersService;

    @GetMapping("/panel")
    public String adminPage(ModelMap model){

        List<PhotoBlogUsers> users = usersService.findAllUsers();

        model.addAttribute("users", users);


        return "admin_page";


    }

}


