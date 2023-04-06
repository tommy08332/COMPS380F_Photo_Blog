package hkmu.comps380f.group16.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long userID;

    @Column(name = "username")
    private String username;
    @Column(name= "user_password")
    private String password;


}
