package controllers;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import models.Address;
import models.Profile;
import models.ProfileAttribute;
import models.Status;
import models.User;
import play.Logger;
import play.data.validation.MaxSize;
import play.data.validation.Required;
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

    public static void show(int id) {
        Logger.info("-i- public static void show(int id)");
        Logger.info("-v- Profiles ID: %d", id);
        User user = User.findById(Long.parseLong(Session.current().get("user")));
        Logger.info("-v- user: " + user);
        Profile profile = Profile.findById(user.id);
        Logger.info("-v- profile: " + profile);
        List<Status> statuses = Status.find("byProfile", profile).fetch();
        Logger.info("-v- size :" + statuses.size());
        Collections.reverse(statuses);
        renderArgs.put("-v- count", statuses.size());
        renderArgs.put("-v- statuses", statuses);
        Logger.info("-o- public static void show()");
        Address businessAddress = profile.businessAddress;
        render("profiles/show.html", user, profile, statuses, businessAddress);
    }

    public static void privat(long id) {
        Logger.info("-i- public static void privat(long id)");
        Logger.info("-v- id: " + id);
        User user = User.findById(id);
        Logger.info("-v- user: " + user);
        Profile profile = Profile.findById(user.id);
        Logger.info("-v- profile: " + profile);
        ProfileAttribute profileAttribute = null;
//        Logger.info("-v- profileAttribute: " + user.profile.profileAttribute);
//        if (user.profile.profileAttribute.isEmpty()) {
//            redirect("/profiles/edit");
//        } else {
//            List<ProfileAttribute> list = ProfileAttribute.find("byProfile", profile).fetch();
//            if (list.size() > 0) {
//                profileAttribute = list.get(list.size() - 1);
//                profile.profileAttribute.add(profileAttribute);
//            } else {
//                profile.profileAttribute = new HashSet<ProfileAttribute>(1);
//            }
//        }
//        Logger.info("-v- profileAttribute: " + user.profile.profileAttribute);
        List<Status> statuses = Status.find("byProfile", profile).fetch();
        Logger.info("-v- size :" + statuses.size());
        Collections.reverse(statuses);
//        renderArgs.put("statuses", statuses);
//        renderArgs.put("companyEmail", profileAttribute.companyEmail);
//        renderArgs.put("companyPhone", profileAttribute.companyPhone);
//        renderArgs.put("mobilePhone", profileAttribute.mobilePhone);
//        renderArgs.put("privateEmail", profileAttribute.privateEmail);
//        renderArgs.put("privatePhone", profileAttribute.privatePhone);
        Address privateAddress = profile.privateAddress;
        Address businessAddress = profile.businessAddress;
        Logger.info("-o- public static void privat(long id)");
        render("profiles/privat.html", user, privateAddress, businessAddress);
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
        long count = status.count("byProfile", profile);
        renderArgs.put("count", count);
        List<Status> statuses = Status.find("byProfile", profile).fetch();
        Logger.info("-v- size :" + statuses.size());
        Collections.reverse(statuses);
        renderArgs.put("statuses", statuses);
        ProfileAttribute profileAttribute = ProfileAttribute.find("byProfile", profile).first();
        //ProfileAttribute profileAttribute = ProfileAttribute.findById(profile.id);
//        if (profileAttribute != null) {
//            if (profileAttribute.companyEmail == null) {
//                flash.put("companyEmail", null);
//            } else {
//                flash.put("companyEmail", profileAttribute.companyEmail);
//            }
//            if (profileAttribute.privateEmail == null) {
//                flash.put("privateEmail", null);
//            } else {
//                flash.put("privateEmail", profileAttribute.privateEmail);
//            }
//            if (profileAttribute.companyPhone == null) {
//                flash.put("companyPhone", null);
//            } else {
//                flash.put("companyPhone", profileAttribute.companyPhone);
//            }
//            if (profileAttribute.mobilePhone == null) {
//                flash.put("mobilePhone", null);
//            } else {
//                flash.put("mobilePhone", profileAttribute.mobilePhone);
//            }
//            if (profileAttribute.privatePhone == null) {
//                flash.put("privatePhone", null);
//            } else {
//                flash.put("privatePhone", profileAttribute.privatePhone);
//            }
//        }

        Address businessAddress = null;
        if (user.profile.businessAddress != null) {
            businessAddress = user.profile.businessAddress.findById(user.profile.businessAddress.id);
            flash.put("businessStreet", businessAddress.street);
            flash.put("businessZip", businessAddress.zip);
            flash.put("businessCity", businessAddress.city);
        }
        if (user.profile.privateAddress != null) {
            Address privateAddress = user.profile.privateAddress.findById(user.profile.privateAddress.id);
            flash.put("privateStreet", privateAddress.street);
            flash.put("privateZip", privateAddress.zip);
            flash.put("privateCity", privateAddress.city);
        }

        Logger.info("-o- public static void edit()");
        render("profiles/edit.html", user, user.profile);
    }

    public static void update(String firstName,
            @Required(message = "required") String lastName,
            String profession,
            @Required(message = "required") String company,
            @MaxSize(512) String about,
            boolean bCompanyEmail,
            boolean bPrivateEmail,
            boolean bCompanyPhone,
            boolean bMobilePhone,
            boolean bPrivatePhone,
            String companyEmail,
            String privateEmail,
            String companyPhone,
            String mobilePhone,
            String privatePhone,
            String businessStreet,
            String businessZip,
            String businessCity,
            String privateStreet,
            String privateZip,
            String privateCity) {
        Logger.info("-i- public static void update()");
        checkAuthenticity();
        //validation.maxSize(about, 512);
        User user = null;
        if (validation.hasErrors()) {
            for (play.data.validation.Error error : validation.errors()) {
                params.flash();
                validation.keep();
                edit();
            }
        } else {
            user = User.findById(Long.parseLong(Session.current().get("user")));
            user.profile = Profile.findById(user.id);
            if (user.profile == null) {
                user.profile = new Profile();
                Date date = new Date(System.currentTimeMillis());
                user.profile.createdAt = date;
                user.profile.updatedAt = date;
            } else {
                user.profile.firstName = firstName;
                user.profile.lastName = lastName;
                user.profile.profession = profession;
                user.profile.company = company;
                user.profile.about = about;
            }
//            ProfileAttribute profileAttribute = ProfileAttribute.find("byProfile", user.profile).first();
//            if (profileAttribute == null) {
//                profileAttribute = new ProfileAttribute();
//                profileAttribute.profile = user.profile;
//
//            }
//            if (bCompanyEmail) {
//                profileAttribute.companyEmail = null;
//            } else {
//                if (companyEmail == null || companyEmail.equalsIgnoreCase("")) {
//                    profileAttribute.companyEmail = profileAttribute.companyEmail;
//                } else {
//                    profileAttribute.companyEmail = companyEmail;
//                }
//            }
//            if (bPrivateEmail) {
//                profileAttribute.privateEmail = null;
//            } else {
//                if (privateEmail == null || privateEmail.equalsIgnoreCase("")) {
//                    profileAttribute.privateEmail = profileAttribute.privateEmail;
//                } else {
//                    profileAttribute.privateEmail = privateEmail;
//                }
//            }
//            if (bCompanyPhone) {
//                profileAttribute.companyPhone = null;
//            } else {
//                if (companyPhone == null || companyPhone.equalsIgnoreCase("")) {
//                    profileAttribute.companyPhone = profileAttribute.companyPhone;
//                } else {
//                    profileAttribute.companyPhone = companyPhone;
//                }
//            }
//            if (bMobilePhone) {
//                profileAttribute.mobilePhone = null;
//            } else {
//                if (mobilePhone == null || mobilePhone.equalsIgnoreCase("")) {
//                    profileAttribute.mobilePhone = profileAttribute.mobilePhone;
//                } else {
//                    profileAttribute.mobilePhone = mobilePhone;
//                }
//            }
//            if (bPrivatePhone) {
//                profileAttribute.privatePhone = null;
//            } else {
//                if (privatePhone == null || privatePhone.equalsIgnoreCase("")) {
//                    profileAttribute.privatePhone = profileAttribute.privatePhone;
//                } else {
//                    profileAttribute.privatePhone = privatePhone;
//                }
//            }
//            profileAttribute.save();
            if (!businessStreet.isEmpty()
                    || !businessZip.isEmpty()
                    || !businessCity.isEmpty()) {
                if (user.profile.businessAddress == null) {
                    user.profile.businessAddress = new Address();
                }
                if (businessStreet == null || businessStreet.equalsIgnoreCase("")) {
                    user.profile.businessAddress.street = user.profile.businessAddress.street;
                } else {
                    user.profile.businessAddress.street = businessStreet;
                }
                if (businessZip == null || businessZip.equalsIgnoreCase("")) {
                    user.profile.businessAddress.zip = user.profile.businessAddress.zip;
                } else {
                    user.profile.businessAddress.zip = businessZip;
                }
                if (businessCity == null || businessCity.equalsIgnoreCase("")) {
                    user.profile.businessAddress.city = user.profile.businessAddress.city;
                } else {
                    user.profile.businessAddress.city = businessCity;
                }
                //user.profile.businessAddress.profile = user.profile;
                user.profile.businessAddress.save();
            }
            if (!privateStreet.isEmpty()
                    || !privateZip.isEmpty()
                    || !privateCity.isEmpty()) {
                if (user.profile.privateAddress == null) {
                    user.profile.privateAddress = new Address();
                }
                if (privateStreet == null || privateStreet.equalsIgnoreCase("")) {
                    user.profile.privateAddress.street = user.profile.privateAddress.street;
                } else {
                    user.profile.privateAddress.street = privateStreet;
                }

                if (privateZip == null || privateZip.equalsIgnoreCase("")) {
                    user.profile.privateAddress.zip = user.profile.privateAddress.zip;
                } else {
                    user.profile.privateAddress.zip = privateZip;
                }
                if (privateCity == null || privateCity.equalsIgnoreCase("")) {
                    user.profile.privateAddress.city = user.profile.privateAddress.city;
                } else {
                    user.profile.privateAddress.city = privateCity;
                }
                //user.profile.privateAddress.profile = user.profile;
                user.profile.privateAddress.save();
            }
            user.profile.save();
        }
        Status status = Status.find("byProfile", user.profile).first();
        long count = status.count("byProfile", user.profile);
        renderArgs.put("count", count);
        List<Status> statuses = Status.find("byProfile", user.profile).fetch();
        Logger.info("-v- size :" + statuses.size());
        Collections.reverse(statuses);
        renderArgs.put("statuses", statuses);
        renderArgs.put("user", user);
        renderArgs.put("user.profile", user.profile);
        Address businessAddress = user.profile.businessAddress;
        Logger.info("-o- public static void update()");
        render("profiles/show.html", businessAddress);

    }
}
