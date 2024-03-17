package tables;

import animals.Animal;
import data.AnimalData;
import db.MySQLConnector;
import exception.AnimalNotSupported;
import factory.AnimalFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AnimalTable extends AbsTable {

    public AnimalTable() {
        super("animal");
        columns = new String[6];
        columns[0] = "id bigint PRIMARY KEY AUTO_INCREMENT";
        columns[1] = "type varchar(20) NOT NULL";
        columns[2] = "name varchar(50) NOT NULL";
        columns[3] = "color varchar(20) NOT NULL";
        columns[4] = "weight double NOT NULL";
        columns[5] = "age int NOT NULL";
        create();
    }

    public void update(String id, Animal animal) {
        String weight = Double.toString(animal.getWeight()).replace(',', '.');
        this.dbConnector = new MySQLConnector();
        this.dbConnector.execute(String.format("UPDATE %s " +
                        "SET type ='%s'," +
                        "name = '%s'," +
                        "color = '%s'," +
                        "weight = %s," +
                        "age = %d " +
                        "WHERE id=%s",
                tableName, animal.getType(), animal.getName(), animal.getColor(), weight, animal.getAge(), id));
        this.dbConnector.close();
    }

    public void write(Animal animal) {
        String weight = Double.toString(animal.getWeight()).replace(',', '.');
        this.dbConnector = new MySQLConnector();
        this.dbConnector.execute(String.format("INSERT INTO %s (type,name,color,weight,age) " +
                        "VALUES('%s','%s','%s','%s','%d')",
                tableName, animal.getType(), animal.getName(), animal.getColor(), weight, animal.getAge()));
        this.dbConnector.close();
    }


    public ArrayList<Animal> read(String... columns) throws AnimalNotSupported {
        ArrayList<Animal> animals = new ArrayList<>();
        String reqColumns = "*";
        ResultSet resultSet;
        AnimalData animalData = null;

        if (columns.length != 0) {
            reqColumns = String.join(",", columns);
        }
        dbConnector = new MySQLConnector();
        resultSet = this.dbConnector.executeResult(String.format("SELECT %s FROM %s", String.join(",", reqColumns), this.tableName));

        try {
            while (resultSet.next()) {
                animalData = AnimalData.valueOf(resultSet.getString("type"));
                animals.add(new AnimalFactory().create(
                        animalData,
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("color"),
                        resultSet.getLong("id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnector.close();
        }
        return animals;
    }

    public ArrayList<Animal> read(AnimalData type, String... columns) throws AnimalNotSupported {
        ArrayList<Animal> animals = new ArrayList<>();
        String reqColumns = "*";
        ResultSet resultSet;
        AnimalData animalData = null;

        if (columns.length != 0) {
            reqColumns = String.join(",", columns);
        }
        dbConnector = new MySQLConnector();
        resultSet = this.dbConnector.executeResult(String.format("SELECT %s FROM %s WHERE type ='%s'", String.join(",", reqColumns), this.tableName, type.toString()));

        try {
            while (resultSet.next()) {
                animalData = AnimalData.valueOf(resultSet.getString("type"));
                animals.add(new AnimalFactory().create(
                        animalData,
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getDouble("weight"),
                        resultSet.getString("color"),
                        resultSet.getLong("id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnector.close();
        }
        return animals;
    }
}
