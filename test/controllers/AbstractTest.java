package controllers;



import org.junit.*;
import play.test.*;

public abstract class AbstractTest extends BaseTest {

    @Test
    public abstract void createAndRetieveUser();
    
    @Test
    public abstract void createProfileAndAddPrivate();

    @Test
    public abstract void tryConnectAsUser();

    @Test
    public abstract void testCountUser();

    @Test
    public abstract void testRandomId();

    @Test
    public abstract void testUserByRandomId();

    @Test
    public abstract void testProfileAttribute();
}
