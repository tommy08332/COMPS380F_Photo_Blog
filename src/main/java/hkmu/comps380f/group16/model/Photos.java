package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="photo")
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private long photoId;

    @Column(name = "photo_title")
    private String photoTitle;

    @Column(name = "photo_data")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] photoData;

    @Column(name = "photo_file_type")
    private String photoFileType;

    @Column(name = "photo_description")
    private String photoDescription;

    @Column(name = "photo_uploaded_datetime")
    private Date photoUploadedDatetime;

    // foreign key
    @Column(insertable = false, updatable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "username")
    private PhotoBlogUsers photoBlogUsers;


    public Photos(){};

    public Photos(String photoTitle,
                  byte[] photoData,
                  String photoFileType,
                  String photoDescription,
                  Date photoUploadedDatetime){

        this.photoTitle = photoTitle;
        this.photoData = photoData;
        this.photoFileType = photoFileType;
        this.photoDescription = photoDescription;
        this.photoUploadedDatetime = photoUploadedDatetime;

    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public String getPhotoFileType() {
        return photoFileType;
    }

    public void setPhotoFileType(String photoFileType) {
        this.photoFileType = photoFileType;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public Date getPhotoUploadedDatetime() {
        return photoUploadedDatetime;
    }

    public void setPhotoUploadedDatetime(Date photoUploadedDatetime) {
        this.photoUploadedDatetime = photoUploadedDatetime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PhotoBlogUsers getPhotoBlogUsers() {
        return photoBlogUsers;
    }

    public void setPhotoBlogUsers(PhotoBlogUsers photoBlogUsers) {
        this.photoBlogUsers = photoBlogUsers;
    }
}
