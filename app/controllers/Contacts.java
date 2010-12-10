package controllers;

import play.mvc.Controller;

/**
 *
 * @editor Folkert Meeuw
 */

public class Contacts extends Application {

    private static boolean contacts;

    public static void index() {
        contacts = true;
        renderArgs.put("contacts", contacts);
        System.out.println("contacts " + contacts);
        render();
    }

}
