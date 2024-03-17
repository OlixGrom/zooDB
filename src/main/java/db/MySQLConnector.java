package db;

import tools.config.ConfigProperties;
import tools.config.IConfig;

import java.sql.*;
import java.util.Map;

public class MySQLConnector implements IDBConnector {
    private static Statement statement = null;
    private static Connection connection = null;

    {
        connect();
    }

    private void connect() {
        IConfig config = new ConfigProperties();
        Map<String, String> settings = config.getConfig();
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        String.format("%s/%s", settings.get("url"), settings.get("db_name")),
                        settings.get("user"),
                        settings.get("password"));
            }

            if (statement == null) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (statement != null) {
            try {
                statement.close();
                statement = null;

            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (connection != null) {
                try {
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }
    }

    @Override
    public void execute(String sql) {
        try {
            this.statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet executeResult(String sql) {
        try {
            return this.statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
