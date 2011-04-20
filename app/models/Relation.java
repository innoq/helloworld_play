package models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
@Table(name = "T_RELATION")
public class Relation extends Model {

    @ManyToOne
    public Profile source;
    @ManyToOne
    public Profile destination;
    @Column(length = 255, nullable = true)
    public String comment;
    @Column(updatable = true, nullable = true)
    public boolean accepted;
    @Column(updatable = false, nullable = false)
    public Date createdAt;
    @Column(updatable = true, nullable = false)
    public Date updatedAt;

    public Relation() {
        source = null;
        destination = null;
        comment = null;
        accepted = false;
        createdAt = null;
        updatedAt = null;
    }
}
