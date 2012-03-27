package controllers;

import java.util.Collections;
import java.util.List;
import models.Profile;
import models.Status;
import models.User;
import play.Logger;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Statuses extends Application {

    public static void form(int size) {
        Logger.info("-i- public static void form()");

        List<Status> statuses = Status.findAll();
        List<Status> sublist = null;

        Collections.reverse(statuses);

        /*Iterator<Status> i = statuses.listIterator();
        while (i.hasNext()) {
        Status s = i.next();
        Logger.info("-v- Status " + s.toString());
        if(s.profile!=null){
        Logger.info("-v- FullName " + s.profile.firstName);
        }
        }*/

        int start = 0;
        int end = 0;

        if (size == 0) {
            end = 25;
            size = end;
        } else {
            end = start = size;
            end += end;
        }

        if (statuses.size() <= end) {
            end = statuses.size();
            size = 0;
        } else {
            size = end;
        }

        sublist = statuses.subList(start, end);
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("-o- public static void form()");
        render("statuses/form.html", sublist, size, user);
    }

    public static void save(String message) {
        Logger.info("-i- public static void save()");
        Profile profile = null;
        //currentUser().profile;
        Status status = new Status(message, profile);
        validation.valid(status);
        if (validation.hasErrors()) {
            Logger.info("-o- public static void save()");
            render("statuses/form.html", status);
        }
        // Save
        status.save();
        form(0);
        Logger.info("-o- public static void save()");
        render();
    }
}
