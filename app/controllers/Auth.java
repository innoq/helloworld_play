package controllers;

import java.util.Random;
import models.Profile;
import models.User;
import play.Logger;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Auth extends Application {

    public static void login() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method login()");
        User sampleUser = Auth.userByRandomId();
        Logger.info("Sample User %s", sampleUser);
        String hint = null;
        if (sampleUser == null) {
            Logger.info("check database, table \"user\" and data");
        } else {
            hint = "Try User " + sampleUser.login +
                    " with Password " + sampleUser.password +
                    " to get started";
        }
        renderArgs.put("hint", hint);
        render("auth/login.html");
    }

    public static void authenticate(
            @Required(message = "required") String login,
            @Required(message = "required") String password) {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method authenticate()");
        checkAuthenticity();

        if (validation.hasErrors()) {
            for (play.data.validation.Error error : validation.errors()) {
                params.flash();
                validation.keep();
            }
            login();
        } else {
            User user = User.find("byLoginAndPassword", login, password).first();
            if (user == null) {
                validation.addError(
                        "user", "correct login/password required for", "");
                if (validation.hasErrors()) {
                    for (play.data.validation.Error error : validation.errors()) {
                        params.flash();
                        validation.keep();
                    }
                    login();
                }
            } else {
                Logger.info("User Profile: %s", user.profile);
                Session.current().put("user", user.id);
                Session.current().put("user.profile", user.profile);
                checkLogin();
                //render("home/dashboard.html", login, password, user);
                render("home/dashboard.html", login, password);
            }
        }
    }

    public static void logout() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method logout()");
        Logger.info("User: %s", currentUser());
        Session.current().clear();
        renderArgs.put("user", null);
        render();

    }

    public static void register(
            @Required(message = "required") @MinSize(5) String login,
            @Required(message = "required") @MinSize(5) String password) {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method register()");
        //boolean currentUser = false;
        checkAuthenticity();
        if (validation.hasErrors()) {
            for (play.data.validation.Error error : validation.errors()) {
                params.flash();
                validation.keep();
            }
            signup();
        } else {
            User user = User.find("byLogin", login).first();
            if (user == null) {
                user = new User(login, password);
                user.profile = new Profile();
                user.save();
                Session.current().put("id", user.id);
            } else {
                validation.addError(
                        "register", "login/password exists already, " +
                        "choose different one", "");
                if (validation.hasErrors()) {
                    for (play.data.validation.Error error : validation.errors()) {
                        params.flash();
                        validation.keep();
                    }
                    signup();
                }
            }
        }
        render("/auth/login.html");
    }

    public static void signup() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method signup()");
        render("/auth/register.html");
    }

    public static long countUser() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method countUser()");
        Long count;
        Random random = new Random();
        count = Long.valueOf(User.count());
        return (count);
    }

    public static int randomId() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method randomId()");
        Long count;
        Random random = new Random();
        int randomId;
        count = Long.valueOf(User.count());
        randomId = random.nextInt(count.intValue());
        return (randomId);
    }

    public static User userByRandomId() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Method userByRandomId()");
        Long count;
        Random random = new Random();
        int randomId;
        count = Long.valueOf(User.count());
        randomId = random.nextInt(count.intValue());
        User user = User.find("byId", Long.parseLong(String.valueOf(randomId))).first();
        return (user);
    }
}
