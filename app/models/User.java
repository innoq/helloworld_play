package models;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import play.data.validation.Password;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class User extends Model {

    @Required
    @Column(unique = true)
    public String login;
    @Required
    @Password
    public String password;
    @OneToOne(optional = true)
    public Profile profile;
    public Date createdAt;
    public Date updatedAt;
    
    @OneToMany(mappedBy = "fromUser")
    public List<UserMessage> messagesSend;
    @OneToMany(mappedBy = "toUser")
    public List<UserMessage> messagesReceived;

    public User() {
        this.createdAt = new Date(new Date().getTime());
        this.updatedAt = new Date(new Date().getTime());
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.createdAt = new Date(new Date().getTime());
        this.updatedAt = new Date(new Date().getTime());
    }

    public static User connect(String login, String password) {
        return find("byLoginAndPassword", login, password).first();
    }
}
