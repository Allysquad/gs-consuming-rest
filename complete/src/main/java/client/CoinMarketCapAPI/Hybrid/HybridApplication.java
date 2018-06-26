package client.CoinMarketCapAPI.Hybrid;

import client.GoogleAPI.GoogleAPIResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class HybridApplication {

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

    private static void updateHybrid(String coin, String value) {
        log.info("Updating");
        String sql = "UPDATE HYBRIDTABLE SET VALUE = ? , TIMESTAMP = ? WHERE TIMESTAMP < ? AND COIN = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setCurrentTime();
            setExpiredTime();
            // set the corresponding param
            pstmt.setString(1, value);
            log.info("Current Time : " + currentTime);
            pstmt.setLong(2, currentTime);
            log.info("Expired Time : " + expiredTime);
            pstmt.setLong(3, expiredTime);
            pstmt.setString(4, coin);
            // execute the delete statement
            pstmt.executeUpdate();
            log.info("Updated");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Instead of returning a value, I'll just automatically update the table with new values.
    //Hard coding the timestamps to save on shitty java.
    public static void checkTable() throws SQLException, IOException {
        ArrayList<String> stringArray = new ArrayList<>();
        Statement stmt = null;
        Statement stmt2 = null;
        String SQL1 = "SELECT COUNT(*) FROM GOOGLETABLE WHERE TIMESTAMP < UNIX_TIMESTAMP(NOW())-172800";
        String SQL2 = "SELECT COIN FROM GOOGLETABLE WHERE TIMESTAMP < UNIX_TIMESTAMP(NOW())-172800";
        try (Connection conn = connect()) {
            stmt = conn.createStatement();
            stmt2 = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL1);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = stmt2.executeQuery(SQL2);
                    //Does the table update after the values have been checked.
                    while (rs2.next()) {
                        updateTable(rs2.getString("COIN"), GoogleAPIResponse.main(rs2.getString("COIN")));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (stmt2 != null) {
                stmt2.close();
            }
        }
    }
}
