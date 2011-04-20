package controllers;

import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import models.Profile;
import models.Relation;
import models.User;
import play.Logger;
import play.modules.paginate.ValuePaginator;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Contacts extends Application {

    public static void index() {
        Logger.info("-i- public static void index()");
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Profile profile = Profile.findById(user.id);
        List<Relation> list = Relation.find("byDestination", profile).fetch();
        ListIterator<Relation> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Relation r = listIterator.next();
            if (r.accepted) {
                profile.contact.add(r);
            }
        }
        Logger.info("-v- Contact: " + profile.contact.size());
        ValuePaginator paginator = new ValuePaginator(profile.contact);
        paginator.setBoundaryControlsEnabled(false);
        paginator.setPageSize(4);
        Logger.info("-o- public static void index()");
        render("contacts/index.html", paginator, user);
    }

    public static void create() {
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Profile userProfile = Profile.findById(user.id);
        Profile sourceProfile = Profile.findById(new Long(10));
        if (!userProfile.relations.contains(sourceProfile)) {
            Relation userIncoming = new Relation();
            userIncoming.source = sourceProfile;
            userIncoming.createdAt = new Date();
            userIncoming.updatedAt = new Date();
            userIncoming.accepted = true;
            userIncoming.save();
            userProfile.save();
        }
        if (!sourceProfile.relations.contains(userProfile)) {
            Relation relation = new Relation();
            relation.source = userProfile;
            relation.accepted = false;
            relation.createdAt = new Date();
            relation.updatedAt = relation.createdAt;
            relation.save();
            sourceProfile.relations.add(relation);
            sourceProfile.save();
        }
        redirect("contacts/index");
        render("contacts/index");
    }

    public static void update() {
        redirect("contacts/index");
        render("contacts/index");
    }

    public static void destroy() {
        redirect("contacts/index");
        render("contacts/index");
    }
}
