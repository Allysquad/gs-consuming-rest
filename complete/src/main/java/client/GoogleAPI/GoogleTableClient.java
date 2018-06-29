package client.GoogleAPI;

import client.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class GoogleTableClient {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static long currentTime;
    private static long expiredTime;

    private static long getCurrentTime() {
        return currentTime;
    }

    public static long getExpiredTime() {
        return expiredTime;
    }

    public static void setCurrentTime() {
        currentTime = Instant.now().getEpochSecond();
    }

    public static void setExpiredTime() {
        expiredTime = getCurrentTime() - 86400;
    }

    private static Connection connect() {
        String url = "jdbc:mysql://localhost:3306/db_example?autoReconnect=true&useSSL=false";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, "springuser", "ThePassword");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    private static void updateTable(String coin, String value) {
        String sql = "UPDATE GOOGLETABLE SET VALUE = ? , TIMESTAMP = ? WHERE TIMESTAMP < ? AND COIN = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setCurrentTime();
            setExpiredTime();
            // set the corresponding param
            pstmt.setInt(1, Integer.parseInt(value));
            pstmt.setLong(2, currentTime);
            pstmt.setLong(3, expiredTime);
            pstmt.setString(4, coin);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Instead of returning a value, I'll just automatically update the table with new values.
    //Hard coding the timestamps to save on shitty java.
    public static void checkTable(String coin) throws SQLException, IOException {
        Statement stmt = null;
        PreparedStatement stmt2 = null;
        String SQL1 = "SELECT COUNT(*) FROM GOOGLETABLE WHERE TIMESTAMP < UNIX_TIMESTAMP(NOW()-86400)";
        String SQL2 = "SELECT COIN FROM GOOGLETABLE WHERE TIMESTAMP < UNIX_TIMESTAMP(NOW()-86400) AND COIN = ?";
        try (Connection conn = connect()) {
            stmt = conn.createStatement();
            stmt2 = conn.prepareStatement(SQL2);
            stmt2.setString(1, coin);
            ResultSet rs = stmt.executeQuery(SQL1);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = stmt2.executeQuery();
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

    public static String getValue(String coin) throws SQLException {
        Statement stmt = null;
        String aString = "";
        String SQL1 = "SELECT VALUE FROM GOOGLETABLE WHERE COIN = \'" + coin + "\'";
        try (Connection conn = connect()) {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL1);
            while (rs.next()) {
                //Does the table update after the values have been checked.
                aString = aString.concat(rs.getString("VALUE"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return aString;
    }
}

