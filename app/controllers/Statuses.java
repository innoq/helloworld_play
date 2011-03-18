package controllers;

import play.mvc.Controller;

/**
 *
 * @editor Folkert Meeuw
 */

public class Statuses extends Application {

    public static void feed(){
        System.out.println("feed");
        render("statuses/feed.html");
    }

}
