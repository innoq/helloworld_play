package controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import models.Profile;
import models.Relation;
import models.Status;
import models.User;
import play.Logger;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Home extends Application {

    public static void dashboard() {
        Logger.info("-i- public static void dashboard()");
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("-v- user: " + user);
        Profile profile = Profile.findById(user.id);
        Logger.info("-v- profile: " + profile);
        List<Relation> list = Relation.find("byDestination", profile).fetch();
        ListIterator<Relation> listIterator = list.listIterator();
        List<Status> statuses = new ArrayList<Status>();
        while (listIterator.hasNext()) {
            statuses.add((Status)Status.find("byProfile", listIterator.next().source).first());
        }
        Logger.info("-v- size :" + statuses.size());
        Collections.reverse(statuses);
        Logger.info("-o- public static void dashboard()");
        render("home/dashboard.html", user, profile, statuses);
    }

    public static void imprint() {
        Logger.info("-i- public static void imprint()");
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("-o- public static void imprint()");
        render("home/imprint.html", user);
    }

    public static void about() {
        Logger.info("-i- public static void about()");
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("-o- public static void about()");
        render("home/about.html", user);
    }
}
