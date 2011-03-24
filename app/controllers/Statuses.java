package controllers;

import java.util.Collections;
import java.util.List;
import models.Profile;
import models.Status;
import play.Logger;

/**
 *
 * @editor Folkert Meeuw
 */
public class Statuses extends Application {

    public static void form(int size) {
        Logger.info("Method form()");

        List<Status> statuses = Status.findAll();
        List<Status> sublist = null;
      
        Collections.reverse(statuses);

        /*Iterator<Status> i = statuses.listIterator();
        while (i.hasNext()) {
        Status s = i.next();
        Logger.info("Status " + s.toString());
        if(s.profile!=null){
        Logger.info("FullName " + s.profile.firstName);
        }
        }*/

        int start = 0;
        int end = 0;

        if (size == 0) {
            end = 4;
            size = end;
        } else {
            start = size;
            end = start + start;
        }

        if (statuses.size() < end) {
            end = statuses.size();
            size = 0;
        } else {
            size = end;
        }

        sublist = statuses.subList(start, end);
        render("statuses/form.html", sublist, size);
    }

    public static void save(String message) {
        Logger.info("Method save()");
        Profile profile = currentUser().profile;
        Status status = new Status(message, profile);
        validation.valid(status);
        if (validation.hasErrors()) {
            render("statuses/form.html", status);
        }
        // Save
        status.save();
        form(0);
        render();
    }
}
