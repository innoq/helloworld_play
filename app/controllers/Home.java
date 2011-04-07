package controllers;

import java.util.Collections;
import java.util.List;
import models.Profile;
import models.Status;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Home extends Application {

    public static void dashboard() {
        Logger.info("-i- public static void dashboard()");
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("user: " + user);
        Profile profile = Profile.findById(user.id);
        Logger.info("profile: " + profile);
        List<Status> statuses = Status.find("byProfile", profile).fetch();
        Logger.info("size :" + statuses.size());
        Collections.reverse(statuses);
        Session.current().put("statuses", statuses);
        Logger.info("-o- public static void dashboard()");
        render("home/dashboard.html", user, profile);
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
