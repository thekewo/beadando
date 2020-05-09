package model;

import javafx.scene.control.TextField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;


@Entity
public class User {
    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Version
    private long version;

    public  User(){};

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String GetUsername(String username){
        return this.username;
    }

    public String GetPassword(String username){
        return this.password;
    }

    public void SetUsername(String username){
        this.username = username;
    }

   public void SetPassword(String password){
       this.password = password;
   }
}
