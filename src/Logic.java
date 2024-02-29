import DataBase.DataBase;

public class Logic {
    private final CLI cli;

    private final DataBase dataBase;

    public Logic() {
        this.cli = new CLI(this);
        dataBase = new DataBase();
        cli.init();
    }
}
