package school.hei.examen_final_poo_l1.repository;

import school.hei.examen_final_poo_l1.config.DatabaseConnection;
import school.hei.examen_final_poo_l1.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    public Optional<User> findById(String id) throws SQLException {
        String sql = "SELECT id, ref, first_name, last_name, email, phone FROM app_user WHERE id = ?";
        Connection connection = DatabaseConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User(
                            resultSet.getString("id"),
                            resultSet.getString("ref"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getString("phone")
                    );
                    return Optional.of(user);
                }
            }
        }
        return Optional.empty();
    }
}