package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotosService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.UUID;


// For all the photo action

@Controller
@RequestMapping(value = "/photo")
public class PhotoController {

    @Resource
    private PhotosService photosService;

    @GetMapping("/show")
    public String show(){ return "photo"; }

    @GetMapping("/upload")
    public View upload(PhotoForm form, Principal principal)
            throws IOException {
//
//        UUID

        return new RedirectView();


    }

    // delete photo


    public static class PhotoForm {

        private String photoTitle;

        private List<MultipartFile> photoData;

        private String photoFileType;

        private String photoDescription;

        private Date photoUploadedDatetime;

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

        public String getPhotoFileType() {
            return photoFileType;
        }

        public void setPhotoFileType(String photoFileType) {
            this.photoFileType = photoFileType;
        }

        public String getPhotoDescription() {
            return photoDescription;
        }

        public void setPhotoDescription(String photoDescription) {
            this.photoDescription = photoDescription;
        }

        public Date getPhotoUploadedDatetime() {
            return photoUploadedDatetime;
        }

        public void setPhotoUploadedDatetime(Date photoUploadedDatetime) {
            this.photoUploadedDatetime = photoUploadedDatetime;
        }
    }


}
