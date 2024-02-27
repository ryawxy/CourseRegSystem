import DataBase.DataBase;
import User.User;
import User.UserType;
import java.io.IOException;

public class Logic {
    private final CLI cli;

    private final DataBase dataBase;

    public Logic() throws IOException {
        this.cli = new CLI(this);
        dataBase = new DataBase();
       dataBase.initialUsers();
        cli.init();
    }
}
