package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.CommentsService;
import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.*;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private PhotoBlogUsersService usersService;

    @Resource
    private PhotosService photosService;

    @Resource
    CommentsService commentsService;

    @GetMapping("/panel/user")
    public String userManagement(ModelMap model){

        List<PhotoBlogUsers> users = usersService.findAllUsers();

        model.addAttribute("users", users);


        return "user_management";


    }

    @GetMapping("/panel/registration")
    public String create(Principal principal, HttpServletRequest request)
        throws UserNotFound{

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (!request.isUserInRole("ROLE_ADMIN") && principal.getName().equals(user.getUsername())){

            return "redirect:/";

        }
        return "redirect:/panel/registration/create";
    }

    @GetMapping("/panel/registration/create")
    public ModelAndView create(){

        return new ModelAndView(
                "registration",
                "createPhotoUser",
                new applicationForm()
        );
    }

    @PostMapping("/panel/registration/create")
    public String create(applicationForm appForm, ModelMap model)
            throws UserAccountAlreadyExists, EmailAlreadyUsed, PhoneNumberAlreadyUsed {

        usersService.createUserAccount(appForm.getUsername(),
                                       appForm.getPassword(),
                                       appForm.getPhoneNum(),
                                       appForm.getEmail(),
                                       appForm.getUserRole());

        return "redirect:/admin/panel/user";

    }


    @GetMapping("/panel/edit/user/{username:.+}")
    public ModelAndView editUser(@PathVariable("username") String username, ModelMap model)
            throws UserNotFound {

        PhotoBlogUsers user = usersService.findUser(username);

        model.addAttribute("user", user);

        return new ModelAndView(
                "edit",
                "editPhotoUser",
                new editForm()
        );

    }

    @PostMapping("/panel/edit/user/{username:.+}")
    public String editUser(@PathVariable("username") String username, editForm form) throws UserNotFound{

        usersService.updateUser(form.getUsername(),
                                form.getPassword(),
                                form.getEmail(),
                                form.getPhoneNum(),
                                form.getUserDescription(),
                                form.getUserRole());

        return "redirect:/admin/panel/user";

    }

    @GetMapping("/panel/delete/user/{username:.+}")
    public String deleteUser(@PathVariable("username") String username)
            throws PhotoNotFound, UserNotFound, CommentsNotFound{

        commentsService.deleteUserAllComment(username);

        photosService.deletePhoto(username);

        usersService.deleteUser(username);

        return "redirect:/admin/panel/user";
    }

    @GetMapping("/panel/history")
    public String adminPage(ModelMap model) throws UnsupportedEncodingException {


        List<Photos> photos = photosService.findAllPhotos();

        model.addAttribute("photos", photos);


        ArrayList<String> filesArr = new ArrayList<String>();

        for (Photos photo : photos){

            byte [] imageByteArr = photo.getPhotoData();
            byte [] p = Base64.getEncoder().encode(imageByteArr);
            String photoImg = new String(p, "UTF-8");

            filesArr.add(photoImg);

        }

        model.addAttribute("fileContent", filesArr);


        return "upload_history";


    }

    @ExceptionHandler({UserNotFound.class})
    public ModelAndView error(Exception e){

        return new ModelAndView("error", "err_message", e.getMessage());
    }

    public class editForm{

        private String username;
        private String password;

        private String phoneNum;

        private String email;

        private String userDescription;

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

        public String getUserDescription() {
            return userDescription;
        }

        public void setUserDescription(String userDescription) {
            this.userDescription = userDescription;
        }

        public String[] getUserRole() {

            return userRole;
        }

        public void setUserRole(String[] userRole) {

            this.userRole = userRole;
        }
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
