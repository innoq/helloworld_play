
import controllers.AuthTest;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import models.Profile;
import models.User;
import org.junit.*;
import play.libs.Crypto;
import play.test.*;
import play.mvc.Http.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertNotNull(response);
        //assertEquals(response.contentType, "text/html; charset=utf-8");
        //int status = 404;
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
    }

    @Test
    public void testThatLoginPageWorks() {
        Response response = GET("/auth/login");
        assertIsOk(response);
        int status = 200;
        assertStatus(status, response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatImprintPageWorks() {
        Response response = GET("/home/imprint");
        assertNotNull(response);
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
        //Map<String Cookie>
        Map<String, Cookie> cookieMap = response.cookies;
        assertNotNull(cookieMap);
        assertFalse(cookieMap.isEmpty());
        //Collection<Cookie>
        Collection<Cookie> cookieCoolection = cookieMap.values();
        assertNotNull(cookieCoolection);
        assertFalse(cookieCoolection.isEmpty());
        //Iterator<Cookie>
        Iterator<Cookie> cookieIterator = cookieCoolection.iterator();
        //Cookie
        Cookie cookie = cookieIterator.next();
        assertEquals("PLAY_FLASH", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_ERRORS", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_SESSION", cookie.name);
        assertNotNull(cookie.value);
    }

    @Test
    public void testThatAboutPageWorks() {
        Response response = GET("/home/about");
        assertNotNull(response);
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
        //Map<String Cookie>
        Map<String, Cookie> cookieMap = response.cookies;
        assertNotNull(cookieMap);
        assertFalse(cookieMap.isEmpty());
        //Collection<Cookie>
        Collection<Cookie> cookieCoolection = cookieMap.values();
        assertNotNull(cookieCoolection);
        assertFalse(cookieCoolection.isEmpty());
        //Iterator<Cookie>
        Iterator<Cookie> cookieIterator = cookieCoolection.iterator();
        //Cookie
        Cookie cookie = cookieIterator.next();
        assertEquals("PLAY_FLASH", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_ERRORS", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_SESSION", cookie.name);
        assertNotNull(cookie.value);
    }

    @Test
    public void testThatSearchPageWorks() {
        Response response = GET("/search");
        assertNotNull(response);
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
        //Map<String Cookie>
        Map<String, Cookie> cookieMap = response.cookies;
        assertNotNull(cookieMap);
        assertFalse(cookieMap.isEmpty());
        //Collection<Cookie>
        Collection<Cookie> cookieCoolection = cookieMap.values();
        assertNotNull(cookieCoolection);
        assertFalse(cookieCoolection.isEmpty());
        //Iterator<Cookie>
        Iterator<Cookie> cookieIterator = cookieCoolection.iterator();
        //Cookie
        Cookie cookie = cookieIterator.next();
        assertEquals("PLAY_FLASH", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_ERRORS", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_SESSION", cookie.name);
        assertNotNull(cookie.value);
    }

    @Test
    public void testThatDashboardPageWorks() {
        Response response = GET("/home/dashboard");
        assertNotNull(response);
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
        //Map<String Cookie>
        Map<String, Cookie> cookieMap = response.cookies;
        assertNotNull(cookieMap);
        assertFalse(cookieMap.isEmpty());
        //Collection<Cookie>
        Collection<Cookie> cookieCoolection = cookieMap.values();
        assertNotNull(cookieCoolection);
        assertFalse(cookieCoolection.isEmpty());
        //Iterator<Cookie>
        Iterator<Cookie> cookieIterator = cookieCoolection.iterator();
        //Cookie
        Cookie cookie = cookieIterator.next();
        assertEquals("PLAY_FLASH", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_ERRORS", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_SESSION", cookie.name);
        assertNotNull(cookie.value);
    }

    @Test
    public void testThatStatusesPageWorks() {
        Response response = GET("/statuses");
        assertNotNull(response);
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
        //Map<String Cookie>
        Map<String, Cookie> cookieMap = response.cookies;
        assertNotNull(cookieMap);
        assertFalse(cookieMap.isEmpty());
        //Collection<Cookie>
        Collection<Cookie> cookieCoolection = cookieMap.values();
        assertNotNull(cookieCoolection);
        assertFalse(cookieCoolection.isEmpty());
        //Iterator<Cookie>
        Iterator<Cookie> cookieIterator = cookieCoolection.iterator();
        //Cookie
        Cookie cookie = cookieIterator.next();
        assertEquals("PLAY_FLASH", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_ERRORS", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_SESSION", cookie.name);
        assertNotNull(cookie.value);
    }

    @Test
    public void testThatContactsPageWorks() {
        Response response = GET("/contacts");
        assertNotNull(response);
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
        //Map<String Cookie>
        Map<String, Cookie> cookieMap = response.cookies;
        assertNotNull(cookieMap);
        assertFalse(cookieMap.isEmpty());
        //Collection<Cookie>
        Collection<Cookie> cookieCoolection = cookieMap.values();
        assertNotNull(cookieCoolection);
        assertFalse(cookieCoolection.isEmpty());
        //Iterator<Cookie>
        Iterator<Cookie> cookieIterator = cookieCoolection.iterator();
        //Cookie
        Cookie cookie = cookieIterator.next();
        assertEquals("PLAY_FLASH", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_ERRORS", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_SESSION", cookie.name);
        assertNotNull(cookie.value);
    }

    @Test
    public void testThatProfilesPageWorks() {
        Response response = GET("/profiles/edit");
        assertNotNull(response);
        int status = 302;
        assertStatus(status, response);
        //Map<String, Header>
        Map<String, Header> headerMap = response.headers;
        assertNotNull(headerMap);
        assertFalse(headerMap.isEmpty());
        //Collection<Header>
        Collection<Header> headerCollection = headerMap.values();
        assertNotNull(headerCollection);
        assertFalse(headerCollection.isEmpty());
        //Iterator<Header>
        Iterator<Header> headerIterator = headerCollection.iterator();
        Header header = headerIterator.next();
        //Header Location
        assertEquals("Location", header.name);
        Pattern p = Pattern.compile("http://[\\w|-]+/auth/login");
        Matcher m = p.matcher(header.value());
        assertTrue(m.matches());
        //Map<String Cookie>
        Map<String, Cookie> cookieMap = response.cookies;
        assertNotNull(cookieMap);
        assertFalse(cookieMap.isEmpty());
        //Collection<Cookie>
        Collection<Cookie> cookieCoolection = cookieMap.values();
        assertNotNull(cookieCoolection);
        assertFalse(cookieCoolection.isEmpty());
        //Iterator<Cookie>
        Iterator<Cookie> cookieIterator = cookieCoolection.iterator();
        //Cookie
        Cookie cookie = cookieIterator.next();
        assertEquals("PLAY_FLASH", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_ERRORS", cookie.name);
        assertEquals("", cookie.value);
        cookie = cookieIterator.next();
        assertEquals("PLAY_SESSION", cookie.name);
        assertNotNull(cookie.value);
    }

    /*@Test
    public void testThatLogoutPageWorks() {
    Response response = GET("/auth/login");
    assertIsOk(response);
    int status = 200;
    assertStatus(status, response);
    assertContentType("text/html", response);
    assertCharset("utf-8", response);

    response = GET("/auth/logout");
    assertIsOk(response);
    assertContentType("text/html", response);
    assertCharset("utf-8", response);
    }*/
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
