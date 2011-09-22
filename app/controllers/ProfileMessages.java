package controllers;

import java.util.List;
import models.ProfileMessage;
import play.mvc.Controller;
import play.data.validation.Valid;
import play.i18n.Messages;

public class ProfileMessages extends Controller {
    public static void index() {
        List<ProfileMessage> entities = models.ProfileMessage.all().fetch();
        render(entities);
    }

    public static void create(ProfileMessage entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        ProfileMessage entity = ProfileMessage.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        ProfileMessage entity = ProfileMessage.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        ProfileMessage entity = ProfileMessage.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid ProfileMessage entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "Message"));
        index();
    }

    public static void update(@Valid ProfileMessage entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "Message"));
        index();
    }
}
