package controllers;

import java.util.List;
import models.User;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class Users extends Controller {
    public static void index() {
        List<User> entities = models.User.all().fetch();
        render(entities);
    }

    public static void create(User entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        User entity = User.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        User entity = User.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        User entity = User.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid User entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "User"));
        index();
    }

    public static void update(@Valid User entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "User"));
        index();
    }
}
