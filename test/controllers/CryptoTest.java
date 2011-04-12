/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.Logger;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import play.libs.Crypto;
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
public class CryptoTest extends BasicTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of passwordHash method, pre-testing for Crypto use in class Auth.
     */
    @Test
    public void testPasswordHash() {
        System.out.println("passwordHash");

        String path = "/home/Techniker/playframework/helloworld_play/conf/password-data.yml";
        assertEquals(path, "/home/Techniker/playframework/helloworld_play/conf/password-data.yml");
        File file = null;
        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            file = new File(path);
            assertFalse(file.createNewFile());
            writer = new FileWriter(file);
            assertNotNull(writer);
            bufferedWriter = new BufferedWriter(writer);
            assertNotNull(bufferedWriter);
            List<User> userList = User.findAll();
            assertNotNull(userList);
            Iterator iterator = userList.iterator();
            assertNotNull(iterator);
            while (iterator.hasNext()) {
                String crypted = Crypto.passwordHash(((User) iterator.next()).password);
                assertNotNull(crypted);
                bufferedWriter.write(crypted);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            writer.close();
        } catch (IOException ex) {
            assertNull(ex);
        }
    }

    @Test
    public void testUserCryptoPassword() {
        System.out.println("userCryptoPassword");
        List<User> userList = User.findAll();
            assertNotNull(userList);
            Iterator iterator = userList.iterator();
            assertNotNull(iterator);
            while (iterator.hasNext()) {
                User user = (User) iterator.next();
                String crypted = Crypto.passwordHash(user.password);
                assertNotNull(crypted);
                user.password = crypted;
                user.save();
            }
    }

    @Override
    @Before
    public void setUpChild() {
        Logger.info("-i- Method setUpChild()");
        Fixtures.deleteAll();
        Fixtures.load("initial-data.yml");
        Logger.info("-o- Method setUpChild()");
    }
}
