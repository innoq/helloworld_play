package models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class Profile extends Model {

    public String profession;
    public String company;
    @Lob
    public String about;
    @OneToOne(optional = true)
    public Address privateAddress;
    @OneToOne(optional = true)
    public Address businessAddress;
    public Date createdAt;
    public Date updatedAt;
    public String firstName;
    public String lastName;
    public Blob photo;
    @OneToMany(mappedBy = "profile")
    public List<ProfileAttribute> attributes;
    @OneToMany(mappedBy = "source")
    public List<Relation> relationsFrom;
    @OneToMany(mappedBy = "destination")
    public List<Relation> relationsTo;

    public Profile() {
        this.createdAt = new Date(new Date().getTime());
        this.updatedAt = new Date(new Date().getTime());
    }

    public String getFullName() {
        if (firstName == null || lastName == null) {
            return "n.n.";
        }
        return firstName.trim() + " " + lastName.trim();
    }

    public String getPhotoFileName() {
        if (photo == null) {
            return "images/user.png";
        }
        return photo.getFile().getName();
    }
}
