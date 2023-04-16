package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.exception.UserNotFound;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.PhotoDetails;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    // for testing
    // for testing


    // for testing
//    @GetMapping("/show/{username:.+}")
//    // for testing
//
//    public String profile(@PathVariable("username") String username){

    @Resource
    private PhotosService photosService;

    @Resource
    private PhotoBlogUsersService BlogUsersService;
    @GetMapping("/profile")
    public View profile(Principal principal)
            throws UserNotFound {
        PhotoBlogUsers blogUsers = BlogUsersService.findUser(principal.getName());

        return new RedirectView("/user/profile/" + blogUsers.getUserId() ,true);

    }
    @GetMapping("/profile/{userId:.+}")
    public String view(@PathVariable("userId") String userId, ModelMap model )
            throws UserNotFound, PhotoNotFound, UnsupportedEncodingException {

        ArrayList<String> photoArr = new ArrayList<String>();

        // for store photo id
        ArrayList<Integer> photoDetailArr = new ArrayList<Integer>();

        //for user profile data
        PhotoBlogUsers blogUsers = BlogUsersService.findUserById(userId);

        List<Photos> photos = photosService.findPhotoByUName(blogUsers.getUsername());
        for(Photos photo : photos){
            byte [] imageByteArr = photo.getPhotoData();
            byte [] p = Base64.getEncoder().encode(imageByteArr);
            String photoImg = new String(p, "UTF-8");
            photoDetailArr.add(photo.getPhotoId());   // getting the id form the photos list
            photoArr.add(photoImg);
        }
        List<PhotoDetails> photoDetails = photosService.findAllPhotoDetail();

        model.addAttribute("blogUsers" , blogUsers);

        model.addAttribute("photoDetails", photoDetails);

        model.addAttribute("photos", photos);
        model.addAttribute("photoImg", photoArr);
        return "profile";
    }


    @GetMapping("/profile/add/{userId:.+}")
    public ModelAndView addDescription(@PathVariable("userId") String userId, ModelMap model)
            throws UserNotFound {

        PhotoBlogUsers blogUsers = BlogUsersService.findUserById(userId);
        model.addAttribute("blogUsers", blogUsers);

        return new ModelAndView(
                "addDescription",
                "adescription",
                new addForm()
        );
    }
    @PostMapping("/profile/add/{userId:.+}")
    public String addDescription(@PathVariable("userId") String userId, addForm form) throws UserNotFound{

        BlogUsersService.updateUserDescription(userId,
                form.getUserDescription()
                );

        return "redirect:/user/profile/{userId}";
    }

    @GetMapping("/profile/edit/{userId:.+}")
    public ModelAndView editDescription(@PathVariable("userId") String userId, ModelMap model)
            throws UserNotFound {

        PhotoBlogUsers blogUsers = BlogUsersService.findUserById(userId);
        model.addAttribute("blogUsers", blogUsers);
        return new ModelAndView(
                "editDescription",
                "edescription",
                new editForm()
        );
    }

    @PostMapping("/profile/edit/{userId:.+}")
    public String editDescription(@PathVariable("userId") String userId, editForm form) throws UserNotFound{

        BlogUsersService.updateUserDescription(userId,
                form.getUserDescription()
        );

        return "redirect:/user/profile/{userId}";
    }

    @ExceptionHandler({UserNotFound.class})
    public ModelAndView error(Exception e){

        return new ModelAndView("error", "err_message", e.getMessage());
    }

    public class addForm {

        private String userId;


        private String userDescription;

        public String getUserDescription() {
            return userDescription;
        }

        public void setUserDescription(String userDescription) {
            this.userDescription = userDescription;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

    }


    public class editForm{
        private String userId;
        private String userDescription;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserDescription() {
            return userDescription;
        }

        public void setUserDescription(String userDescription) {
            this.userDescription = userDescription;
        }
    }
}
