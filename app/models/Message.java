package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class Message extends Model {

    public String subject;
    @Lob
    public String body;
    @ManyToOne
    public User fromUser;
    @ManyToOne
    public User toUser;
    public Date createdAt;
    public Date updatedAt;

    public Message() {
        this.createdAt = new Date(new Date().getTime());
        this.updatedAt = new Date(new Date().getTime());
    }
}
