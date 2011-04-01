package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.db.jpa.Model;


/**
 *
 * @editor Folkert Meeuw
 */

@Entity
@Table(name="T_COMMENT")
public class Comment extends Model {
 
    @Required
    public String author;
    
    @Required
    public Date postedAt;
     
    @Lob
    @Required
    @MaxSize(10000)
    public String content;
    
    @ManyToOne
    @Required
    public Post post;
    
    public Comment(Post post, String author, String content) {
        this.post = post;
        this.author = author;
        this.content = content;
        this.postedAt = new Date();
    }
    
    public String toString() {
        return content.length() > 50 ? content.substring(0, 50) + "..." : content;
    }
 
}