package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    public static QueryRunner runner = new QueryRunner();

    private SQLHelper() {

    }

    private static Connection getConn() throws SQLException {

        return DriverManager.getConnection("jdbs:mysql://localhost:3306/app", "app", "pass");
    }

    public static DataHelper.VerificationCode getVerificationCode() {
        var CodeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";

        try (var conn = getConn()) {
            var code = runner.query(conn, CodeSQL, new ScalarHandler<String>());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows

    public static void cleanDataBase() {

        var connection = getConn();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");


    }
}