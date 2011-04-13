package controllers;

import models.User;
import play.Logger;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Application extends Controller {

    public static void index() {
        redirect("auth/login");
    }

    @Before(unless = {"Auth.login", "Auth.authenticate", "Auth.signup",
        "Auth.register", "Auth.registerUser"})
    protected static void checkLogin() {
        Logger.info("-i- protected static void checkLogin()");
        boolean currentUser = Boolean.valueOf(Session.current().get("currentUser"));
        if (!currentUser) {
            redirect("Auth.login", currentUser);
        }
        Logger.info("-o- protected static void checkLogin()");

    }

    protected static void currentUser() {
        Logger.info("-i- protected static void currentUser()");
        User user = null;
        boolean currentUser = false;
        String userString = Session.current().get("user");
        if (userString != null) {
            user = User.findById(Long.parseLong(userString));
        }
        currentUser = (user == null) ? false : true;
        Session.current().put("currentUser", currentUser);
        Logger.info("-v- currentUser: " + currentUser);
        Logger.info("-o- protected static void currentUser()");
    }
}
