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
        assertNotNull(User.connect("christa", "chr"));
    }

    @Test
    public void testCountUser() {
        assertNotNull("Count User", Auth.countUser());
    }

    @Test
    public void testRandomId() {
        assertNotNull("Random Id", Auth.randomId());
    }

    @Test
    public void testUserByRandomId() {
        assertNotNull("User", Auth.userByRandomId());
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
