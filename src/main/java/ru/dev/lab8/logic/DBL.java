package ru.dev.lab8.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.mysql.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBL {

    public DBL() {
        //Class.forName("org.mariadb.jdbc.Driver");
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db?" + "user=root&password=root");
            statement = connection.createStatement();
            System.out.println("Connected!");
            initialize();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //-------------------------------
    public static final DBL INSTANCE = new DBL();   // SINGLETONE
    //-------------------------------
    private static List<Product> Storage = new ArrayList();
    private List<Product> Row = new ArrayList();
    private Connection connection;
    private Statement statement;
    //-------------------------------
    private final String NAMEUSER = "root";
    private final String PASSWORD = "root";
    private final String URL = "jdbc:mysql://localhost:3306/db";//?" +
    //   "useUnicode=true&useJDBCCompliantTimezoneShift=true&" +
    //   "useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";

    //-------------------------------
    public List getData(int index) {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connecton working");
                if (index > 0) return getByIndex(index);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initialize() {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connecton working");
                clearDB();
                fillDB();
                System.out.println("Database restored!");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List getFullData() {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connecton working");

                ResultSet rs = null;
                rs = statement.executeQuery("SELECT * FROM products");

                Storage.clear();
                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setCount(rs.getInt("count"));
                    product.setCategories(rs.getNString("categories"));
                    Storage.add(product);
                }
                return Storage;
            }
            } catch(SQLException e){
                e.printStackTrace();
            }
         return null;
    }

    private List<Product> getByIndex(int index) {
        String SqlQuery = "SELECT id, name, count, categories FROM products WHERE id = ?";
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(SqlQuery);
            st.setInt(1, index);
            ResultSet rs = null;
            rs = st.executeQuery();
            //Row = FXCollections.observableArrayList();

            while (rs.next()) {
                Product product;
                product = new Product();
                {
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getNString("name"));
                    product.setCount(rs.getInt("count"));
                    product.setCategories(rs.getNString("categories"));
                }
                Row.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addToDB(Product product) {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection working");
                int id = product.getId();
                String name = product.getName();
                int count = product.getCount();
                String cat = product.getCategories();
                String stmnt = "INSERT INTO `db`.`products` (`id`, `name`, `count`, `categories`) VALUES (?, ?, ?, ?) ON DUPLICATE KEY UPDATE" + " `id` = ?, `name` = ?, `count` = ?, `categories` = ?;";
                PreparedStatement statement = connection.prepareStatement(stmnt);
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setInt(3, count);
                statement.setString(4, cat);

                statement.setInt(5, id);
                statement.setString(6, name);
                statement.setInt(7, count);
                statement.setString(8, cat);

                System.out.println(statement.execute());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void clearDB() {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection working");
                Statement statement = connection.createStatement();
                statement.execute("DROP TABLE IF EXISTS `db`.`products`;");
                statement.execute("CREATE TABLE IF NOT EXISTS `db`.`products` " + "(`id` INT NOT NULL AUTO_INCREMENT UNIQUE,`name` VARCHAR(45) NOT NULL,`" + "count` INT(7) NOT NULL,`categories` VARCHAR(45) NOT NULL, " + "PRIMARY KEY (`id`))ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;");
                System.out.println("DB Cleared!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteZero() {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection working");
                Statement statement = connection.createStatement();
                System.out.println(statement.execute("DELETE FROM `db`.`products` WHERE `count` < 1;"));

                System.out.println("Empty fields deleted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(int id) {
        try {
            if (!connection.isClosed()) {
                System.out.println("Connection working");
                String stmnt = "DELETE FROM `db`.`products` WHERE `id` = ?;";
                PreparedStatement prepStmnt = connection.prepareStatement(stmnt);
                prepStmnt.setInt(1, id);
                System.out.println(prepStmnt.execute());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillDB() {
        try {
            System.out.println("DB Filled!");

            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('1', 'lg l90', '1', 'phone 3g 2g cheap quick_memo') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('2', 'lg k8', '10', 'phone 4g knock_code android_7') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('3', 'samsung GT', '12', 'tablet 3g 10_inch') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('4', 'Nokia c6', '1', 'phone 3g') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('5', 'lenovo a3500', '2', 'tablet 3g 1gb') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('6', 'lenovo a319', '0', 'phone 3g 1gb') " + "ON DUPLICATE KEY UPDATE id = id;");
            statement.executeUpdate("INSERT INTO `db`.`products` " + "(`id`, `name`, `count`, `categories`) VALUES " + "('7', 'iphone X', '0', 'phone 4g 3gb') " + "ON DUPLICATE KEY UPDATE id = id;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
