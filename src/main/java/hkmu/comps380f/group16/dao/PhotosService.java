package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PhotosService {

    @Resource
    private PhotosRepository photosRepository;


    @Transactional
    public void uploadPhoto(String photoTitle,
                            byte[] photoData,
                            String photoFileType,
                            String photoDescription,
                            Date photoUploadedDatetime){

        Photos p = new Photos();




        photosRepository.save(p);

    }


}
