package fr.epsi.dao;

import fr.epsi.model.Message;
import fr.epsi.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessageDaoTest {

    @Test
    public void insertMessagesInUser() {
        User user = new User();
        user.setFirstname("Benjamin");
        user.setLastname("Tourman");
        user.setEmail("ben@tatou.co");
        user.setBirthday(new Date());

        Message t1 = new Message();
        t1.setText("test1");
        Message t2 = new Message();
        t2.setText("test2");
        Message t3 = new Message();
        t3.setText("test3");

        List<Message> messages = Arrays.asList(t1, t2, t3);
        messages.forEach(t -> t.setUserCreator(user));

        user.setMessages(messages);
        long userId = new UserDao().save(user);

        User newUser = new UserDao().get(userId);

        Assert.assertEquals(messages.size(), newUser.getMessages().size());
    }

    @Test
    public void getRechercheMessage() {
        Message t1 = new Message();
        t1.setText("test1");
        Message t2 = new Message();
        t2.setText("#test2");
        Message t3 = new Message();
        t3.setText("test3");
        Message t4 = new Message();
        t4.setText("c'est un #test");
        Message t5 = new Message();
        t5.setText("ca sera 4");

        Arrays.asList(t1, t2, t3, t4, t5).forEach(t -> new MessageDao().save(t));

        Assert.assertEquals(4, new MessageDao().getRechercheMessage(Arrays.asList("test")).size());
    }
}
