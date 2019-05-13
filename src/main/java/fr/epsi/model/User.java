package fr.epsi.model;


import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames = "email")},
        indexes = @Index(columnList = "email"))
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String firstname;
    private String lastname;
    private String email;
    private Date birthday;
    @Formula("DATEDIFF('YEAR',birthday,now())")
    private int age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Tweet> tweets;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "follower", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    private List<User> followers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Tweet> getTweets() {
        if (this.tweets == null) {
            this.tweets = new ArrayList<>();
        }
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Transient
    public int getAge() {
        return age;
    }

    public List<User> getFollowers() {
        if (this.followers == null) {
            this.followers = new ArrayList<>();
        }
        return followers;
    }
}
