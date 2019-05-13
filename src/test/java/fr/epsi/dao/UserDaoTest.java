package fr.epsi.dao;

import com.sun.tools.javac.util.List;
import fr.epsi.model.Admin;
import fr.epsi.model.Tweet;
import fr.epsi.model.User;
import org.h2.jdbc.JdbcSQLException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.PersistenceException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class UserDaoTest {

    @Test
    public void insertUser() {
        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");
        user.setEmail("ben@tatou.co");
        user.setBirthday(new Date());
        long userId = new UserDao().save(user);

        LinkedList<User> allUsers = new LinkedList<>(new UserDao().getAll());

        Assert.assertEquals(userId, allUsers.getLast().getId());
    }

    @Test(expected = PersistenceException.class)
    public void uniqueEmailFail() {
        User user1 = new User();
        user1.setFirstname("Benjamin");
        user1.setLastname("Tourman");
        user1.setEmail("ben@tatou.co");
        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setFirstname("Benjamin");
        user2.setLastname("Tourman");
        user2.setEmail("ben@tatou.co");
        user2.setBirthday(new Date());

        new UserDao().save(user1);
        new UserDao().save(user2);
    }

    @Test
    public void uniqueEmailSuccess() {
        User user1 = new User();
        user1.setFirstname("Benjamin");
        user1.setLastname("Tourman");
        user1.setEmail("ben@tatou.co");
        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setFirstname("Benjamin");
        user2.setLastname("Tourman");
        user2.setEmail("benjamin@tatou.co");
        user2.setBirthday(new Date());

        new UserDao().save(user1);
        new UserDao().save(user2);
    }

    @Test
    public void calculateAge() {
        Date birthday = java.sql.Date.valueOf(LocalDate.of(1990, 5, 20));

        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");
        user.setEmail("ben@tatou.co");
        user.setBirthday(birthday);

        long userId = new UserDao().save(user);
        User newUser = new UserDao().get(userId);

        LocalDate now = LocalDate.now();

        Assert.assertEquals(newUser.getAge(), now.getYear() - ((java.sql.Date) birthday).toLocalDate().getYear());
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");
        user.setEmail("ben@tatou.co");
        user.setBirthday(new Date());

        Tweet t1 = new Tweet();
        t1.setText("test1");
        Tweet t2 = new Tweet();
        t2.setText("test2");
        Tweet t3 = new Tweet();
        t3.setText("test3");

        java.util.List<Tweet> tweets = Arrays.asList(t1, t2, t3);
        tweets.forEach(t -> t.setUser(user));

        user.setTweets(tweets);
        long userId = new UserDao().save(user);

        new UserDao().delete(user);

        Assert.assertEquals(0, new TweetDao().getByUser(userId).size());
    }

    @Test
    public void followers() {
        User user1 = new User();
        user1.setFirstname("Benjamin");
        user1.setLastname("Tourman");
        user1.setEmail("ben@tatou.co");
        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setFirstname("Benjamin");
        user2.setLastname("Tourman");
        user2.setEmail("benjamin@tatou.co");
        user2.setBirthday(new Date());

        long userId = new UserDao().save(user1);
        new UserDao().save(user2);

        user1.getFollowers().add(user2);
        new UserDao().merge(user1);

        Assert.assertEquals(1, new UserDao().get(userId).getFollowers().size());
    }

}
