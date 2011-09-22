package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */

@Entity
public class User extends Model {

    @Required(message="required")
    @Column(unique=true)
    public String login;

    @Required(message="required")
    public String password;

    @OneToOne(optional=true)
    public Profile profile;

    public User () {}

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User connect(String login, String password) {
        return find("byLoginAndPassword", login, password).first();
    }

}
