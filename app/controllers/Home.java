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

    private static boolean dashboard;

    public static void dashboard() {
        //dashboard = true;
        //renderArgs.put("dashboard", dashboard);

        Logger.info("method dashboard");
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("user: " + user);
        Profile profile = Profile.findById(user.id);
        Logger.info("profile: " + profile);
        List<Status> statuses =  Status.find("byProfile", profile).fetch();
        Logger.info("size :" + statuses.size());
        Collections.reverse(statuses);
        Session.current().put("statuses", statuses);
        render("home/dashboard.html");
    }

    public static void imprint() {
        System.out.println("imprint");
        render("home/imprint.html");
    }

    public static void about() {
        System.out.println("about");
        render("home/about.html");
    }

}
