
import java.util.Iterator;
import java.util.List;
import play.jobs.*;
import play.test.*;

import models.*;
import play.Logger;
import play.libs.Crypto;

@OnApplicationStart
public class Bootstrap extends Job {

    public void doJob() {
        Logger.info("-i- Method doJob()");
        // Check if the database is empty
        Fixtures.deleteAll();
        Fixtures.load("initial-data.yml");
        List<User> userList = User.findAll();
        Iterator iterator = userList.iterator();
        User user = null;
        while (iterator.hasNext()) {
            user = (User) iterator.next();
            String crypted = Crypto.passwordHash(user.password);
            user.password = crypted;
            user.save();
        }
        Logger.info("-o- Method doJob()");
    }
}
