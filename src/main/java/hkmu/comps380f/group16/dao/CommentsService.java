package hkmu.comps380f.group16.dao;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    @Resource
    private CommentsRepository commentsRepository;

    public List<Comments> findPhotoAllComments(int photo_id) throws CommentsNotFound{
        
        Commnets comments = commentsRepository.findByPhotoId(photo_id).orElse(null);

        if (comments == null){
            throw new CommentsNotFound(Integer.toString(photo_id));
        }

        return comments;
    }


}
