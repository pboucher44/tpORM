package fr.epsi.dao;

import java.util.List;

import fr.epsi.model.Admin;
import fr.epsi.model.Channel;
import fr.epsi.model.Message;

public class ChannelDao extends AbstractDao<Channel>{
	public List<Channel> getAll() {
        return this.execute(s -> s.createQuery("from Channel").list());
    }
}
