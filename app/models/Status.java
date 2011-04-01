package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
@Table(name="T_STATUS")
public class Status extends Model {

    @Required(message = "required")
    public String message;
    @ManyToOne
    public Profile profile;

    public Status() {
    }

    public Status(String message, Profile profile) {
        this.message = message;
        this.profile = profile;
    }
}
