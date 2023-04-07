package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PhotosService {

    @Resource
    private PhotosRepository photosRepository;

    @Resource
    private PhotoBlogUsersRepository usersRepository;


    @Transactional
    public void uploadPhoto(List<MultipartFile> photoData){
//    public void uploadPhoto(String photoTitle,
//                            List<MultipartFile> photoData,
//                            String photoFileType,
//                            String photoDescription,
//                            Date photoUploadedDatetime){

//
        String test_user_id = "testuser";
//
//        PhotoBlogUsers userss = new PhotoBlogUsers();
//
//        userss.setUsername(test_user_id);

        PhotoBlogUsers testuser = new PhotoBlogUsers();

        testuser.setUsername(test_user_id);

        Photos p = new Photos();

        for (MultipartFile photo : photoData){




            String test = "dasdsadasdasasdasd";

            p.setPhotoTitle("photoTitle");

            p.setPhotoDescription("photoDescription");


            p.setPhotoData(test.getBytes());
            // for test

//            p.setPhotoBlogUsers(test_user_id);

            p.setPhotoBlogUsers(testuser);

            p.setPhotoFileType("jpg");

            p.setPhotoUploadedDatetime(new Date());


//            userss.getPhotoAttachments().add(p);



        }

        Photos savePhoto = photosRepository.save(p);


    }


}
