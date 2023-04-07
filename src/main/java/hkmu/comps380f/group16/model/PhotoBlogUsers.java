package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="photoblog_users")
public class PhotoBlogUsers {

    // Primary key
    @Id
    private String username;
    @Column(name= "user_password")
    private String password;


    @OneToMany(mappedBy = "photoBlogUsers",
               fetch = FetchType.EAGER,
               cascade = CascadeType.ALL,
               orphanRemoval = true)

    private List<UserRole> userRoles = new ArrayList<>();

    public PhotoBlogUsers(){};

    public PhotoBlogUsers(String username,
                          String password,
                          String[] userRoles){

        this.username = username;
        this.password = "{noop}" +password;

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

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
