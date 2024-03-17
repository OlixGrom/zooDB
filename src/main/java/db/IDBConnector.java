package db;

import java.sql.ResultSet;

public interface IDBConnector {
    void execute(String sql);
    ResultSet executeResult(String sql);
    void close();


}
