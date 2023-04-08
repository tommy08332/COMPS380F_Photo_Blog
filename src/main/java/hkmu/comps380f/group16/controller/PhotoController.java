package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotoBlogUsersService;
import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.exception.UserNotFound;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.security.Principal;
import java.util.*;
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

    @GetMapping("/upload")
    public ModelAndView upload() {
//
//        UUID

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
//    public View upload(PhotoForm photoForm) throws IOException, UserNotFound {
    public View upload(PhotoForm photoForm) throws IOException, UserNotFound {

        String unaasdasdsadme = "";


        // may find id ?

        int photoId = photosService.uploadPhoto(photoForm.getPhotoTitle(),
                                                photoForm.getPhotoData(),
                                                photoForm.getPhotoDescription());



//        String uname = photosService.uploadPhoto();

//        String username = photoUsersService.findUser();

        return new RedirectView("/photo/show/" + photoId, true);


    }

    @GetMapping("/show/{photoId:.+}")
    public String view(@PathVariable("photoId") int photoId, ModelMap model)
            throws PhotoNotFound {

        Photos photos = photosService.findPhoto(photoId);

//        ByteArrayInputStream bis = new ByteArrayInputStream(photos.getPhotoData());
//
//        BufferedImage bImage2 = ImageIO.read(bis);



//        ImageIcon imageIcon = new ImageIcon(photos.getPhotoData());

//        new ByteArrayInputStream(photos.getPhotoData());

//        ImageIO.write(new ByteArrayInputStream(photos.getPhotoData()), "png", new File("test.png"));

//        System.out.println(imageIcon.getImage());

        model.addAttribute("photos", photos);

//        model.addAttribute("test_photo_data", imageIcon.getImage());

        return "photo";

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
