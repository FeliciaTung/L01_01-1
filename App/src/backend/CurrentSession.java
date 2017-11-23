package backend;

import holders.Assignment;
import holders.User;

public class CurrentSession {
    public static User user = null;
    public static Assignment assignment = null;
    public static boolean addingAssignment = false;
}
