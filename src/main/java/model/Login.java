package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Login {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("beadando-mysql");

    public boolean isUsernameInDatabase(String userName){
        return false;
    }

    public boolean isUsernameAndPasswordCorrect(String userName, String password){
        EntityManager em = emf.createEntityManager();
        var dbUser = new User();
        try {
            dbUser = em.createQuery("SELECT * FROM sql7339094 WHERE username like " + userName + " AND password like " + password + ";", User.class).getSingleResult();
        } finally {
            em.close();
        }

        if(dbUser == null)
            return false;
        return true;
    }

}
