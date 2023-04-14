package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.InvalidFileFormat;
import hkmu.comps380f.group16.exception.PhotoNotFound;
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
    public String view(@PathVariable("photoId") int photoId, ModelMap model)
            throws PhotoNotFound, UnsupportedEncodingException {

        Photos photos = photosService.findPhoto(photoId);

        byte [] imageByteArr = photos.getPhotoData();
        byte [] photo = Base64.getEncoder().encode(imageByteArr);
        String photoImg = new String(photo, "UTF-8");

        PhotoDetails photoDetails =photosService.findPhotoDetail(photoId);

        model.addAttribute("photos", photos);
        model.addAttribute("photoDetails", photoDetails);
        model.addAttribute("photoImg", photoImg);

        return "photo";

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

}
