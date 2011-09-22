package models;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class Profile extends Model {

    @Required
    @OneToOne
    public Address privateAddress = null;
    //
    @Required
    @OneToOne
    public Address businessAddress = null;
    //
    @Required
    @OneToMany(mappedBy = "profile")
    public Set<ProfileAttribute> profileAttribute = null;
    //
    @Required
    @OneToMany
    public Set<Contact> contact = null;
    //
    @OneToMany(mappedBy = "source")
    public Set<Relation> relations = null;
    //
    @OneToMany(mappedBy = "destination")
    public Set<Relation> incomingRelations = null;
    //
    @OneToMany(mappedBy = "from")
    public Set<ProfileMessage> sendMessages = null;
    //
    @OneToMany(mappedBy = "to")
    public Set<ProfileMessage> receivedMessages = null;
    //
    @OneToMany(mappedBy = "profile")
    public Set<Status> statuses = null;
    //
    @Column(nullable = true)
    public String profession = null;
    @Column(nullable = true)
    public String company = null;
    @Column(nullable = true, length = 512)
    public String about = null;
    @Column(updatable = false)
    public Date createdAt = null;
    @Column(updatable = true)
    public Date updatedAt = null;
    @Column(nullable = true)
    public String firstName = null;
    @Column(nullable = true)
    public String lastName = null;
    public String fullName = null;
    public String photoFileName = null;
    public String photoContentType = null;
    public int photoFileSize = -1;

    public Profile() {
        //fullName = firstName.trim() + lastName.trim();
        //photoFileName = "images/user.png";
    }

    public String getFullName() {
        if (firstName == null || lastName == null) {
            return "n.n.";
        }
        return firstName.trim() + " " + lastName.trim();
    }

    public String getPhotoFileName() {
        if (photoFileName == null) {
            photoFileName = "images/user.png";
        }
        return photoFileName.trim();
    }

}
