package controllers;

import java.util.Iterator;
import java.util.Set;
import models.Profile;
import models.Relation;
import models.User;
import play.Logger;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Contacts extends Application {

    public static void index() {
        Logger.info("-i- public static void index()");
        boolean contacts = true;
        renderArgs.put("contacts", contacts);
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("-o- public static void index()");
        render("contacts/index.html", user);
    }

    public static void create() {
        /*User user = User.findById(Long.parseLong(Session.current().get("user")));
        Relation destination = Relation.find("byDestination", user.profile.incomingRelations).first();
        Iterator<Relation> iterator = user.profile.incomingRelations.iterator();
        while(iterator.hasNext()){
            iterator.next().id = destination.id;
        }*/
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
