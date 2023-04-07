package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.exception.UserNotFound;
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


//    For registration
    @Transactional
    public void createUserAccount(String username,
                                  String password,
                                  String[] userRole){

        // may add some condition to check the username whether exist in the database

        PhotoBlogUsers createUser = new PhotoBlogUsers(username, password, userRole);



        usersRepository.save(createUser);

    }

    // For Admin page show all users
    @Transactional
    public List<PhotoBlogUsers> findAllUser(){

        return usersRepository.findAll();

    }



    // For delete account
    @Transactional
    public void deleteUserAccount(String username)
        throws UserNotFound {

        PhotoBlogUsers user = usersRepository.findById(username).orElse(null);

        if (user == null){

            // call exception
            throw new UserNotFound(username);

        }

        usersRepository.delete(user);

    }







}
