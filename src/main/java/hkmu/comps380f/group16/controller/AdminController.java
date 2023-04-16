package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.UserNotFound;
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

    @GetMapping("/panel/user")
    public String userManagement(ModelMap model, Principal principal, HttpServletRequest request)
            throws UserNotFound{

        PhotoBlogUsers findUser = usersService.findUser(principal.getName());

        if (!request.isUserInRole("ROLE_ADMIN") && !principal.getName().equals(findUser.getUsername())){

            return "redirect:/";

        }

        List<PhotoBlogUsers> users = usersService.findAllUsers();

        model.addAttribute("users", users);

        return "user_management";

    }

    @GetMapping({"/","/panel/", "/panel"})
    public String localDirectory(){
        return "redirect:/admin/panel/user";
    }

    @GetMapping("/panel/edit/user/{userId:.+}")
    public ModelAndView editUser(@PathVariable("userId") String userId, ModelMap model)
            throws UserNotFound {

        PhotoBlogUsers user = usersService.findUserById(userId);


        model.addAttribute("user", user);

        return new ModelAndView(
                "edit",
                "editPhotoUser",
                new editForm()
        );

    }

    @PostMapping("/panel/edit/user/{userId:.+}")
    public String editUser(@PathVariable("userId") String userId,
                           editForm form, Principal principal, HttpServletRequest request) throws UserNotFound{

        PhotoBlogUsers findUser = usersService.findUser(principal.getName());

        if (!request.isUserInRole("ROLE_ADMIN") && !principal.getName().equals(findUser.getUsername())){

            return "redirect:/";

        }

        usersService.updateUser(userId,
                                form.getUsername(),
                                form.getPassword(),
                                form.getEmail(),
                                form.getPhoneNum(),
                                form.getUserDescription(),
                                form.getUserRole());

        return "redirect:/admin/panel";

    }

    @GetMapping("/panel/delete/user/{userId:.+}")
    public String deleteUser(@PathVariable("userId") String userId,
                             Principal principal, HttpServletRequest request) throws UserNotFound{

        PhotoBlogUsers findUser = usersService.findUser(principal.getName());

        if (!request.isUserInRole("ROLE_ADMIN") && !principal.getName().equals(findUser.getUsername())){

            return "redirect:/";

        }


        usersService.deleteUser(userId);
        return "redirect:/admin/panel";
    }

    @GetMapping("/panel/history")
    public String adminPage(ModelMap model, Principal principal, HttpServletRequest request)
            throws UnsupportedEncodingException, UserNotFound {


        PhotoBlogUsers findUser = usersService.findUser(principal.getName());

        if (!request.isUserInRole("ROLE_ADMIN") && !principal.getName().equals(findUser.getUsername())){

            return "redirect:/";

        }

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

        private String userId;

        private String username;
        private String password;

        private String phoneNum;

        private String email;

        private String userDescription;

        private String[] userRole;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

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

}
