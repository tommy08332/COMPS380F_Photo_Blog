package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.exception.EmailAlreadyUsed;
import hkmu.comps380f.group16.exception.PhoneNumberAlreadyUsed;
import hkmu.comps380f.group16.exception.UserAccountAlreadyExists;
import hkmu.comps380f.group16.model.PhotoBlogUsers;
import hkmu.comps380f.group16.model.UserRole;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoBlogUsersService implements UserDetailsService {

    @Resource
    private PhotoBlogUsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {

        System.out.println("Running loadUserByUsername1");

        PhotoBlogUsers photoBlogUsers = usersRepository.findById(user).orElse(null);

        if (photoBlogUsers == null){

            throw new UsernameNotFoundException("The user " + user + " does not exist");

        }

        System.out.println("Running loadUserByUsername2");

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : photoBlogUsers.getUserRoles()){

            authorities.add(new SimpleGrantedAuthority(role.getUserRole()));

        }

        return new User(photoBlogUsers.getUsername(),
                        photoBlogUsers.getPassword(),
                        authorities);

    }

    @Transactional
    public void createUserAccount(String username,
                                  String password,
                                  String phoneNum,
                                  String email,
                                  String[] userRole)
            throws UserAccountAlreadyExists,
                   EmailAlreadyUsed,
                   PhoneNumberAlreadyUsed {

        PhotoBlogUsers findUser = usersRepository.findById(username).orElse(null);

        PhotoBlogUsers findEmail = usersRepository.findByEmail(email);

        PhotoBlogUsers findPhoneNum = usersRepository.findByPhoneNum(phoneNum);

        if (findUser != null){

            throw new UserAccountAlreadyExists(username);

        }

        if (findEmail != null){

            throw new EmailAlreadyUsed(email);
        }

        if (findPhoneNum != null){

            throw new PhoneNumberAlreadyUsed(phoneNum);

        }

        if (userRole == null){

            String[] defaultRole = {"ROLE_USER"};

            userRole = defaultRole;

        }

        PhotoBlogUsers createUser = new PhotoBlogUsers(username,
                                                       password,
                                                       phoneNum,
                                                       email,
                                                       userRole);

        usersRepository.save(createUser);

    }

    @Transactional
    public List<PhotoBlogUsers> findAllUsers(){

        return usersRepository.findAll();
    }

}