package fr.epsi.dao;

import fr.epsi.model.Tweet;
import fr.epsi.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TweetDaoTest {

    @Test
    public void insertTweetsInUser() {
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

        List<Tweet> tweets = Arrays.asList(t1, t2, t3);
        tweets.forEach(t -> t.setUser(user));

        user.setTweets(tweets);
        long userId = new UserDao().save(user);

        User newUser = new UserDao().get(userId);

        Assert.assertEquals(tweets.size(), newUser.getTweets().size());
    }

    @Test
    public void getHashtags() {
        Tweet t1 = new Tweet();
        t1.setText("test1");
        Tweet t2 = new Tweet();
        t2.setText("#test2");
        Tweet t3 = new Tweet();
        t3.setText("test3");
        Tweet t4 = new Tweet();
        t4.setText("c'est un #test");

        Arrays.asList(t1, t2, t3, t4).forEach(t -> new TweetDao().save(t));

        Assert.assertEquals(2, new TweetDao().getHahstags(Arrays.asList("test")).size());
    }
}
