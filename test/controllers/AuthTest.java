package controllers;

import java.util.Iterator;
import java.util.List;
import javax.persistence.PersistenceException;
import org.junit.*;
import models.*;
import play.libs.Crypto;
import play.mvc.Scope.Session;
import play.test.Fixtures;

public class AuthTest extends BasicTest {

    /*@Test
    public void createAndRetieveUser() {
    user = new User("tom@some.com","some");
    assertNotNull(user);
    }
    
    @Test
    public void createProfileAndAddPrivate() {
    profile = new Profile();
    assertNotNull(profile);
    }

    @Test
    public void tryConnectAsUser() {
    user = new User("tom@some.com","where");
    assertNull(User.connect("tom@some.com", "some"));
    assertNotNull(User.connect("christa", "chr34"));
    }

    @Test
    public void testCountUser() {
    count = Auth.countUser();
    value = Long.valueOf(count);
    assertNotNull(value);
    }

    @Test
    public void testRandomId() {
    id = Integer.valueOf(Auth.randomId());
    assertNotNull(id);
    user = (User) User.findById(Long.valueOf(id.longValue()));
    assertNotNull(user);
    assertEquals(user, User.connect(user.login, user.password));
    }*/
    @Test
    public void testUserByRandomId() {
        setUpChild();
        user = Auth.userByRandomId();
        assertNotNull(user);
        //assertEquals(user, User.connect(user.login, user.password));
    }

    @Test
    public void testFindByLoginAndPassword() {
        setUpChild();
        assertNotNull(login);
        assertNotNull(password);
        String crypto = Crypto.passwordHash(password);
        assertNotNull(crypto);
        user = User.find("byLoginAndPassword", login, crypto).first();
        assertNotNull(user);
        assertNotNull(user.id);
        assertNotNull(user.login);
        assertNotNull(user.password);
        assertNotNull(user.profile);
    }

    @Test
    public void testSessionCurrentPut() {
        testFindByLoginAndPassword();
        session = Session.current();
        assertNotNull(session);
        session.put("user.id", user.id);
        session.put("user.profile", user.profile);
    }

    @Test
    public void testSessionCurrentClear() {
        testSessionCurrentPut();
        session.clear();
        assertNull(session.get("user"));
        assertNull(session.get("user.profile"));
        //behavior isn't consistent (Play-1.1 vs. Play-1.2)
        //assertNotNull(session.getId());
        //assertNull(session.getId());
    }

    @Test
    public void testFindByLogin() {
        setUpChild();
        assertNotNull(login);
        assertNotNull(password);
        user = User.find("byLogin", login).first();
        assertNotNull(user);
        assertNotNull(user.id);
        assertNotNull(user.login);
        assertNotNull(user.password);
        assertNotNull(user.profile);
    }

    @Test
    public void testRegister() {

        login = "erich01";
        assertNotSame(login, "folkertm");
        password = "f53_gfe46";
        assertNotSame(password, "hmsygc");
        assertNotNull(login);
        assertNotNull(password);
        user = User.find("byLogin", login).first();
        assertNull(user);
        if (user == null) {
            String crypto = Crypto.passwordHash(password);
            user = new User(login, crypto);
            assertNotNull(user);
            user.profile = new Profile();
            assertNotNull(user.profile);
            assertNotNull(user.profile.save());
            assertNotNull(user.save());
            session = Session.current();
            assertNotNull(session);
            session.put("user.id", user.id);
            session.put("user.profile", user.profile);
        }

        login = "james01";
        assertNotSame(login, "folkertm");
        password = "gregkrg";
        assertNotSame(password, "hmsygc");
        assertNotNull(login);
        assertNotNull(password);
        user = User.find("byLogin", login).first();
        assertNotNull(user);

    }

    @Test
    public void testProfileAttribute() {
        profileAttribute = new ProfileAttribute();
        profileAttribute.companyEmail = "august@helloworld.com";
        profileAttribute.privateEmail = "august@helloworld.hom";
        profileAttribute.companyPhone = "0123456789";
        profileAttribute.mobilePhone = "1234567890";
        profileAttribute.privatePhone = "2345678901";
        assertNotNull(profileAttribute);
        assertEquals(profileAttribute.companyEmail, "august@helloworld.com");
        profileAttribute.save();
        profileAttribute.delete();
    }

    @Override
    @Before
    public void setUpChild() {
        //Fixtrue
        Fixtures.deleteAll();
        Fixtures.load("initial-data.yml");
        //User
        List<User> userList = User.findAll();
        Iterator iterator = userList.iterator();
        User user = null;
        while (iterator.hasNext()) {
            user = (User) iterator.next();
            String crypted = Crypto.passwordHash(user.password);
            user.password = crypted;
            user.save();
        }
        //Default
        login = "david01";
        password = "hgiue";
    }
}
