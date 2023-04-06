package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

import java.util.Date;

//@Entity
//@Table(name="photo")
public class Photos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private long photoID;



    @Column(name = "photo_title")
    private String photoTitle;

    @Column(name = "photo_data")
    private byte[] photoData;

    @Column(name = "photo_file_type")
    private String photoFileType;

    @Column(name = "photo_description")
    private String photoDescription;

    @Column(name = "photo_update_datetime")
    private Date photoUpdateDatetime;

    // foreign key
    @Column(insertable = false, updatable = false)
    private String username;

    @ManyToOne
    @JoinColumn(name = "username")
    private PhotoBlogUsers photoBlogUsers;





}
