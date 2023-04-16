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

        PhotoBlogUsers findUser = usersRepository.findByUsername(username);

        System.out.println("findUser" + findUser);


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

        PhotoBlogUsers findUser = usersRepository.findByUsername(username);

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

//            For trouble shoot
//            String[] defaultRole = {"ROLE_USER", "ROLE_ADMIN"};

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

    public PhotoBlogUsers findUser(String username) throws UserNotFound{

        PhotoBlogUsers user = usersRepository.findByUsername(username);

        if (user == null){

            throw new UserNotFound("Username '" + username + "' not found.");

        }

        return user;

    }

    @Transactional

    public PhotoBlogUsers findUserById(String userId) throws UserNotFound{

        PhotoBlogUsers user = usersRepository.findById(userId).orElse(null);

        if (user == null){

            throw new UserNotFound("User Id '" + userId + "' not found.");

        }

        return user;

    }


    @Transactional
    public List<PhotoBlogUsers> findAllUsers(){

        return usersRepository.findAll();
    }

    @Transactional
    public void deleteUser(String userId) throws UserNotFound {

        PhotoBlogUsers targetUser = usersRepository.findById(userId).orElse(null);
        if (targetUser == null){

            throw new UserNotFound("User Id '" + userId + "' not found.");

        }

        usersRepository.delete(targetUser);


    }

    @Transactional
    public void updateUser(String userId,
                           String username,
                           String password,
                           String email,
                           String phoneNumber,
                           String userDescription,
                           String[] updateRoles) throws UserNotFound{

        PhotoBlogUsers updatedUser = usersRepository.findById(userId).orElse(null);

        if (updatedUser == null){
            throw new UserNotFound("User Id '" + userId + "' not found.");

        }

        updatedUser.setUserId(Long.parseLong(userId));
        updatedUser.setUsername(username);
        updatedUser.setPassword("{noop}"+password);
        updatedUser.setEmail(email);
        updatedUser.setPhoneNum(phoneNumber);
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

}
