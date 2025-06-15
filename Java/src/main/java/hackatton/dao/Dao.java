package hackatton.dao;
import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    private Connection connection;

    public Dao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/unialfa?useTimezone=true&serverTimezone=UTC",
                    "root",
                    "");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public Connection getConnection() {
        return connection;
    }
}
