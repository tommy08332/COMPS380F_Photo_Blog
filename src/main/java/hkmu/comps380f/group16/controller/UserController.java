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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Base64;

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
    @GetMapping("/profile/{userid:.+}")
    public String view(@PathVariable("userid") String userid, ModelMap model)
            throws UserNotFound, PhotoNotFound, UnsupportedEncodingException {
        PhotoBlogUsers blogUsers = BlogUsersService.findUserById(userid);

        Photos photos = photosService.findPhoto(1);
        byte [] imageByteArr = photos.getPhotoData();
        byte [] photo = Base64.getEncoder().encode(imageByteArr);
        String photoImg = new String(photo, "UTF-8");

        PhotoDetails photoDetails =photosService.findPhotoDetail(1);

        model.addAttribute("blogUsers" , blogUsers);
        model.addAttribute("photoDetails", photoDetails);
        model.addAttribute("photos", photos);
        model.addAttribute("photoImg", photoImg);

        return "profile";
    }

}