package models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
@Embeddable
public class ProfileAttribute extends Model {

    public String companyEmail;
    public String privateEmail;
    public String companyPhone;
    public String mobilePhone;
    public String privatePhone;
    @ManyToOne
    public Profile profile;

    public ProfileAttribute(){
        this.companyEmail = null;
        this.companyPhone = null;
        this.mobilePhone = null;
        this.privateEmail = null;
        this.privatePhone = null;
    }

}
