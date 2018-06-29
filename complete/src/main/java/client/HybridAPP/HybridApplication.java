package client.HybridAPP;

import java.sql.*;
import java.time.Instant;

public class HybridApplication {

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

    private String pullMarketCap(String coinName) {
        String returnString = null;
        String SQL1 = "SELECT COUNT(*) FROM WRAPPER_MAPPER_STORAGE WHERE A13_NAME = ?";
        String SQL2 = "SELECT A23_MARKET_CAP FROM WRAPPER_MAPPER_STORAGE WHERE A13_NAME = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL1);
             PreparedStatement pstmt2 = conn.prepareStatement(SQL2)) {
            pstmt.setString(1, coinName);
            pstmt2.setString(1, coinName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery();
                    //Does the table update after the values have been checked.
                    while (rs2.next()) {
                        returnString = rs2.getString("A23_MARKET_CAP");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnString;
    }

    private String pullGoogleValue(String coinName) {
        String returnString = null;
        String SQL1 = "SELECT COUNT(*) FROM GOOGLETABLE WHERE COIN = ?";
        String SQL2 = "SELECT VALUE FROM GOOGLETABLE WHERE COIN = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL1);
             PreparedStatement pstmt2 = conn.prepareStatement(SQL2)) {
            pstmt.setString(1, coinName);
            pstmt2.setString(1, coinName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery();
                    //Does the table update after the values have been checked.
                    while (rs2.next()) {
                        returnString = rs2.getString("VALUE");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnString;
    }

    private String pullFacebookValue(String coinName) {
        Integer returnInteger;
        String returnString = null;
        String SQL1 = "SELECT COUNT(*) FROM FBTABLE WHERE COINNAME = ?";
        String SQL2 = "SELECT FBMEAN FROM FBTABLE WHERE COINNAME = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL1);
             PreparedStatement pstmt2 = conn.prepareStatement(SQL2)) {
            pstmt.setString(1, coinName);
            pstmt2.setString(1, coinName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery();
                    //Does the table update after the values have been checked.
                    while (rs2.next()) {
                        returnInteger = rs2.getInt("FBMEAN");
                        returnString = returnInteger.toString();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnString;
    }

    private String pullTwitterValue(String coinName) {
        Integer returnInteger;
        String returnString = null;
        String SQL1 = "SELECT COUNT(*) FROM TWITTERTABLE WHERE COINNAME = ?";
        String SQL2 = "SELECT FOLLOWERS FROM TWITTERTABLE WHERE COINNAME = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL1);
             PreparedStatement pstmt2 = conn.prepareStatement(SQL2)) {
            pstmt.setString(1, coinName);
            pstmt2.setString(1, coinName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery();
                    //Does the table update after the values have been checked.
                    while (rs2.next()) {
                        returnInteger = rs2.getInt("FOLLOWERS");
                        returnString = returnInteger.toString();
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return returnString;
    }

    private static void updateTable(String coinName, Float google, Float facebook, Float twitter) {
        String sql = "UPDATE HYBRIDTABLE SET GOOGP = ?, FACEP = ?, TWITP = ?, TIMESTAMP = ? WHERE TIMESTAMP < ? AND COIN = ?";
        setCurrentTime();
        setExpiredTime();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            setCurrentTime();
            setExpiredTime();
            // set the corresponding param
            pstmt.setFloat(1, google);
            pstmt.setFloat(2, facebook);
            pstmt.setFloat(3, twitter);
            pstmt.setInt(4, (int) currentTime);
            pstmt.setInt(5, (int) expiredTime);
            pstmt.setString(6, coinName);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void doWork(String coinName) {
        System.out.println("now working on " + coinName);
        //pull all the data for a coin
        String price = pullMarketCap(coinName);
        System.out.println("price : " + price);
        String googleValue = pullGoogleValue(coinName);
        String facebookValue = pullFacebookValue(coinName);
        String twitterValue = pullTwitterValue(coinName);

        Float returnGV = Float.parseFloat(price) / (Float.parseFloat(googleValue));
        Float returnFV = Float.parseFloat(price) / (Float.parseFloat(facebookValue));
        Float returnTV = Float.parseFloat(price) / (Float.parseFloat(twitterValue));

//        Testing
//        System.out.println("coin = " + coinName);
//        System.out.println("market Cap = " + price);
//        System.out.println("GV = " + googleValue);
//        System.out.println("FV = " + facebookValue);
//        System.out.println("TV = " + twitterValue);
//        System.out.println("this is the google worked number : " + String.format("%.6f", returnGV) + " | ");
//        System.out.println("this is the facebook worked number : " + String.format("%.6f", returnFV) + " | ");
//        System.out.println("this is the twitter worked number : " + String.format("%.6f", returnTV) + " | ");
//        System.out.println("----------------------------------------------------");

        updateTable(coinName, returnGV, returnFV, returnTV);
    }

    public void main(String coinName) {
        doWork(coinName);
    }

}
