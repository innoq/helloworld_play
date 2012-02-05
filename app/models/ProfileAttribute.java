package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class ProfileAttribute extends Model {

    public String type;
    public String value;
    @ManyToOne
    public Profile profile;

}
