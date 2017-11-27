package backend;

import holders.Assignment;
import holders.User;

/**
 * Keep track of current session data, including the currently signed in user and
 * if user is viewing an assignment, keep track of the assignment as well
 */
public class CurrentSession {
    public static User user = null;
    public static Assignment assignment = null;
    public static boolean addingAssignment = false;
}
