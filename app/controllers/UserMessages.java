package controllers;

import java.util.List;
import models.UserMessage;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class UserMessages extends Controller {
    public static void index() {
        List<UserMessage> entities = models.UserMessage.all().fetch();
        render(entities);
    }

    public static void create(UserMessage entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        UserMessage entity = UserMessage.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        UserMessage entity = UserMessage.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        UserMessage entity = UserMessage.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid UserMessage entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "UserMessage"));
        index();
    }

    public static void update(@Valid UserMessage entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "UserMessage"));
        index();
    }
}
