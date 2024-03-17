package tables;


import animals.Animal;
import db.IDBConnector;
import db.MySQLConnector;

import java.sql.ResultSet;
import java.util.Map;

public abstract class AbsTable {
    String tableName;
    IDBConnector dbConnector;
    //protected Map<String,String> columns;
    String[] columns;

    public AbsTable(String tableName) {
        this.tableName = tableName;
    }

    //public void create(String... columns){
    public void create() {
        this.dbConnector = new MySQLConnector();
        this.dbConnector.execute(String.format("CREATE TABLE IF NOT EXISTS " +
                "%s (%s)", this.tableName, String.join(",", this.columns)));
        this.dbConnector.close();
    }

    public void drop() {
        this.dbConnector = new MySQLConnector();
        this.dbConnector.execute(String.format("DROP TABLE  %s", this.tableName));
        this.dbConnector.close();
    }
}
