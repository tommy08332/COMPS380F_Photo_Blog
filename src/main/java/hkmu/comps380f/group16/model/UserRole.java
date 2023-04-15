package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

@Entity
@Table(name="photoblog_user_role")
public class UserRole {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private int userRoleId;

    @Column(name = "user_id", insertable = false,
            updatable = false, nullable = false)
    private long userId;
    @Column(name = "user_role", nullable = false)
    private String userRole;


    // Foreign key
    @ManyToOne
    @JoinColumn(name = "user_id")
    private PhotoBlogUsers photoBlogUsers;

    public UserRole(){};

    public UserRole(PhotoBlogUsers photoBlogUsers, String userRole){

        this.photoBlogUsers = photoBlogUsers;

        this.userRole = userRole;


    };

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public PhotoBlogUsers getPhotoBlogUsers() {
        return photoBlogUsers;
    }

    public void setPhotoBlogUsers(PhotoBlogUsers photoBlogUsers) {
        this.photoBlogUsers = photoBlogUsers;
    }
}
