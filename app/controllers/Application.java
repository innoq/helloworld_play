package controllers;

import java.util.List;
import models.Message;
import models.Post;
import models.User;
import play.Logger;
import play.cache.Cache;
import play.libs.Codec;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.Scope.Session;

/**
 *
 * @editor Folkert Meeuw
 */
public class Application extends Controller {

    @Before(unless = {"Auth.login", "Auth.authenticate", "Auth.signup",
                        "Auth.register", "Auth.registerUser"})
    protected static void checkLogin() {
        if (currentUser() == null) {
            Auth.login();
        } else
        {
            renderArgs.put("user", currentUser());
        }
    }

    protected static User currentUser() {
        User user = null;
        String userString = Session.current().get("user");
        if (userString == null) {
        } else {
            user = User.findById(Long.parseLong(userString));
        }
        return user;
    }

    public static void index() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Class: %s, Method: index()", Application.class.getName());

        boolean currentUser = false;
        Post frontPost = Post.find("order by postedAt desc").first();
        List<Post> olderPosts = Post.find("order by postedAt desc").from(1).fetch(10);
        render(frontPost, olderPosts, currentUser);
    }

    public static void show(Long id) {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Class: %s, Method: show()", Application.class.getName());

        Post post = Post.findById(id);
        String randomID = Codec.UUID();
        render(post, randomID);
    }

    public static void messages() {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Class: %s, Method: messages()", Application.class.getName());

        List messages = Cache.get(session.getId() + "messages", List.class);
        if (messages == null) {
            //messages = Message.findById(session.get("user"));
            // Ooops
            //.findByUser(session.get("user"));

            //Session Management
            //Setzt die Gültigkeit für den Cache auf 30 Minuten
            Cache.set(session.getId() + "-messages" + messages, "30mn");
        }
        render(messages);
    }

    public static void messages(int page) {
        //TODO AspectJ Aspekt zur Ausgliederung - Navigation-Tracing
        Logger.info("Class: %s, Method: messages()", Application.class.getName());

        User user = User.find("byEmail", new Object()).first();
        List<Message> messages = Message.find("user = ? and read = false order by date desc", user).from(page * 10).fetch(10);
        render(user, messages);
    }

    public static void linkTo() {

        render();
    }

    public static void imageTag() {
        render();
    }

    /*public static void hasSessionUser() {
    setCurrentUser(Scope.Session.current().contains(getUser().toString()) ? true : false);
    }*/
    /**
     * @return the user
     */
}
