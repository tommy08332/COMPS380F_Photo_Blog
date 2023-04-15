package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.exception.EmailAlreadyUsed;
import hkmu.comps380f.group16.exception.PhoneNumberAlreadyUsed;
import hkmu.comps380f.group16.exception.UserAccountAlreadyExists;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/")
    public String localDirectory(){

        return "redirect:/registration/create";
    }

    @GetMapping("/create")
    public ModelAndView create(){

        return new ModelAndView(
                "registration",
                "createPhotoUser",
                new applicationForm()
        );
    }

    @PostMapping("/create")
    public View create(applicationForm appForm)
            throws IOException,
            UserAccountAlreadyExists,
            EmailAlreadyUsed,
            PhoneNumberAlreadyUsed {

        usersService.createUserAccount(appForm.getUsername(),
                appForm.getPassword(),
                appForm.getPhoneNum(),
                appForm.getEmail(),
                appForm.getUserRole());


        return new RedirectView("/PhotoBlog/login");

    }

    @ExceptionHandler({UserAccountAlreadyExists.class,
            EmailAlreadyUsed.class,
            PhoneNumberAlreadyUsed.class})
    public ModelAndView error(Exception e){

        return new ModelAndView("error", "err_message", e.getMessage());
    }

    public class applicationForm{

        private String username;
        private String password;

        private String phoneNum;

        private String email;
        private String[] userRole;

        public String getUsername() {

            return username;
        }

        public void setUsername(String username) {

            this.username = username;
        }

        public String getPassword() {

            return password;
        }

        public void setPassword(String password) {

            this.password = password;
        }

        public String getPhoneNum() {
            return phoneNum;
        }

        public void setPhoneNum(String phoneNum) {
            this.phoneNum = phoneNum;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String[] getUserRole() {

            return userRole;
        }

        public void setUserRole(String[] userRole) {

            this.userRole = userRole;
        }
    }

}