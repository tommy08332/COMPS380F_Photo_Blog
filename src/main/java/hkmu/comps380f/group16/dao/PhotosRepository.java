package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.model.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhotosRepository extends JpaRepository<Photos, Integer> {
}
