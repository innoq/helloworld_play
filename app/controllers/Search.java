package controllers;

import play.mvc.Controller;

/**
 *
 * @editor Folkert Meeuw
 */

public class Search extends Application {

    public static void search(){
        System.out.println("search");
        render("search/search.html");
    }

}
