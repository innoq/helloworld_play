package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */

@Entity
public class Relation extends Model {

    @ManyToOne
    public Profile source;

    @ManyToOne
    public Profile destination;
    
    public Relation() {

    }

}
