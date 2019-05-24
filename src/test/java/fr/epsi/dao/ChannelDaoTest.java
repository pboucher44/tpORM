package fr.epsi.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import fr.epsi.model.Channel;
import fr.epsi.model.User;

public class ChannelDaoTest {

	@Test
    public void insertChannel() {
		long userIdOfUser = -1l;
        Channel channel = new Channel();
        channel.setId(1);;
        channel.setLesMessages(null);;
        channel.setNomChannel("leNom");
        long channelId = new ChannelDao().save(channel);

        LinkedList<Channel> allChan = new LinkedList<>(new ChannelDao().getAll());
        for (Channel monChannel : allChan) {
	        if (monChannel.getNomChannel().equals("leNom")) {
	            userIdOfUser = monChannel.getId();
	        }
	    }
        Assert.assertEquals(userIdOfUser, userIdOfUser);
    }

}
