package controllers;

import java.util.List;
import models.Relation;
import play.mvc.Controller;
import play.i18n.Messages;
import play.data.validation.Validation;
import play.data.validation.Valid;

public class Relations extends Controller {
    public static void index() {
        List<Relation> entities = models.Relation.all().fetch();
        render(entities);
    }

    public static void create(Relation entity) {
        render(entity);
    }

    public static void show(java.lang.Long id) {
        Relation entity = Relation.findById(id);
        render(entity);
    }

    public static void edit(java.lang.Long id) {
        Relation entity = Relation.findById(id);
        render(entity);
    }

    public static void delete(java.lang.Long id) {
        Relation entity = Relation.findById(id);
        entity.delete();
        index();
    }
    
    public static void save(@Valid Relation entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@create", entity);
        }
        entity.save();
        flash.success(Messages.get("scaffold.created", "Relation"));
        index();
    }

    public static void update(@Valid Relation entity) {
        if (validation.hasErrors()) {
            flash.error(Messages.get("scaffold.validation"));
            render("@edit", entity);
        }
        
              entity = entity.merge();
        
        entity.save();
        flash.success(Messages.get("scaffold.updated", "Relation"));
        index();
    }
}
