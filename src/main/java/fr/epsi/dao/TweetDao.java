package fr.epsi.dao;

import fr.epsi.model.Tweet;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TweetDao extends AbstractDao<Tweet> {

    public List<Tweet> getHahstags(List<String> hashtags) {
        if (hashtags.size() > 0) {
            return this.execute(session -> {
                Query query = session.createQuery("from Tweet where text like :hashtag");

                query.setParameterList("hashtag", hashtags.stream().map(h -> "%#" + h + "%").collect(Collectors.toList()));

                return query.list();
            });
        }
        return new ArrayList<>();
    }

    public List<Tweet> getByUser(long userId) {
        return this.execute(s -> s.createQuery("from Tweet where user.id=" + userId).list());
    }

}
