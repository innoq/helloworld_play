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
        render();
    }

    public static void imprint() {
        System.out.println("imprint");
        render();
    }

    public static void about() {
        System.out.println("about");
        render();
    }

}
