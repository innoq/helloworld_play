import org.junit.*;
import play.test.*;
import play.mvc.Http.*;

public class ApplicationTest extends FunctionalTest {

    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatSearchPageWorks() {
        Response response = GET("/search");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatLoginPageWorks() {
        Response response = GET("/auth/login");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatLogoutPageWorks() {
        Response response = GET("/auth/logout");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);  
    }

    @Test
    public void testThatDashboardPageWorks() {
        Response response = GET("/home/dashboard");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatImprintPageWorks() {
        Response response = GET("/home/imprint");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatAboutPageWorks() {
        Response response = GET("/home/about");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatStatusesPageWorks() {
        Response response = GET("/statuses");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatContactsPageWorks() {
        Response response = GET("/contacts");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }

    @Test
    public void testThatProfilesPageWorks() {
        Response response = GET("/profiles");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset("utf-8", response);
    }
}