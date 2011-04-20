
import java.util.Iterator;
import java.util.List;
import models.Profile;
import models.User;
import org.junit.*;
import play.libs.Crypto;
import play.test.*;
import play.mvc.Http.*;

public class ControllersTest extends FunctionalTest {

    @Test
    public void testControllers() {

    }

    @Before
    public void setUpChild() {
        //Fixtrue
        Fixtures.deleteAll();
        Fixtures.load("initial-data.yml");
        //User
        User user = null;
        String login = "erich01";
        String password = "f53_gfe46";
        user = User.find("byLogin", login).first();
        if (user == null) {
            user = new User(login, password);
            user.save();
            Profile profile = new Profile();
            profile.user = user;
            profile.save();
            user.profile = profile;
            user.save();
        }
        List<User> userList = User.findAll();
        Iterator iterator = userList.iterator();
        while (iterator.hasNext()) {
            user = (User) iterator.next();
            String crypted = Crypto.passwordHash(user.password);
            user.password = crypted;
            user.save();
        }
    }
}
