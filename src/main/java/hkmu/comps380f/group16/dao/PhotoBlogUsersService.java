package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.model.PhotoBlogUsers;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoBlogUsersService {

    @Resource
    private PhotoBlogUsersRepository usersRepository;
    @Resource
    private UserRoleRepository userRoleRepository;


//    For testing
    @Transactional
    public void createUserAccount(String username,
                                    String password,
                                    String[] userRole){


        PhotoBlogUsers createUser = new PhotoBlogUsers(username, password, userRole);

        usersRepository.save(createUser);

    }

    // For testing
    @Transactional
    public List<PhotoBlogUsers> findAllUser(){

        return usersRepository.findAll();

    }


}
