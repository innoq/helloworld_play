package controllers;

import java.util.HashSet;
import java.util.List;
import models.Profile;
import models.ProfileAttribute;
import models.User;
import play.Logger;

/**
 *
 * @editor Folkert Meeuw
 */
public class Profiles extends Application {

    public static void _contact_data() {
        System.out.println("_contact_data");
        render();
    }

    public static void _edit_address() {
        System.out.println("_edit_address");
        render();
    }

    public static void edit() {
        Logger.info("method edit");
        render("profiles/edit.html");
    }

    public static void privat(long id) {
        Logger.info("method privat");
        Logger.info("id: " + id);
        User user = User.findById(id);
        Logger.info("user: " + user);
        Profile profile = Profile.findById(user.id);
        Logger.info("profile: " + profile);
        ProfileAttribute profileAttribute = null;
        Logger.info("profileAttribute: " + user.profile.profileAttribute);
        if (user.profile.profileAttribute.isEmpty()) {
            redirect("/profiles/edit");
        } else {
            List<ProfileAttribute> list = ProfileAttribute.find("byProfile", profile).fetch();
            if (list.size() > 0) {
                profileAttribute = list.get(list.size() - 1);
                profile.profileAttribute.add(profileAttribute);
            } else {
                profile.profileAttribute = new HashSet<ProfileAttribute>(1);
            }
        }
        Logger.info("profileAttribute: " + user.profile.profileAttribute);
        renderArgs.put("companyEmail", profileAttribute.companyEmail);
        renderArgs.put("companyPhone", profileAttribute.companyPhone);
        renderArgs.put("mobilePhone", profileAttribute.mobilePhone);
        renderArgs.put("privateEmail", profileAttribute.privateEmail);
        renderArgs.put("privatePhone", profileAttribute.privatePhone);
        render("profiles/privat.html", user);
    }

    public static void show(int id) {
        Logger.info("Method show(int id)");
        Logger.info("Profiles ID: %d", id);
        render("profiles/show.html");
    }
}
