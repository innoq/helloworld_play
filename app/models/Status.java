package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class Status extends Model {

    private static Status instance = null;

    /**
     * Default-Konstruktor, der nicht außerhalb dieser Klasse
     * aufgerufen werden kann
     */
    private Status() {}

    /**
     * Statische Methode, liefert die einzige Instanz dieser
     * Klasse zurück
     */
    public static Status getInstance() {
        if (instance == null) {
            instance = new Status();
        }
        return instance;
    }
}
