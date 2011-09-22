package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class ProfileMessage extends Model {
    
    public String subject;
    public String body;
    public Profile from;
    public Profile to;
    public Date createdAt;
    public Date updatedAt;
    
}