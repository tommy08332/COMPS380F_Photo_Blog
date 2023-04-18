package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.exception.CommentsNotFound;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.model.Comments;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentsService {

    @Resource
    private CommentsRepository commentsRepository;

    @Resource
    private PhotosRepository photosRepository;


    //List All Photo' Comments
    public List<Comments> findPhotoAllComments(int photo_id) throws CommentsNotFound {

        List<Comments> comments = commentsRepository.findByPhotoId(photo_id);

        if (comments == null){
            throw new CommentsNotFound("Photo ID('" + photo_id + "') commnets not found");
        }

        return comments;
    }

    //List All User' Comments
    public List<Comments> findUserAllComments(String username){
        return commentsRepository.findByUsername(username);
    }

    //Update Comment
    public void updateComment(long id, String context) throws CommentsNotFound{
        Comments comment = commentsRepository.findById(id).orElse(null);
        if(comment == null){
            throw new CommentsNotFound("Comment not found");
        }
        comment.setCommentText(context);
        commentsRepository.save(comment);
    }

    //Insert New Comment
    public void insertComment(int photoId, String context, String username) throws PhotoNotFound {

        Comments comment = new Comments();
        comment.setCommentText(context);
        comment.setCommentDatetime(new Date());
        comment.setUsername(username);
        comment.setPhotoId(photoId);
        Photos photo = photosRepository.findById(photoId).orElse(null);
        if(photo == null){
            throw new PhotoNotFound(String.valueOf(photoId));
        }
        comment.setPhoto(photo);

        commentsRepository.save(comment);
    }

}
