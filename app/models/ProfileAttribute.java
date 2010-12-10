package models;

import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */

@Entity
public class ProfileAttribute extends Model {

    public String companyEmail;
    public String privateEmail;
    public String companyPhone;
    public String mobilePhone;
    public String privatePhone;
    
}
