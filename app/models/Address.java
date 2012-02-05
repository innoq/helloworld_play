package models;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class Address extends Model {

    public String street;
    public String city;
    public String zip;
    public String country;
}
