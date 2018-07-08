package client.YinApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;

public class YinRepo {

    private static long currentTime;

    public static long getCurrentTime() {
        return currentTime;
    }

    public static void setCurrentTime() {
        currentTime = Instant.now().getEpochSecond();
    }


    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:mysql://localhost:3306/db_example?autoReconnect=true&useSSL=false";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "springuser", "ThePassword");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void updateYinScore(String coinName, Integer yinScore) {
        String sql = "UPDATE YINTABLE SET TOTALSCORE = ?, TIMESTAMP = ? WHERE COIN = ?";
        setCurrentTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, yinScore);
            pstmt.setInt(2, (int) currentTime);
            pstmt.setString(3, coinName);

            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateYinScore(String coinName, String column, Integer yinScore) {
        String sql = "UPDATE YINTABLE SET " + column + " = ?, TIMESTAMP = ? WHERE COIN = ?";
        setCurrentTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setInt(1, yinScore);
            pstmt.setInt(2, (int) currentTime);
            pstmt.setString(3, coinName);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
