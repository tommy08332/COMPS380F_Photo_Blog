package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.CommentsService;
import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.*;
import hkmu.comps380f.group16.model.Comments;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

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
    public String userManagement(ModelMap model, Principal principal, HttpServletRequest request)
        throws UserNotFound{

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return "redirect:/";

        }

        List<PhotoBlogUsers> users = usersService.findAllUsers();

        model.addAttribute("users", users);


        return "user_management";


    }

    @GetMapping({"/panel/user/", "/panel/","/panel" , "/"})
    public String userManagement(){

        return "redirect:/admin/panel/user";

    }


    @GetMapping({"/panel/registration", "/panel/registration/"})
    public String create() {

        return "redirect:/admin/panel/registration/create";
    }

    @GetMapping("/panel/registration/create")
    public ModelAndView create(Principal principal, HttpServletRequest request)
    throws UserNotFound{

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return new ModelAndView(new RedirectView("/", true));

        }

        return new ModelAndView(
                "registration",
                "createPhotoUser",
                new applicationForm()
        );
    }

    @PostMapping("/panel/registration/create")
    public String create(@ModelAttribute("createPhotoUser") @Valid
             applicationForm appForm, BindingResult result,
                         Principal principal, HttpServletRequest request)
            throws UserAccountAlreadyExists, EmailAlreadyUsed, PhoneNumberAlreadyUsed, UserNotFound {

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return "redirect:/";

        }

        if (result.hasErrors()){

            return "registration";

        }

        usersService.createUserAccount(appForm.getUsername(),
                                       appForm.getPassword(),
                                       appForm.getPhoneNum(),
                                       appForm.getEmail(),
                                       appForm.getUserRole());

        return "redirect:/admin/panel/user";

    }


    @GetMapping("/panel/edit/user/{username:.+}")
    public ModelAndView editUser(@PathVariable("username") String username,
             ModelMap model, Principal principal, HttpServletRequest request)
            throws UserNotFound {

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        PhotoBlogUsers targetUser = usersService.findUser(username);

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return new ModelAndView(new RedirectView("/", true));

        }



        model.addAttribute("user", targetUser);

        return new ModelAndView(
                "edit",
                "editPhotoUser",
                new editForm()
        );

    }

    @PostMapping("/panel/edit/user/{username:.+}")
    public String editUser(@PathVariable("username") String username, editForm form,
                           Principal principal, HttpServletRequest request)
            throws UserNotFound{

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return "redirect:/";
        }


        usersService.updateUser(form.getUsername(),
                                form.getPassword(),
                                form.getEmail(),
                                form.getPhoneNum(),
                                form.getUserDescription(),
                                form.getUserRole());

        return "redirect:/admin/panel/user";

    }

    @GetMapping("/panel/delete/user/{username:.+}")
    public String deleteUser(@PathVariable("username") String username,
                             Principal principal, HttpServletRequest request)

            throws PhotoNotFound, UserNotFound, CommentsNotFound{

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return "redirect:/";

        }

        List<Comments> commentsList = commentsService.findUserAllComments(username);
        for(Comments comment : commentsList){
            photosService.deleteComment(comment.getPhotoId(), comment.getCommentId());
        }

        photosService.deletePhoto(username);

        usersService.deleteUser(username);

        return "redirect:/admin/panel/user";
    }

    @GetMapping("/panel/history")
    public String adminPage(ModelMap model, Principal principal, HttpServletRequest request)
            throws UnsupportedEncodingException, UserNotFound {

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

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

    // delete photo
    @GetMapping("/panel/history/delete/photo/{photoId:.+}")
    public String deletePhoto(@PathVariable("photoId") String photoId,
                              Principal principal, HttpServletRequest request)
            throws PhotoNotFound, UserNotFound{

        PhotoBlogUsers user = usersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return "redirect:/";

        }

        photosService.deletePhotoById(photoId);

        return "redirect:/admin/panel/history";


    }


    @ExceptionHandler({UserNotFound.class, UserAccountAlreadyExists.class,
            EmailAlreadyUsed.class, PhoneNumberAlreadyUsed.class,
            PhotoNotFound.class, CommentsNotFound.class})
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

    public static class applicationForm{

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

        @NotEmpty(message = "You must select at least 1 role.")
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
