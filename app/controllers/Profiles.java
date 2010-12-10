package controllers;

import play.Logger;


/**
 *
 * @editor Folkert Meeuw
 */

public class Profiles extends Application {

    public static void _contact_data(){
        System.out.println("_contact_data");
        render();
    }

    public static void _edit_address(){
        System.out.println("_edit_address");
        render();
    }
    
    public static void edit(){
        System.out.println("edit");
        render();
    }

    public static void privat(int id){
        System.out.println("privat");
        render();
    }

    public static void show(int id) {
        Logger.info("Method show(int id)");
        Logger.info("Profiles ID: %d", id);
        render();
    }

}
