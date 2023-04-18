package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.CommentsService;
import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.exception.UserNotFound;
import hkmu.comps380f.group16.model.Comments;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.PhotoDetails;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Resource
    private PhotosService photosService;

    @Resource
    private CommentsService commentsService;

    @Resource
    private PhotoBlogUsersService BlogUsersService;
    @GetMapping("/profile")
    public View profile(Principal principal)
            throws UserNotFound {
        PhotoBlogUsers blogUsers = BlogUsersService.findUser(principal.getName());

        return new RedirectView("/user/profile/" + blogUsers.getUsername() ,true);

    }
    @GetMapping("/profile/{username:.+}")
    public String view(@PathVariable("username") String username, ModelMap model )
            throws UserNotFound, PhotoNotFound, UnsupportedEncodingException {

        ArrayList<String> photoArr = new ArrayList<String>();

        // for store photo id
        ArrayList<Integer> photoDetailArr = new ArrayList<Integer>();

        //for user profile data
        PhotoBlogUsers blogUsers = BlogUsersService.findUser(username);

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

        List<Comments> commentsList = commentsService.findUserAllComments(blogUsers.getUsername());

        if(commentsList == null){
            model.addAttribute("commentsList", null);
        }else{
            model.addAttribute("commentsList", commentsList);
        }

        return "profile";
    }

    @GetMapping("/profile/edit/{username:.+}")
    public ModelAndView editDescription(@PathVariable("username") String username, ModelMap model, Principal principal, HttpServletRequest request)
            throws UserNotFound {

        PhotoBlogUsers user = BlogUsersService.findUser(principal.getName());

        if (user == null || !username.equals(user.getUsername())){

            return new ModelAndView(new RedirectView("/", true));

        }



        PhotoBlogUsers blogUsers = BlogUsersService.findUser(username);
        model.addAttribute("blogUsers", blogUsers);
        return new ModelAndView(
                "editDescription",
                "edescription",
                new editForm()
        );
    }

    @PostMapping("/profile/edit/{username:.+}")
    public String editDescription(@PathVariable("username") String username, editForm form, Principal principal, HttpServletRequest request) throws UserNotFound{

        PhotoBlogUsers user = BlogUsersService.findUser(principal.getName());

        if (user == null || !username.equals(user.getUsername())){

            return "redirect:/";

        }

        BlogUsersService.updateUserDescription(username,
                form.getEmail(),
                form.getPhoneNum(),
                form.getUserDescription()
        );

        return "redirect:/user/profile/{username}";
    }


    public class editForm{

        private String phoneNum;

        private String email;
        private String userDescription;

        public String getUserDescription() {
            return userDescription;
        }

        public void setUserDescription(String userDescription) {
            this.userDescription = userDescription;
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
    }

}
