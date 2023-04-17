package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.exception.InvalidFileFormat;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.exception.UserNotFound;
import hkmu.comps380f.group16.model.Comments;
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
    private CommentsRepository commentsRepository;

    @Resource
    private PhotoBlogUsersRepository usersRepository;


    @Transactional
    public int uploadPhoto(String username,
                           String photoTitle,
                           List<MultipartFile> photoDatas,
                           String photoDescription) throws IOException, InvalidFileFormat {



        Photos photos = new Photos();

        for (MultipartFile photoData : photoDatas){

            String [] fileType = photoData.getContentType().split("/");

            if (fileType[0].trim().equals("image") &&
                    photoData.getOriginalFilename() != null &&
                    photoData.getOriginalFilename().length() > 0 &&
                    photoData != null &&
                    username != null &&
                    username.length() > 0) {

                PhotoDetails photoDetails = new PhotoDetails();

                // set filename
                photos.setPhotoFilename(photoData.getOriginalFilename());

                // set upload date & time
                photos.setPhotoUploadedDatetime(new Date());

                // set file byte content
                photos.setPhotoData(photoData.getBytes());

                // set file type e.g. jpg, png
                photos.setPhotoFileType(fileType[1]);

                // set file owner
                photos.setUploadUsername(username);

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
    public List<Photos> findAllPhotos() {

        return photosRepository.findAll();

    }
    @Transactional
    public List<PhotoDetails> findAllPhotoDetail() throws PhotoNotFound {
        return photoDetailsRepository.findAll();
    }

    @Transactional
    public List<Photos> findPhotoByUName(String uName) throws UserNotFound {

        List<Photos> photo = photosRepository.findByUploadUsername(uName);
        if (photo == null){

            throw new UserNotFound("Username '" + uName + "' not found.");

        }
        return photo;
    }

    //Delete comment
    public void deleteComment(int photoId, long commentId) throws PhotoNotFound {
//        Comments comment = commentsRepository.findById(id).orElse(null);
//        System.out.println("Deleting ... \n" + comment.getCommentId());
        Photos photo = photosRepository.findById(photoId).orElse(null);
        if(photo == null){
            throw new PhotoNotFound(String.valueOf(photoId));
        }
        for(Comments comment : photo.getComments()){
            if(comment.getCommentId() == commentId){
                photo.getComments().remove(comment);
                photosRepository.save(photo);
                return;
            }
        }


    }

//    public PhotoDetails findPhotoDetails(int photoID) throws PhotoNotFound {
//
//        PhotoDetails photoDetails = photoDetailsRepository.
//
//
//    }

}
