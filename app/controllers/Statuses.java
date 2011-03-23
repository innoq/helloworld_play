package controllers;

import java.util.Iterator;
import java.util.List;
import models.Profile;
import models.Status;
import play.Logger;

/**
 *
 * @editor Folkert Meeuw
 */
public class Statuses extends Application {

    public static void index(String message) {
        Logger.info("Method index");
        render();
    }

    public static void form() {
        Logger.info("Method form()");
        List<Status> statuses = Status.findAll();
        /*Iterator<Status> i = statuses.listIterator();
        while (i.hasNext()) {
            Status s = i.next();
            Logger.info("Status " + s.toString());
            if(s.profile!=null){
                Logger.info("FullName " + s.profile.firstName);
            }

        }*/
        render("statuses/form.html", statuses);
    }

    public static void save(String message) {
        Logger.info("Method save()");
        Logger.info("Message " + message);
        Profile profile = currentUser().profile;
        Status status = new Status(message, profile);
        validation.valid(status);
        if (validation.hasErrors()) {
            render("statuses/form.html", status);
        }
        // Save
        status.save();
        form();
        render();
    }
}
