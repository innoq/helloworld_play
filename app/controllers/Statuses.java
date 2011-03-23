package controllers;

import java.util.Iterator;
import java.util.List;
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
        Iterator i = statuses.listIterator();
        while (i.hasNext()) {
            Logger.info("Status "+i.next().toString());
        }
        render("statuses/form.html", statuses);
    }

    public static void save(String message) {
        Logger.info("Method save()");
        Logger.info("Message " + message);
        Status status = new Status(message);
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
