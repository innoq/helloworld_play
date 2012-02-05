package controllers;

import java.util.List;
import models.Address;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class Addresses extends Controller {
    public static void index() {
        List<Address> entities = models.Address.all().fetch();
        render(entities);
    }

    public static void create(Address entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        Address entity = Address.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        Address entity = Address.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        Address entity = Address.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid Address entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "Address"));
        index();
    }

    public static void update(@Valid Address entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "Address"));
        index();
    }
}
