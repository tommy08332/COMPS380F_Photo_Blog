package hkmu.comps380f.group16.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name="photo")
public class Photos {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private int photoId;

//    @Column(name = "photo_title")
//    private String photoTitle;

    @Column(name = "photo_data")
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private byte[] photoData;

    @Column(name = "photo_filename")
    private String photoFilename;

    @Column(name = "photo_file_type")
    private String photoFileType;

//    @Column(name = "photo_description")
//    private String photoDescription;

    @Column(name = "photo_uploaded_datetime")
    private Date photoUploadedDatetime;


//    @Column(insertable = false, updatable = false, nullable = false)
//    private String username;

    @Column(name = "upload_uname")
    private String uploadUsername;


    @OneToMany(
            mappedBy = "photo",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SUBSELECT)

    private List<PhotoDetails> photoDetails = new ArrayList<>();


    @OneToMany(
            mappedBy = "photo",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SUBSELECT)

    private List<Comments> comments = new ArrayList<>();

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public byte[] getPhotoData() {
        return photoData;
    }

    public void setPhotoData(byte[] photoData) {
        this.photoData = photoData;
    }

    public String getPhotoFilename() {
        return photoFilename;
    }

    public void setPhotoFilename(String photoFilename) {
        this.photoFilename = photoFilename;
    }

    public String getPhotoFileType() {
        return photoFileType;
    }

    public void setPhotoFileType(String photoFileType) {
        this.photoFileType = photoFileType;
    }

    public Date getPhotoUploadedDatetime() {
        return photoUploadedDatetime;
    }

    public void setPhotoUploadedDatetime(Date photoUploadedDatetime) {
        this.photoUploadedDatetime = photoUploadedDatetime;
    }

    public String getUploadUsername() {
        return uploadUsername;
    }

    public void setUploadUsername(String uploadUsername) {
        this.uploadUsername = uploadUsername;
    }

    public List<PhotoDetails> getPhotoDetails() {
        return photoDetails;
    }

    public void setPhotoDetails(List<PhotoDetails> photoDetails) {
        this.photoDetails = photoDetails;
    }

    // delete photoDetails


    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    // delete comments
}
