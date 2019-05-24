package fr.epsi.dao;

import fr.epsi.model.Admin;
import fr.epsi.model.Message;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MessageDao extends AbstractDao<Message> {

    public List<Message> getByUser(long userId) {
        return this.execute(s -> s.createQuery("from Message where user.id=" + userId).list());
    }
    
    public List<Message> getAll() {
        return this.execute(s -> s.createQuery("from Message").list());
    }
    
    public List<Message> getRechercheMessage(List<String> hashtags) {
        if (hashtags.size() > 0) {
            return this.execute(session -> {
                Query query = session.createQuery("from Message where text like :hashtag");

                query.setParameterList("hashtag", hashtags.stream().map(h -> "%" + h + "%").collect(Collectors.toList()));

                return query.list();
            });
        }
        return new ArrayList<>();
}

}
