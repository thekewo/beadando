package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class UserDB {
    public List<User> getUsers() {
        return users;
    }

    public User getUser(String username){
        for (User item: users){
            if(username.equals(item.getUsername()))
                return item;
        }
        return null;
    }

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User> users = new ArrayList<User>();

    public UserDB(){
    }

    public UserDB(User user){
        users.add(user);
    }
    
    public boolean IsUserInDb(User user){
        for (User item: users){
            if(user.getUsername().equals(item.getUsername()))
                return true;
        }
        return false;
    }

    public boolean IsLoggedInUserAdmin(){
        for (User item: users){
            if(item.isLoggedIn() && item.isAdmin())
                return true;
        }
        return false;
    }

    public void LogoutUsers(){
        for (User item: users){
            if(item.isLoggedIn())
                item.setLoggedIn(false);
        }
    }
}
