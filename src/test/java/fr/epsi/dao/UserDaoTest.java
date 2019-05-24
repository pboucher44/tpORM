package fr.epsi.dao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.PersistenceException;

import org.junit.Assert;
import org.junit.Test;

import fr.epsi.model.Message;
import fr.epsi.model.User;

public class UserDaoTest {

    @Test
    public void insertUser() {
    	long userIdOfUser = -1L;
        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");
        user.setEmail("ben6@tatou.co");
        user.setMessages(null);
        user.setEmail("ben6@tatou.co");
        user.setBirthday(new Date());
        long userId = new UserDao().save(user);

        LinkedList<User> allUsers = new LinkedList<>(new UserDao().getAll());
        for (User monUser : allUsers) {
	        if (monUser.getEmail().equals("ben6@tatou.co")) {
	            userIdOfUser = monUser.getId();
	        }
	    }
        Assert.assertEquals(userId, userIdOfUser);
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
        user1.setEmail("ben2@tatou.co");
        user1.setBirthday(new Date());

        User user2 = new User();
        user2.setFirstname("Benjamin");
        user2.setLastname("Tourman");
        user2.setEmail("benjamin2@tatou.co");
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
        user.setEmail("ben3@tatou.co");
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
        user.setEmail("ben4@tatou.co");
        user.setBirthday(new Date());

        Message t1 = new Message();
        t1.setText("test1");
        Message t2 = new Message();
        t2.setText("test2");
        Message t3 = new Message();
        t3.setText("test3");

        java.util.List<Message> messages = Arrays.asList(t1, t2, t3);
        messages.forEach(t -> t.setUserCreator(user));

        user.setMessages(messages);
        long userId = new UserDao().save(user);

        new UserDao().delete(user);

        Assert.assertEquals(0, new MessageDao().getByUser(userId).size());
    }

}
