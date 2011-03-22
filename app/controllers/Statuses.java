package controllers;

import play.mvc.Before;
import play.mvc.Controller;

/**
 *
 * @editor Folkert Meeuw
 */

public class Statuses extends Application {

    @Before (unless= {"Auth.login", "Auth.authenticate", "Auth.signup",
    "Auth.register", "Auth.registerUser"})

    public static void index(){

        render("statuses/index.html");
    }

}
