package controllers;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.junit.*;
import models.*;
import play.Logger;
import play.libs.Crypto;
import play.modules.paginate.ValuePaginator;
import play.test.Fixtures;

public class ContactsTest extends BasicTest {

    @Test
    public void testIndex() {
        System.out.println("index");

        User user = User.connect("james01", "CijT15M8I5ETmNX09SeMEQ==");
        assertEquals(user.login, "james01");
        Profile profile = Profile.findById(user.id);
        assertTrue(profile.contact.isEmpty());
        List<Relation> list = Relation.find("byDestination", profile).fetch();
        assertNotNull(list);
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 3);
        ListIterator<Relation> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Relation r = listIterator.next();
            if (r.accepted) {
                profile.contact.add(r);
            }
        }

        ValuePaginator<Relation> paginator = new ValuePaginator(profile.contact);
        paginator.setBoundaryControlsEnabled(false);
        assertNotNull(paginator);
        assertEquals(paginator.size(), 3);
    }

    @Test
    public void testCreate() {
        System.out.println("create");

        User firstUser = User.connect(login, password);
        assertEquals(firstUser.login, "david01");
        Profile firstProfile = Profile.findById(firstUser.id);
        assertNotNull(firstProfile);
        assertEquals(firstProfile.id, new Long(1));

        User secondUser = User.connect("james01", "CijT15M8I5ETmNX09SeMEQ==");
        assertEquals(secondUser.login, "james01");
        Profile secondProfile = Profile.findById(secondUser.id);
        assertNotNull(secondProfile);
        assertEquals(secondProfile.id, new Long(9));

        if (Relation.find("byDestinationAndSource", firstProfile, secondProfile).first() == null) {
            Relation secondIncoming = new Relation();
            assertNotNull(secondIncoming);
            secondIncoming.destination = firstProfile;
            secondIncoming.source = secondProfile;
            secondIncoming.createdAt = new Date();
            secondIncoming.updatedAt = new Date();
            secondIncoming.accepted = true;
            secondIncoming.save();
        }

        if (Relation.find("byDestinationAndSource", secondProfile, firstProfile).first() == null) {
            Relation relation = new Relation();
            assertNotNull(relation);
            relation.destination = secondProfile;
            relation.source = firstProfile;
            relation.createdAt = new Date();
            relation.updatedAt = relation.createdAt;
            relation.accepted = false;
            relation.save();
        }
        //
        User thirdUser = User.connect("charles01", "T/LjKfAEL14nt8NyMAxneg==");
        Profile thirdProfile = Profile.findById(thirdUser.id);
        assertNotNull(thirdProfile);
        assertEquals(thirdProfile.id, new Long(8));
        assertNotNull(thirdProfile.incomingRelations);

        if (Relation.find("byDestinationAndSource", firstProfile, thirdProfile).first() == null) {
            Relation secondIncoming = new Relation();
            assertNotNull(secondIncoming);
            secondIncoming.destination = firstProfile;
            secondIncoming.source = thirdProfile;
            secondIncoming.createdAt = new Date();
            secondIncoming.updatedAt = new Date();
            secondIncoming.accepted = true;
            secondIncoming.save();
        }

        if (Relation.find("byDestinationAndSource", thirdProfile, firstProfile).first() == null) {
            Relation relation = new Relation();
            assertNotNull(relation);
            relation.destination = thirdProfile;
            relation.source = firstProfile;
            relation.createdAt = new Date();
            relation.updatedAt = relation.createdAt;
            relation.accepted = false;
            relation.save();
        }
        //
        User fourthUser = User.connect("charles01", "T/LjKfAEL14nt8NyMAxneg==");
        Profile fourthProfile = Profile.findById(fourthUser.id);
        assertNotNull(fourthProfile);
        assertEquals(fourthProfile.id, new Long(8));
        assertNotNull(fourthProfile.incomingRelations);

        if (Relation.find("byDestinationAndSource", firstProfile, fourthProfile).first() == null) {
            Relation secondIncoming = new Relation();
            assertNotNull(secondIncoming);
            secondIncoming.destination = firstProfile;
            secondIncoming.source = fourthProfile;
            secondIncoming.createdAt = new Date();
            secondIncoming.updatedAt = new Date();
            secondIncoming.accepted = true;
            secondIncoming.save();
        }

        if (Relation.find("byDestinationAndSource", fourthProfile, firstProfile).first() == null) {
            Relation relation = new Relation();
            assertNotNull(relation);
            relation.destination = fourthProfile;
            relation.source = firstProfile;
            relation.createdAt = new Date();
            relation.updatedAt = relation.createdAt;
            relation.accepted = false;
            relation.save();
        }

    }

    @Override
    @Before
    public void setUpChild() {
        //Fixtrue
        Fixtures.deleteAll();
        Fixtures.load("initial-data.yml");
        //User
        List<User> userList = User.findAll();
        Iterator iterator = userList.iterator();
        User user = null;
        while (iterator.hasNext()) {
            user = (User) iterator.next();
            String crypted = Crypto.passwordHash(user.password);
            user.password = crypted;
            user.save();
        }
        //Default
        login = "david01";
        password = "yyoCrp3VOszqD5odAb6Y+w==";
    }
}
