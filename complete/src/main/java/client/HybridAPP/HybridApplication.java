package client.HybridAPP;

import java.sql.*;

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

    private String pullPrice(String coinName) {
        String returnString = null;
        String SQL1 = "SELECT COUNT(*) FROM WRAPPER_MAPPER_STORAGE WHERE A13_NAME = ?";
        String SQL2 = "SELECT A15_PRICE FROM WRAPPER_MAPPER_STORAGE WHERE A13_NAME = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL1);
             PreparedStatement pstmt2 = conn.prepareStatement(SQL2)) {
            pstmt.setString(1, coinName);
            pstmt2.setString(1, coinName);
            ResultSet rs = pstmt.executeQuery(SQL1);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery(SQL2);
                    //Does the table update after the values have been checked.
                    while (rs2.next()) {
                        returnString = rs2.getString("A15_PRICE");
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
            ResultSet rs = pstmt.executeQuery(SQL1);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery(SQL2);
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
            ResultSet rs = pstmt.executeQuery(SQL1);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery(SQL2);
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
            ResultSet rs = pstmt.executeQuery(SQL1);
            while (rs.next()) {
                if (rs.getInt(1) > 0) {
                    ResultSet rs2 = pstmt2.executeQuery(SQL2);
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

    private void doWork(String coinName) {
        //pull all the data for a coin
//        String price = pullPrice(coinName);
//        String googleValue = pullGoogleValue(coinName);
//        String facebookValue = pullFacebookValue(coinName);
//        String twitterValue = pullTwitterValue(coinName);

        String examplePrice = "6150.09";
        String exampleGV = "35000000";
        String exampleFV = "";
        String exampleTV = "";


        Float returnGV = Float.parseFloat(examplePrice) / (Float.parseFloat(exampleGV) / 100000);

        System.out.print("this is the google worked number : " + String.format("%.6f", returnGV) + " | ");

    }

    public void main(String coinName) {
        doWork(coinName);
    }


}
