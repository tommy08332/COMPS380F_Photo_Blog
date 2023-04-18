package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.CommentsService;
import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.CommentsNotFound;
import hkmu.comps380f.group16.exception.InvalidFileFormat;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.exception.UserNotFound;
import hkmu.comps380f.group16.model.Comments;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.PhotoDetails;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;


// For all the photo action

@Controller
@RequestMapping(value = "/photo")
public class PhotoController {

    @Resource
    private PhotoBlogUsersService photoUsersService;

    @Resource
    private PhotosService photosService;

    @Resource
    private CommentsService commentsService;

    @GetMapping("/show")
    public String show(){ return "photo"; }

    @GetMapping("/")
    public String localDirectory(){
        return "redirect:/photo/upload";
    }

    @GetMapping("/upload")
    public ModelAndView upload(Principal principal)
            throws UserNotFound{

        PhotoBlogUsers user = photoUsersService.findUser(principal.getName());
        if (user == null || !principal.getName().equals(user.getUsername())){

            return new ModelAndView(new RedirectView("/", true));

        }

        return new ModelAndView("upload_photo", "photoUploadForm", new PhotoForm());

    }


    @PostMapping("/upload")

    public View upload(PhotoForm photoForm, Principal principal)
            throws IOException, InvalidFileFormat, UserNotFound {

        PhotoBlogUsers user = photoUsersService.findUser(principal.getName());
        if (user == null || !principal.getName().equals(user.getUsername())){

            return new RedirectView("/", true);

        }


        int photoId = photosService.uploadPhoto(principal.getName(),
                photoForm.getPhotoTitle(),
                photoForm.getPhotoData(),
                photoForm.getPhotoDescription());


        return new RedirectView("/photo/show/" + photoId, true);


    }

    @GetMapping("/show/{photoId:.+}")
    public ModelAndView showPhotoAndComment(@PathVariable("photoId") int photoId, Principal principal)
            throws PhotoNotFound, UnsupportedEncodingException, CommentsNotFound {
        ModelAndView model = new ModelAndView("photo");


        Photos photos = photosService.findPhoto(photoId);


        byte [] imageByteArr = photos.getPhotoData();
        byte [] photo = Base64.getEncoder().encode(imageByteArr);
        String photoImg = new String(photo, "UTF-8");

        PhotoDetails photoDetails =photosService.findPhotoDetail(photoId);

        model.addObject("photos", photos);
        model.addObject("photoDetails", photoDetails);
        model.addObject("photoImg", photoImg);


        if(principal == null){
            model.addObject("username", null);
        }else {
            model.addObject("username", principal.getName());
        }

        List<Comments> comments = commentsService.findPhotoAllComments(photoId);

        model.addObject("comments", comments);

        model.addObject("comment", new CommentForm());

        return model;

    }

    //Update Comment
    @PostMapping("/show/comment/update")
    public String updateComment(CommentForm commentForm,
                                Principal principal, HttpServletRequest request)throws CommentsNotFound, UserNotFound {

        PhotoBlogUsers user = photoUsersService.findUser(principal.getName());


        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return "redirect:/photo/show/" + String.valueOf(commentForm.getPhotoId());

        }


        if(commentForm.getOrder().equals("UPDATE")){

            commentsService.updateComment(commentForm.getCommentId(), commentForm.getCommentText());
        }
        return "redirect:/photo/show/" + String.valueOf(commentForm.getPhotoId());
    }


    //Delete Comment
    @PostMapping("/show/comment/delete")
    public String deleteComment(CommentForm commentForm,
                                Principal principal, HttpServletRequest request)throws PhotoNotFound, UserNotFound {

        PhotoBlogUsers user = photoUsersService.findUser(principal.getName());

        if (user == null || !request.isUserInRole("ROLE_ADMIN") &&
                !principal.getName().equals(user.getUsername())){

            return "redirect:/photo/show/" + String.valueOf(commentForm.getPhotoId());

        }

        if(commentForm.getOrder().equals("DELETE")){
            photosService.deleteComment(commentForm.getPhotoId(), commentForm.getCommentId());
        }
        return "redirect:/photo/show/" + String.valueOf(commentForm.getPhotoId());
    }

    //Insert New Comment
    @PostMapping("/show/comment/insert")
    public String insertComment(CommentForm commentForm,
                                Principal principal) throws PhotoNotFound, UserNotFound{


        PhotoBlogUsers user = photoUsersService.findUser(principal.getName());
        if (user == null || !principal.getName().equals(user.getUsername())){

            return "redirect:/photo/show/" + String.valueOf(commentForm.getPhotoId());

        }


        if (commentForm.getOrder().equals("INSERT")) {

            commentsService.insertComment(commentForm.getPhotoId(), commentForm.getCommentText(), principal.getName());
        }
        return "redirect:/photo/show/" + String.valueOf(commentForm.getPhotoId());
    }


    @ExceptionHandler({PhotoNotFound.class, InvalidFileFormat.class,
            UserNotFound.class, CommentsNotFound.class, InvalidFileFormat.class})
    public ModelAndView error(Exception e){

        return new ModelAndView("error", "err_message", e.getMessage());
    }




    public static class PhotoForm {

        private String photoTitle;

        private List<MultipartFile> photoData;

        private String photoDescription;

        public String getPhotoTitle() {
            return photoTitle;
        }

        public void setPhotoTitle(String photoTitle) {
            this.photoTitle = photoTitle;
        }

        public List<MultipartFile> getPhotoData() {
            return photoData;
        }

        public void setPhotoData(List<MultipartFile> photoData) {
            this.photoData = photoData;
        }

        public String getPhotoDescription() {
            return photoDescription;
        }

        public void setPhotoDescription(String photoDescription) {
            this.photoDescription = photoDescription;
        }

    }
    public static class CommentForm{
        private String order;
        private String commentText;
        private long commentId;

        private int photoId;

        public String getOrder(){
            return this.order;
        }

        public void setOrder(String order){
            this.order = order;
        }

        public String getCommentText(){
            return this.commentText;
        }

        public void setCommentText(String commentText){
            this.commentText = commentText;
        }

        public long getCommentId(){
            return this.commentId;
        }

        public void setCommentId(long commentId){
            this.commentId = commentId;
        }

        public int getPhotoId() {
            return photoId;
        }

        public void setPhotoId(int photoId) {
            this.photoId = photoId;
        }
    }

}
