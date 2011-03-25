import controllers.Auth;
import org.junit.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void createAndRetieveUser() {
        User user = new User("tom@some.com","some");
        assertNotNull(user);
    }
    
    @Test
    public void createProfileAndAddPrivate() {
        Profile profile = new Profile();
        assertNotNull(profile);
    }

    @Test
    public void tryConnectAsUser() {
        new User("tom@some.com","where");
        assertNull(User.connect("tom@some.com", "some"));
        assertNotNull(User.connect("christa", "chr34"));
    }

    @Test
    public void testCountUser() {
        long count = Auth.countUser();
        Long value = Long.valueOf(count);
        assertNotNull(value);
    }

    @Test
    public void testRandomId() {
        Integer id = Integer.valueOf(Auth.randomId());
        assertNotNull(id);
        User user = User.findById(Long.valueOf(id.longValue()));
        assertNotNull(user);
        assertEquals(user, User.connect(user.login, user.password));
    }

    @Test
    public void testUserByRandomId() {
        User user = Auth.userByRandomId();
        assertNotNull(user);
        assertEquals(user, User.connect(user.login, user.password));
    }

    @Test
    public void testProfileAttribute() {
        ProfileAttribute profileAttribute;
        profileAttribute = new ProfileAttribute();
        profileAttribute.companyEmail = "august@helloworld.com";
        profileAttribute.privateEmail = "august@helloworld.hom";
        profileAttribute.companyPhone = "0123456789";
        profileAttribute.mobilePhone = "1234567890";
        profileAttribute.privatePhone = "2345678901";
        assertNotNull(profileAttribute);
        assertEquals(profileAttribute.companyEmail, "august@helloworld.com");
    }
}
