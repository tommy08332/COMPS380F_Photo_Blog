package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.model.PhotoBlogUsers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoBlogUsersRepository extends JpaRepository<PhotoBlogUsers, String> {

    PhotoBlogUsers findByEmail(String email);

    PhotoBlogUsers findByPhoneNum(String phoneNum);

    PhotoBlogUsers findByUsername(String username);

}