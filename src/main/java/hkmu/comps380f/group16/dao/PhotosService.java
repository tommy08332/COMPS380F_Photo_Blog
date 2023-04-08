package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.controller.RegistrationController;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.exception.UserNotFound;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PhotosService {

    @Resource
    private PhotosRepository photosRepository;

    @Resource
    private PhotoBlogUsersRepository usersRepository;

    @Transactional
    public int uploadPhoto(String photoTitle,
                            List<MultipartFile> photoDatas,
                            String photoDescription) throws IOException, UserNotFound {

        // for test
        String test_user_id = "testuser";
        // for test

        PhotoBlogUsers pUser = new PhotoBlogUsers();
        pUser.setUsername(test_user_id);

        for (MultipartFile photoData : photoDatas){

            String [] fileType = photoData.getContentType().split("/");

            if (fileType[0].trim().equals("image")) {


                Photos p = new Photos();

                // file title
                p.setPhotoTitle(photoTitle);

                // file description
                p.setPhotoDescription(photoDescription);

                // file byte content
                p.setPhotoData(photoData.getBytes());


                p.setPhotoBlogUsers(pUser);

                // file type e.g. jpg, png
                p.setPhotoFileType(fileType[1]);

                // Current system time
                p.setPhotoUploadedDatetime(new Date());

                pUser.getPhotoAttachments().add(p);

            }

        }

        PhotoBlogUsers savePhoto = usersRepository.save(pUser);

        int photoId = savePhoto.getPhotoAttachments().get(0).getPhotoId();

        return photoId;

    }

    @Transactional
    public Photos findPhoto(int photoID) throws PhotoNotFound {

        Photos photo = photosRepository.findById(photoID).orElse(null);

        if (photo == null){

            throw new PhotoNotFound(Integer.toString(photoID));

        }

        return photo;


    }

}
