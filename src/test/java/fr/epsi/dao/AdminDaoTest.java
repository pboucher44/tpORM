package fr.epsi.dao;

import fr.epsi.model.Admin;
import fr.epsi.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.LinkedList;

public class AdminDaoTest {

    @Test
    public void insertAdmin() {
        Admin admin = new Admin();
        admin.setFirstname("Benjamin");
        admin.setLastname("Tourman");
        admin.setEmail("benj@tatou.co");
        admin.setNickname("Ben");
        admin.setBirthday(new Date());

        long userId = new AdminDao().save(admin);

        LinkedList<User> allAdmins = new LinkedList<>(new AdminDao().getAll());

        Assert.assertEquals(userId, allAdmins.getLast().getId());
    }
}
