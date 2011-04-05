package models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
@Embeddable
@Table(name = "T_PROFILE_ATTRIBUTE")
public class ProfileAttribute extends Model {

    public String companyEmail;
    public String privateEmail;
    public String companyPhone;
    public String mobilePhone;
    public String privatePhone;
    @ManyToOne
    @JoinColumn
    public Profile profile;

    public ProfileAttribute(){
        this.companyEmail = "n.n.";
        this.companyPhone = "n.n.";
        this.mobilePhone = "n.n.";
        this.privateEmail = "n.n.";
        this.privatePhone = "n.n.";
    }

}
