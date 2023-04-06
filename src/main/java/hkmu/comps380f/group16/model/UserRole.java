package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

@Entity
@Table(name="photoblog_user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private long userRoleId;


    @Column(name = "user_role")
    private String username;
    private long userRole;


    // for foreign key
    @ManyToOne
    @JoinColumn(name = "username")
    private PhotoBlogUsers photoBlogUsersusers;




    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getUserRole() {
        return userRole;
    }

    public void setUserRole(long userRole) {
        this.userRole = userRole;
    }

    public PhotoBlogUsers getPhotoBlogUsersusers() {
        return photoBlogUsersusers;
    }

    public void setPhotoBlogUsersusers(PhotoBlogUsers photoBlogUsersusers) {
        this.photoBlogUsersusers = photoBlogUsersusers;
    }
}
