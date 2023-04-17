package hkmu.comps380f.group16.dao;

import hkmu.comps380f.group16.exception.EmailAlreadyUsed;
import hkmu.comps380f.group16.exception.PhoneNumberAlreadyUsed;
import hkmu.comps380f.group16.exception.UserAccountAlreadyExists;
import hkmu.comps380f.group16.exception.UserNotFound;
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

    @Resource
    private UserRoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("before" );

        PhotoBlogUsers findUser = usersRepository.findById(username).orElse(null);

        System.out.println("findUser" + findUser);

        System.out.println("&amp;  " + username);


        if (findUser == null){

            throw new UsernameNotFoundException("The user " + username + " does not exist");

        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRole role : findUser.getUserRoles()){

            authorities.add(new SimpleGrantedAuthority(role.getUserRole()));


        }


        return new User(findUser.getUsername(),
                        findUser.getPassword(),
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
                                                       Integer.parseInt(phoneNum),
                                                       email,
                                                       userRole);

        usersRepository.save(createUser);

    }

    @Transactional

    public PhotoBlogUsers findUser(String username) throws UserNotFound{

        PhotoBlogUsers user = usersRepository.findById(username).orElse(null);

        if (user == null){

            throw new UserNotFound("Username '" + username + "' not found.");

        }

        return user;

    }


    @Transactional
    public List<PhotoBlogUsers> findAllUsers(){

        return usersRepository.findAll();
    }

    @Transactional
    public void deleteUser(String username) throws UserNotFound {

        PhotoBlogUsers targetUser = usersRepository.findById(username).orElse(null);
        if (targetUser == null){

            throw new UserNotFound("Username '" + username + "' not found.");

        }

        usersRepository.delete(targetUser);


    }

    @Transactional
    public void updateUser(String username,
                           String password,
                           String email,
                           String phoneNumber,
                           String userDescription,
                           String[] updateRoles) throws UserNotFound{

        PhotoBlogUsers updatedUser = usersRepository.findById(username).orElse(null);

        if (updatedUser == null){
            throw new UserNotFound("Username '" + username + "' not found.");

        }

        updatedUser.setPassword("{noop}"+password);
        updatedUser.setEmail(email);
        updatedUser.setPhoneNum(Long.parseLong(phoneNumber));
        updatedUser.setUserDescription(userDescription);

        // update user roles
        List<UserRole> updatedRoleList = new ArrayList<>();
        updatedUser.getUserRoles().clear();
        for (String updateRole : updateRoles){
            updatedRoleList.add(new UserRole(updatedUser, updateRole));
        }
        updatedUser.getUserRoles().addAll(updatedRoleList);

        // save update
        usersRepository.save(updatedUser);

    }

    @Transactional
    public void updateUserDescription(String username,
                                      String email,
                                      String phoneNumber,
                                      String userDescription) throws UserNotFound {

        PhotoBlogUsers updateUserDescription = usersRepository.findById(username).orElse(null);
        if (updateUserDescription == null){
            throw new UserNotFound("Username '" + username + "' not found.");
        }

        updateUserDescription.setUserDescription(userDescription);
        updateUserDescription.setEmail(email);
        updateUserDescription.setPhoneNum(Long.parseLong(phoneNumber));

        usersRepository.save(updateUserDescription);

    }

}
