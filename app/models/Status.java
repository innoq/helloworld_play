package models;

import java.util.Date;
import javax.persistence.Column;
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
@Table(name = "T_STATUS")
public class Status extends Model {

    @Required(message = "required")
    public String message;
    @ManyToOne
    public Profile profile;
    @Column(updatable = false)
    public Date createdAt;
    @Column(updatable = true)
    public Date updatedAt;

    public Status() {
        message = null;
        profile = null;
        createdAt = null;
        updatedAt = null;
    }

    public Status(@Required(message = "required") String message, Profile profile, Date createdAt, Date updatedAt) {
        this.message = message;
        this.profile = profile;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
