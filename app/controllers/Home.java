package controllers;

import play.mvc.Controller;

/**
 *
 * @editor Folkert Meeuw
 */

public class Home extends Application {

    private static boolean dashboard;

    public static void dashboard() {
        dashboard = true;
        renderArgs.put("dashboard", dashboard);
        System.out.println("dashboard " + dashboard);
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
