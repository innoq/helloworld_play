package controllers;

import java.util.Random;
import models.Profile;
import models.User;
import play.Logger;
import play.data.validation.MinSize;
import play.data.validation.Required;
import play.libs.Crypto;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Auth extends Application {

    public static void login() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static void login()");
        User sampleUser = Auth.userByRandomId();
        Logger.info("-v- sampleUser %s", sampleUser);
        String hint = null;
        if (sampleUser == null) {
            Logger.info("check database, table \"user\" and data");
        } else {
            hint = "Try User '" + sampleUser.login
                    + "' with Password '" + sampleUser.password
                    + "' to get started";
        }
        renderArgs.put("hint", hint);
        Logger.info("-o- public static void login()");
        render("auth/login.html");
    }

    public static void authenticate(
            @Required(message = "required") String login,
            @Required(message = "required") String password) {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static void authenticate()");
        //checkAuthenticity();
        if (validation.hasErrors()) {
            for (play.data.validation.Error error : validation.errors()) {
                params.flash();
                validation.keep();
            }
            login();
        } else {
            //User user = User.find("byLoginAndPassword", login, Crypto.passwordHash(password)).first();
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
                Session.current().put("login", login);
                Session.current().put("user", user.id);
                Session.current().put("user.profile.id", user.profile.id);
                Session.current().put("edit", true);
                currentUser();

                //render("home/dashboard.html", login, password, user);
                Logger.info("-o- public static void authenticate()");
                Logger.info("-v- " + user.profile.getFullName());
                renderArgs.put("user", user);
                renderArgs.put("user.profile.id", user.profile.id);
                render("home/dashboard.html", user, user.profile);
            }
        }
    }

    public static void logout() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static void logout()");
        renderArgs.put("user", null);
        renderArgs.put("user.profile.id", null);
        Session.current().clear();
        currentUser();
        Logger.info("-o- public static void logout()");
        render("auth/logout.html");
    }

    public static void register(
            @Required(message = "required") @MinSize(5) String login,
            @Required(message = "required") @MinSize(5) String password) {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static void register()");
        String crypted = Crypto.passwordHash(password);
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
                user = new User(login, crypted);
                //user = new User(login, password);
                user.profile = new Profile();
                user.profile.save();
                user.save();
                Session.current().put("id", user.id);
            } else {
                validation.addError(
                        "register", "login/password exists already, "
                        + "choose different one", "");
                if (validation.hasErrors()) {
                    for (play.data.validation.Error error : validation.errors()) {
                        params.flash();
                        validation.keep();
                    }
                    signup();
                }
            }
        }
        Logger.info("-o- public static void register()");
        render("/auth/login.html");
    }

    public static void signup() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static void signup()");
        Logger.info("-o- public static void signup()");
        render("/auth/register.html");
    }

    public static long countUser() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static long countUser()");
        Long count;
        Random random = new Random();
        if (User.count() > 0) {
            count = Long.valueOf(User.count());
        } else {
            count = 10L;
        }
        Logger.info("-o- public static long countUser()");
        return (count);
    }

    protected static int randomId() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static void randomId()");
        Long count;
        Random random = new Random();
        int randomId;
        if (User.count() > 0) {
            count = Long.valueOf(User.count());
        } else {
            count = 10L;
        }
        randomId = random.nextInt(count.intValue());
        Logger.info("-o- public static void randomId()");
        return (randomId);
    }

    protected static User userByRandomId() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("-i- public static void userByRandomId()");
        Long count;
        Random random = new Random();
        int randomId;
        if (User.count() > 0) {
            count = Long.valueOf(User.count());
        } else {
            count = 10L;
        }
        randomId = random.nextInt(count.intValue());
        User user = User.find("byId", Long.parseLong(String.valueOf(randomId))).first();
        Logger.info("-o- public static void userByRandomId()");
        return (user);
    }
}
