package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
  List<Comments> findByPhotoId(int photoId);
  List<Comments> findByUsername(String username);
}
