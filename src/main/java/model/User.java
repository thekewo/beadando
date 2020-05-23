package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "username", "password", "created"})
public class User {
    private UUID id;
    private String username;
    private String password;
    private Date created;

    public  User(){};

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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.created = CreateDate();
        this.id = CreateId();
    }

    public Date CreateDate(){
        Calendar calendar = Calendar.getInstance();
        Date startDate = new Date(calendar.getTime().getTime());
        return startDate;
    }

    public UUID CreateId(){
        return randomUUID();
    }
}
