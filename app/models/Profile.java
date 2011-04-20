package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    //
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
    public Collection<ProfileAttribute> profileAttribute = null;
    //
    @Required
    @OneToMany
    public List<Relation> contact = new ArrayList<Relation>();
    //
    @OneToMany(mappedBy="source")
    public Collection<Relation> relations = null;
    //
    @OneToMany(mappedBy="destination")
    public Collection<Relation> incomingRelations = null;
    //
    @OneToMany
    @Column(name = "from_id")
    public Collection<Message> sendMessages = null;
    //
    @OneToMany
    @Column(name = "to_id")
    public Collection<Message> receivedMessages = null;
    //
    @OneToMany(mappedBy = "profile")
    public Collection<Status> statuses = null;
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
