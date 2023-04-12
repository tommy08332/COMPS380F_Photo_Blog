package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comments {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private long commentId;

    @Column(name = "comment_text")
    private String commentText;

    @Column(name = "comment_datetime")
    private Date commentDatetime;

    @Column(insertable = false, updatable = false)
    private String username;

    @Column(name = "photo_id", insertable = false, updatable = false)
    private int photoId;

    // foreign key
//    @ManyToOne
//    @JoinColumn(name = "username")
//    private PhotoBlogUsers photoBlogUsers;

    // foreign key
    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photos photos;


    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentDatetime() {
        return commentDatetime;
    }

    public void setCommentDatetime(Date commentDatetime) {
        this.commentDatetime = commentDatetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhotoId() { return photoId; }

    public void setPhotoId(int photoId) { this.photoId = photoId; }

//    public PhotoBlogUsers getPhotoBlogUsers() {
//        return photoBlogUsers;
//    }

//    public void setPhotoBlogUsers(PhotoBlogUsers photoBlogUsers) {
//        this.photoBlogUsers = photoBlogUsers;
//    }

    public Photos getPhotos() {
        return photos;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }
}
