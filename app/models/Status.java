package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    @ManyToOne
    public Profile profile;
    public Date createdAt;
    public Date updatedAt;

    public Status(){
        this.createdAt = new Date(new Date().getTime());
        this.updatedAt = new Date(new Date().getTime());
    }

    public Status(String message, Profile profile) {
        this.message = message;
        this.profile = profile;
        this.createdAt = new Date(new Date().getTime());
        this.updatedAt = new Date(new Date().getTime());
    }

    
}
