package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */

@Entity
@Table(name="T_RELATION")
public class Relation extends Model {

    @ManyToOne
    public Profile source;

    @ManyToOne
    public Profile destination;
    
    public Relation() {

    }

}
