package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

@Entity
@Table(name = "photo_details")
public class PhotoDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_details_id")
    private int id;


    @Column(name = "photo_title")
    private String photoTitle;

    @Column(name = "photo_description")
    private String photoDescription;


    @Column(name = "photo_id", insertable = false, updatable = false)
    private int photoId;

    @ManyToOne
    @JoinColumn(name = "photo_id")
    private Photos photo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoTitle() {
        return photoTitle;
    }

    public void setPhotoTitle(String photoTitle) {
        this.photoTitle = photoTitle;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public Photos getPhoto() {
        return photo;
    }

    public void setPhoto(Photos photo) {
        this.photo = photo;
    }
}
