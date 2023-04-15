package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.model.PhotoDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDetailsRepository extends JpaRepository<PhotoDetails, Integer> {

    PhotoDetails findByPhotoId(int photoId);


}

