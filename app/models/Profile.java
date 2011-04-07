package models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import play.data.validation.Required;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
@Table(name = "T_PROFILE")
public class Profile extends Model {

    @OneToOne
    public User user = null;
    @Required
    @OneToOne
    @Embedded
    public Address privateAddress = null;
    //
    @Required
    @OneToOne
    @Embedded
    public Address businessAddress = null;
    //
    @Required
    @OneToMany(mappedBy = "profile")
    @Embedded
    public Set<ProfileAttribute> profileAttribute = null;
    //
    @Required
    @OneToMany
    public Set<Contact> contact = null;
    //
    @OneToMany
    @JoinColumn(name = "source_id")
    public Set<Relation> relations = null;
    //
    @OneToMany
    @Column(name = "destination_id")
    public Set<Relation> incomingRelations = null;
    //
    @OneToMany
    @Column(name = "from_id")
    public Set<Message> sendMessages = null;
    //
    @OneToMany
    @Column(name = "to_id")
    public Set<Message> receivedMessages = null;
    //
    @OneToMany
    public Set<Status> statuses = null;
    //
    public String profession = null;
    public String company = null;
    public String about = null;
    public Date createdAt = null;
    public Date updatedAt = null;
    public String firstName = "";
    public String lastName = "";
    public String fullName = "";
    public String photoFileName = null;
    public String photoContentType = null;
    public int photoFileSize = -1;

    public Profile() {
        fullName = firstName.trim() + lastName.trim();
    }

    public String getFullName() {
        if(firstName == null){
            firstName = "n.n.";
            return firstName;
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
