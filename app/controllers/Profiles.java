package controllers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import models.Profile;
import models.ProfileAttribute;
import models.Status;
import models.User;
import play.Logger;
import play.mvc.Scope.Session;

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
        Logger.info("-i- public static void edit()");
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Profile profile = Profile.findById(user.id);
        flash.put("firstName", profile.firstName);
        flash.put("lastName", profile.lastName);
        flash.put("profession", profile.profession);
        flash.put("company", profile.company);
        flash.put("about", profile.about);
        Status status = Status.find("byProfile", profile).first();
        long count = status.count("byProfile",profile);
        renderArgs.put("count",count);
        List<Status> statuses =  Status.find("byProfile", profile).fetch();
        Logger.info("size :" + statuses.size());
        Collections.reverse(statuses);
        renderArgs.put("statuses",statuses);
        ProfileAttribute profileAttribute = ProfileAttribute.find("byProfile", profile).first();
        flash.put("companyEmail", profileAttribute.companyEmail);
        flash.put("privateEmail", profileAttribute.privateEmail);
        flash.put("companyPhone", profileAttribute.companyPhone);
        flash.put("mobilePhone", profileAttribute.mobilePhone);
        flash.put("privatePhone", profileAttribute.privatePhone);
        Logger.info("-o- public static void edit()");
        render("profiles/edit.html", user, user.profile);
    }

    public static void privat(long id) {
        Logger.info("-i- public static void privat(long id)");
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
        List<Status> statuses =  Status.find("byProfile", profile).fetch();
        Logger.info("size :" + statuses.size());
        Collections.reverse(statuses);
        renderArgs.put("statuses",statuses);
        renderArgs.put("companyEmail", profileAttribute.companyEmail);
        renderArgs.put("companyPhone", profileAttribute.companyPhone);
        renderArgs.put("mobilePhone", profileAttribute.mobilePhone);
        renderArgs.put("privateEmail", profileAttribute.privateEmail);
        renderArgs.put("privatePhone", profileAttribute.privatePhone);
        Logger.info("-o- public static void privat(long id)");
        render("profiles/privat.html", user);
    }

    public static void show(int id) {
        Logger.info("Method show(int id)");
        Logger.info("Profiles ID: %d", id);
        render("profiles/show.html");
    }
}
