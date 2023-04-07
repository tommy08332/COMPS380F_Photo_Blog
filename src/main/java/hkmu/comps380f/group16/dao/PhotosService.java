package hkmu.comps380f.group16.dao;

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


    @Transactional
    public void uploadPhoto(String photoTitle,
                            List<MultipartFile> photoData,
                            String photoFileType,
                            String photoDescription,
                            Date photoUploadedDatetime){

        Photos p = new Photos();

        p.setPhotoTitle(photoTitle);

        p.setPhotoDescription(photoDescription);

        p.setPhotoUploadedDatetime(photoUploadedDatetime);


        photosRepository.save(p);

    }


}
