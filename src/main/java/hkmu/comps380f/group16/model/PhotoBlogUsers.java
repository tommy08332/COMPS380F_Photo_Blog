package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="photoblog_users")
public class PhotoBlogUsers {

    // Add Primary key
    @Id
    @Column(name= "username")
    private String username;

    @Column(name= "user_password")
    private String password;

    @Column(name= "phone_number")
    private long phoneNum;

    @Column(name= "email")
    private String email;

    @Column(name= "user_description")
    private String userDescription;



    @OneToMany(mappedBy = "photoBlogUsers",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)

    private List<UserRole> userRoles = new ArrayList<>();


    public PhotoBlogUsers(){};

    public PhotoBlogUsers(String username,
                          String password,
                          int phoneNum,
                          String email,
                          String[] userRoles){

        this.username = username;
        this.password = "{noop}"+password;
        this.phoneNum = phoneNum;
        this.email = email;

        for (String userRole : userRoles){

            this.userRoles.add(new UserRole(this, userRole));
        }

    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }


}
