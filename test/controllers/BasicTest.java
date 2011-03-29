package controllers;

import models.Profile;
import models.ProfileAttribute;
import models.User;
import play.mvc.Scope.Session;
import play.test.*;

public abstract class BasicTest extends UnitTest {

    protected User user;
    protected String login;
    protected String password;
    protected Profile profile;
    protected Session session;
    protected ProfileAttribute profileAttribute;
    protected long count;
    protected Long value;
    protected Integer id;

    public final void setUp() {
        setUpChild();
    }

    public final void tearDown() {
    }

    public abstract void setUpChild();
}
