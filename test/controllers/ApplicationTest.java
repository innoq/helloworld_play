/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Profile;
import models.User;
import play.mvc.Scope.Session;
import play.test.Fixtures;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Techniker
 */
public class ApplicationTest extends BasicTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of checkLogin method, of class Application.
     */
    @Test
    public void testCheckLogin() {
        System.out.println("checkLogin");
        testCurrentUser();
        assertFalse(!currentUser);
    }

    /**
     * Test of currentUser method, of class Application.
     */
    @Test
    public void testCurrentUser() {
        System.out.println("currentUser");
        user = User.find("byLogin", "james01").first();
        assertNotNull(user);
        profile = Profile.findById(user.id);
        assertNotNull(profile);
        assertNotSame(user, null);
        assertEquals(user.id, profile.id);
        assertEquals((user == null), false);
        currentUser = (user == null) ? false : true;
        assertTrue(currentUser);
    }

    @Override
    @Before
    public void setUpChild() {
        System.out.println("setUpChild");
        Fixtures.deleteAll();
        Fixtures.load("initial-data.yml");
    }
}
