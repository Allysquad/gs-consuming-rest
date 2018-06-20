package client;

import java.io.IOException;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;

public class GoogleTableClient {

    private static long currentTime;
    private static long expiredTime;
    private String coin;
    private String value;
    private String timestamp;

    public long getCurrentTime() {
        return currentTime;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setCurrentTime() {
        this.currentTime = Instant.now().getEpochSecond();
    }

    public void setExpiredTime() {
        this.expiredTime = getCurrentTime() - 172800;
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


    public static void updateTable(String coin, String value) {
        String sql = "UPDATE GOOGLETABLE SET COIN = ?, VALUE = ? , TIMESTAMP = ? WHERE TIMESTAMP < ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, coin);
            pstmt.setString(2, value);
            pstmt.setLong(3, currentTime);
            pstmt.setLong(4, expiredTime);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Instead of returning a value, I'll just automatically update the table with new values.
    //Hard coding the timestamps to save on shitty java.
    public static void checkTable() throws SQLException, IOException {
        ArrayList<String> stringArray = new ArrayList<>();
        Statement stmt = null;
        String SQL1 = "SELECT COIN FROM GOOGLETABLE WHERE TIMESTAMP < UNIX_TIMESTAMP(NOW())-172800";
        try (Connection conn = connect()) {
            conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL1);
            while (rs.next()) {
                //Does the table update after the values have been checked.
                updateTable(rs.getString("COIN"), GoogleAPIResponse.main(rs.getString("COIN")));
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static String getValue(String coin) throws SQLException {
        Statement stmt = null;
        String aString = null;
        String SQL1 = "SELECT VALUE FROM GOOGLETABLE WHERE COIN = \"" + coin + "\"";
        try (Connection conn = connect()) {
            conn.createStatement();

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

