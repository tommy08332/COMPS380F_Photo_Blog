package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.exception.EmailAlreadyUsed;
import hkmu.comps380f.group16.exception.PhoneNumberAlreadyUsed;
import hkmu.comps380f.group16.exception.UserAccountAlreadyExists;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String create(@ModelAttribute("createPhotoUser") @Valid applicationForm appForm, BindingResult result, ModelMap model)
            throws IOException,
            UserAccountAlreadyExists,
            EmailAlreadyUsed,
            PhoneNumberAlreadyUsed {

        if (result.hasErrors()){

            return "registration";

        }

        usersService.createUserAccount(appForm.getUsername(),
                appForm.getPassword(),
                appForm.getPhoneNum(),
                appForm.getEmail(),
                appForm.getUserRole());

        model.addAttribute("isAccountCreated", true);

        return "redirect:/login";

    }

    @ExceptionHandler({UserAccountAlreadyExists.class,
            EmailAlreadyUsed.class,
            PhoneNumberAlreadyUsed.class})
    public ModelAndView error(Exception e){

        return new ModelAndView("error", "err_message", e.getMessage());
    }

    public class applicationForm{

        @NotEmpty(message = "Username cannot be empty.")
        private String username;

        @NotEmpty(message = "Password cannot be empty.")
        @Size(min=6, max = 20, message = "Password length must be between {min} and {max}")
        private String password;

        @NotEmpty(message = "Phone number cannot be empty.")
        @Size(min=5, max = 9, message = "Phone number length must be between {min} and {max}")
        private String phoneNum;

        @NotEmpty(message = "Email cannot be empty, please enter your email.")
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
