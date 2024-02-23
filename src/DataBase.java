import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataBase {
    public DataBase(){
        initialUsers();
    }
    private static final HashMap<String,String> users = new HashMap<>();

    private User LoggedInUser;

    public void initialUsers(){
        users.put("402131241","1234");
    }

    public HashMap<String, String> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return LoggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        LoggedInUser = loggedInUser;
    }

public boolean alreadyExists(String studentNumber){
        return users.containsKey(studentNumber);
}
}
