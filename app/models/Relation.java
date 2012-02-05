package models;

import java.util.Date;
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
    public String comment;
    public boolean accepted;
    public Date createdAt;
    public Date updatedAt;

    public Relation() {
        this.createdAt = new Date(new Date().getTime());
        this.updatedAt = new Date(new Date().getTime());
    }
}
