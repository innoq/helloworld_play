package models;

import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.db.jpa.Model;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
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
    @OneToMany
    @Embedded
    public Set<ProfileAttribute> profileAttribute = null;
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
    public StringBuffer about = null;
    public Date createdAt = null;
    public Date updatedAt = null;
    public String firstName = null;
    public String lastName = null;
    public String photoFileName = null;
    public String photoContentType = null;
    public int photoFileSize = 0;

    public Profile() {
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
