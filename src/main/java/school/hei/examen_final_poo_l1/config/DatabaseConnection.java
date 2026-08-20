package school.hei.examen_final_poo_l1.config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/association_db";
    private static final String USER = "postgres";
    private static Connection connection;
    private DatabaseConnection() {
    }
    public static Connection getInstance() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String password = System.getenv("DB_PASSWORD");
            if (password == null) {
                throw new IllegalArgumentException("Database password environment variable is missing");
            }
            connection = DriverManager.getConnection(URL, USER, password);
        }
        return connection;
    }
}