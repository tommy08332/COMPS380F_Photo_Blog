package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.exception.InvalidFileFormat;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.model.PhotoDetails;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class PhotosService {

    @Resource
    private PhotosRepository photosRepository;

    @Resource
    private PhotoDetailsRepository photoDetailsRepository;

    @Resource
    private PhotoBlogUsersRepository usersRepository;


    @Transactional
    public int uploadPhoto(String photoTitle,
                            List<MultipartFile> photoDatas,
                            String photoDescription) throws IOException, InvalidFileFormat {


        // suppose got the username from session
        // for test
        String test_user_id = "testuser01";
        // for test

        Photos photos = new Photos();

        for (MultipartFile photoData : photoDatas){

            String [] fileType = photoData.getContentType().split("/");

            if (fileType[0].trim().equals("image") &&
                photoData.getOriginalFilename() != null &&
                photoData.getOriginalFilename().length() > 0 &&
                photoData != null &&
                test_user_id != null &&
                test_user_id.length() > 0) {

                PhotoDetails photoDetails = new PhotoDetails();

                // filename
                photos.setPhotoFilename(photoData.getOriginalFilename());

                // upload date & time
                photos.setPhotoUploadedDatetime(new Date());

                // file byte content
                photos.setPhotoData(photoData.getBytes());

                // file type e.g. jpg, png
                photos.setPhotoFileType(fileType[1]);

                // file owner
                photos.setUploadUsername(test_user_id);

                photoDetails.setPhotoId(photos.getPhotoId());
                photoDetails.setPhotoTitle(photoTitle);
                photoDetails.setPhotoDescription(photoDescription);
                photoDetails.setPhoto(photos);

                photos.getPhotoDetails().add(photoDetails);

            } else {

                throw new InvalidFileFormat(photoData.getOriginalFilename());

            }

        }

        Photos savePhoto = photosRepository.save(photos);

        return savePhoto.getPhotoId();

    }

    @Transactional
    public Photos findPhoto(int photoID) throws PhotoNotFound {

        Photos photo = photosRepository.findById(photoID).orElse(null);

        // get password

        if (photo == null){

            throw new PhotoNotFound(Integer.toString(photoID));

        }

        return photo;

    }

    @Transactional
    public PhotoDetails findPhotoDetail(int photoId) throws PhotoNotFound {

        PhotoDetails photoDetails = photoDetailsRepository.findByPhotoId(photoId);

        if (photoDetails == null){

            throw new PhotoNotFound(Integer.toString(photoId));
        }

        return photoDetails;

    }

    // for index page
    @Transactional
    public List<Photos> findAllPhotos() throws PhotoNotFound {

        return photosRepository.findAll();

    }

//    public PhotoDetails findPhotoDetails(int photoID) throws PhotoNotFound {
//
//        PhotoDetails photoDetails = photoDetailsRepository.
//
//
//    }

}
