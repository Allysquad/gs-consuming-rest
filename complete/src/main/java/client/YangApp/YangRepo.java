package client.YangApp;

import java.sql.*;
import java.time.Instant;


public class YangRepo {


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

    static void getSUMofTable(String columnName) {
        String SQL = "select SUM(" + columnName + ") as BADSUM from yangvaluetable where coin != \"SUMTOTAL\"";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                updateYangSum(rs.getString("BADSUM"), columnName);
                System.out.println("sum of " + rs.getString("BADSUM") + "BADSUM : " + columnName);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //for dealing with the decimals
    static void getSUMofTable(String columnName, Integer myInt) {
        String SQL = "select ROUND(SUM(" + columnName + ")," + myInt + ") as GOODSUM from yangvaluetable where coin != \"SUMTOTAL\"";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                updateYangSum(rs.getString("GOODSUM"), columnName);
                System.out.println("sum of " + rs.getString("GOODSUM"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateYangSum(String sum, String columnName) {
        String sql = "UPDATE YANGVALUETABLE SET " + columnName + " = ?, TIMESTAMP = ? WHERE COIN = \"SUMTOTAL\"";
        setCurrentTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, sum);
            pstmt.setInt(2, (int) currentTime);
            // execute the delete statement
            pstmt.executeUpdate();
            System.out.println("updatedSUM for " + columnName);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateYangValue(String coinName, String column, String value) {

        String sql = "UPDATE YANGVALUETABLE SET " + column + " = ?, TIMESTAMP = ? WHERE COIN = ?";
        setCurrentTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, value);
            pstmt.setInt(2, (int) currentTime);
            pstmt.setString(3, coinName);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateYangValue(String coinName, String column, Float value) {

        String sql = "UPDATE YANGVALUETABLE SET " + column + " = ?, TIMESTAMP = ? WHERE COIN = ?";
        setCurrentTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setFloat(1, value);
            pstmt.setInt(2, (int) currentTime);
            pstmt.setString(3, coinName);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void updatePCTableValue(String coinName, String column, String value) {

        String sql = "UPDATE PCTABLE SET " + column + " = ? WHERE COIN = ?";
        setCurrentTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, value);
            pstmt.setString(2, coinName);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void updateYangTable(String coinName, String column, String value) {

        String sql = "UPDATE YANGTABLE SET " + column + " = ?, TIMESTAMP = ? WHERE COIN = ?";
        setCurrentTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, value);
            pstmt.setInt(2, (int) currentTime);
            pstmt.setString(3, coinName);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
