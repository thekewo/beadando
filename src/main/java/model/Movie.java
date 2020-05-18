package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Movie {

    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Version
    private long version;

    public Movie(){}
}
