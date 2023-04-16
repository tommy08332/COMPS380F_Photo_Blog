package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.CommentsService;
import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.CommentsNotFound;
import hkmu.comps380f.group16.exception.InvalidFileFormat;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.model.Comments;
import hkmu.comps380f.group16.model.PhotoDetails;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    public ModelAndView upload() {

        // for test upload photo
//        List<MultipartFile> list = new ArrayList<>();
//
//        list.add(null); // size=1
//        list.add(null); // size=2
//
//
//        photosService.uploadPhoto(list);

        return new ModelAndView("upload_photo", "photoUploadForm", new PhotoForm());


//        return "upload_photo";

    }


    @PostMapping("/upload")

    public View upload(PhotoForm photoForm, Principal principal) throws IOException, InvalidFileFormat {


        // use Principle to find userid


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


        model.addObject("username", principal);

        List<Comments> comments = commentsService.findPhotoAllComments(photoId);

        System.out.println(comments.size());
        model.addObject("comments", comments);

        model.addObject("comment", new CommentForm());
        
        return model;

    }

    @PostMapping("/show/{photoId:.+}")
    public String showPhotoAndComment(@PathVariable("photoId") int photoId, CommentForm comment, Principal principal) throws CommentsNotFound, PhotoNotFound{
        switch (comment.getOrder()) {
            case "UPDATE":
//                Comments old_commment = commentsService.fin
                System.out.println("Now doing update\ncomment ID : " + comment.getCommentId() + "\ncomment : " + comment.getCommentText());
                commentsService.updateComment(comment.getCommentId(), comment.getCommentText());

                break;

            case "INSERT":
                System.out.println("Now doing insert\ncomment : " + comment.getCommentText());
                commentsService.insertComment(photoId, comment.getCommentText(), principal.getName());

                break;

            case "DELETE":
                System.out.println("Now doing delete\ncomment ID: " + comment.getCommentId());
                commentsService.deleteComment(comment.getCommentId());

                break;

            default:
                break;
        }
        return "redirect:/photo/show/" + String.valueOf(photoId);
    }

    @ExceptionHandler({PhotoNotFound.class, InvalidFileFormat.class})
    public ModelAndView error(Exception e){

        return new ModelAndView("error", "err_message", e.getMessage());
    }


    // delete photo


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
    }

}
