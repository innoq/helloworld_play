package models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class Status extends Model {

    @Required(message = "required")
    public String message;
    @OneToOne(optional = true)
    public Profile profile;

    public Status() {
    }

    public Status(String message, Profile profile) {
        this.message = message;
        this.profile = profile;
    }
}
