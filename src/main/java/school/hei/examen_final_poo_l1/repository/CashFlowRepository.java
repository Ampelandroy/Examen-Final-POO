package school.hei.examen_final_poo_l1.repository;

import school.hei.examen_final_poo_l1.config.DatabaseConnection;
import school.hei.examen_final_poo_l1.model.CashFlow;
import school.hei.examen_final_poo_l1.model.Donation;
import school.hei.examen_final_poo_l1.model.Expense;
import school.hei.examen_final_poo_l1.model.ExpenseFrequency;
import school.hei.examen_final_poo_l1.model.User;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
public class CashFlowRepository {
    public List<CashFlow> findByType(String type) throws SQLException {
        List<CashFlow> cashFlows = new ArrayList<>();
        String sql = "SELECT c.id, c.created_at, c.amount, c.flow_type, c.comment, c.reason, c.frequency, "
                + "u.id AS user_id, u.ref, u.first_name, u.last_name, u.email, u.phone "
                + "FROM cash_flow c "
                + "JOIN app_user u ON c.user_id = u.id";
        if (type != null && !type.isBlank()) {
            sql += " WHERE UPPER(c.flow_type) = UPPER(?)";
        }
        Connection connection = DatabaseConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            if (type != null && !type.isBlank()) {
                statement.setString(1, type);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cashFlows.add(mapResultSetToCashFlow(resultSet));
                }
            }
        }
        return cashFlows;
    }
    public List<CashFlow> findByUserId(String userId) throws SQLException {
        List<CashFlow> cashFlows = new ArrayList<>();
        String sql = "SELECT c.id, c.created_at, c.amount, c.flow_type, c.comment, c.reason, c.frequency, "
                + "u.id AS user_id, u.ref, u.first_name, u.last_name, u.email, u.phone "
                + "FROM cash_flow c "
                + "JOIN app_user u ON c.user_id = u.id "
                + "WHERE c.user_id = ?";

        Connection connection = DatabaseConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cashFlows.add(mapResultSetToCashFlow(resultSet));
                }
            }
        }
        return cashFlows;
    }
    public Expense saveExpense(Expense expense) throws SQLException {
        String sql = "INSERT INTO cash_flow (id, created_at, amount, user_id, flow_type, reason, frequency) "
                + "VALUES (?, ?, ?, ?, 'EXPENSE', ?, ?)";
        Connection connection = DatabaseConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, expense.getId());
            statement.setTimestamp(2, Timestamp.from(expense.getCreatedAt()));
            statement.setBigDecimal(3, expense.getAmount());
            statement.setString(4, expense.getUser().getId());
            statement.setString(5, expense.getReason());
            statement.setString(6, expense.getFrequency().name());
            statement.executeUpdate();
        }
        return expense;
    }
    public BigDecimal calculateBalance() throws SQLException {
        String sql = "SELECT "
                + "COALESCE(SUM(CASE WHEN UPPER(flow_type) = 'DONATION' THEN amount ELSE 0 END), 0) - "
                + "COALESCE(SUM(CASE WHEN UPPER(flow_type) = 'EXPENSE' THEN amount ELSE 0 END), 0) AS total_balance "
                + "FROM cash_flow";
        Connection connection = DatabaseConnection.getInstance();
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                BigDecimal balance = resultSet.getBigDecimal("total_balance");
                return balance != null ? balance : BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }
    private CashFlow mapResultSetToCashFlow(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getString("user_id"),
                resultSet.getString("ref"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("email"),
                resultSet.getString("phone")
        );

        String flowType = resultSet.getString("flow_type");
        if ("EXPENSE".equalsIgnoreCase(flowType)) {
            String frequencyStr = resultSet.getString("frequency");
            ExpenseFrequency frequency = frequencyStr != null ? ExpenseFrequency.valueOf(frequencyStr) : ExpenseFrequency.NONE;
            return new Expense(
                    resultSet.getString("id"),
                    resultSet.getTimestamp("created_at").toInstant(),
                    resultSet.getBigDecimal("amount"),
                    user,
                    resultSet.getString("reason"),
                    frequency
            );
        } else {
            return new Donation(
                    resultSet.getString("id"),
                    resultSet.getTimestamp("created_at").toInstant(),
                    resultSet.getBigDecimal("amount"),
                    user,
                    resultSet.getString("comment")
            );
        }
    }
}