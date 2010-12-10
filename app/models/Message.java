package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

/**
 *
 * @editor Folkert Meeuw
 */
@Entity
public class Message extends Model {
    
    public String subject;
    public String body;
    public int fromId;
    public int toId;
    public Date createdAt;
    public Date updatedAt;
    
}