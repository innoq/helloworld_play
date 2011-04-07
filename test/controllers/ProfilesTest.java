/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.test.Fixtures;
import models.Status;
import models.Profile;
import models.User;
import play.mvc.Scope.Session;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Techniker
 */
public class ProfilesTest extends BasicTest {

    public ProfilesTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of _contact_data method, of class Profiles.
     */
    @Test
    public void test_contact_data() {
        System.out.println("_contact_data");
        //Profiles._contact_data();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of _edit_address method, of class Profiles.
     */
    @Test
    public void test_edit_address() {
        System.out.println("_edit_address");
        //Profiles._edit_address();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class Profiles.
     */
    @Test
    public void testEdit() {
        System.out.println("edit");
        setUpChild();
        login = "buelent01";
        password = "uludag";
        assertEquals(login, "buelent01");
        assertEquals(password, "uludag");
        user = User.find("byLoginAndPassword", login, password).first();
        assertNotNull(user);
        session.current().put("user", user.id);
        assertEquals(Session.current().get("user"), "10");
        User user02 = User.findById(Long.parseLong(Session.current().get("user")));
        assertNotNull(user02);
        assertEquals(user02.login, "buelent01");
        Profile profile = Profile.findById(user02.id);
        assertNotNull(profile.id);
        assertEquals(profile.id, new Long(10));
        Status status = Status.find("byProfile", profile).first();
        assertNotNull(status);
        assertEquals(status.id, new Long(11));
        assertEquals(status.count("byProfile",profile),Long.parseLong("1"));

        login = "leo01";
        password = "nmxsczvm3";
        assertEquals(login, "leo01");
        assertEquals(password, "nmxsczvm3");
        user = User.find("byLoginAndPassword", login, password).first();
        assertNotNull(user);
        session.current().put("user", user.id);
        assertEquals(Session.current().get("user"), "6");
        user02 = User.findById(Long.parseLong(Session.current().get("user")));
        assertNotNull(user02);
        assertEquals(user02.login, "leo01");
        profile = Profile.findById(user02.id);
        assertNotNull(profile.id);
        assertEquals(profile.id, new Long(6));
        status = Status.find("byProfile", profile).first();
        assertNotNull(status);
        assertEquals(status.id, new Long(6));
        assertEquals(status.count("byProfile",profile),Long.parseLong("2"));

        login = "leo01";
        password = "nmxsczvm3";
        assertEquals(login, "leo01");
        assertEquals(password, "nmxsczvm3");
        user = User.find("byLoginAndPassword", login, password).first();
        assertNotNull(user);
        session.current().put("user", user.id);
        assertEquals(Session.current().get("user"), "6");
        user02 = User.findById(Long.parseLong(Session.current().get("user")));
        assertNotNull(user02);
        assertEquals(user02.login, "leo01");
        profile = Profile.findById(user02.id);
        assertNotNull(profile.id);
        assertEquals(profile.id, new Long(6));
        profileAttribute = profileAttribute.findById(profile.id);
        assertNotNull(profileAttribute);
        assertEquals(profileAttribute.id, new Long(6));
        assertEquals(profileAttribute.count("byProfile",profile),Long.parseLong("2"));

        login = "buelent01";
        password = "uludag";
        assertEquals(login, "buelent01");
        assertEquals(password, "uludag");
        user = User.find("byLoginAndPassword", login, password).first();
        assertNotNull(user);
        session.current().put("user", user.id);
        assertEquals(Session.current().get("user"), "10");
        user02 = User.findById(Long.parseLong(Session.current().get("user")));
        assertNotNull(user02);
        assertEquals(user02.login, "buelent01");
        profile = Profile.findById(user02.id);
        assertNotNull(profile.id);
        assertEquals(profile.id, new Long(10));
        profileAttribute = profileAttribute.findById(profile.id);
        assertNotNull(profileAttribute);
        assertEquals(profileAttribute.id, new Long(10));
        assertEquals(profileAttribute.count("byProfile",profile),Long.parseLong("1"));

    }

    /**
     * Test of privat method, of class Profiles.
     */
    @Test
    public void testPrivat() {
        System.out.println("privat");
        long id = 0L;
        //Profiles.privat(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of show method, of class Profiles.
     */
    @Test
    public void testShow() {
        System.out.println("show");
        int id = 0;
        //Profiles.show(id);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Override
    @Before
    public void setUpChild() {
        Fixtures.deleteAll();
        Fixtures.load("initial-data.yml");
    }
}
