package client.YinApp;

import java.sql.*;

public class YinApplication {

    private static Integer rankCounter;
    private static Integer returnGSCORE;
    private static Integer returnFSCORE;
    private static Integer returnTSCORE;
    private static Integer returnMCSCORE;
    private static Integer returnVOLSCORE;
    private static Integer return1HSCORE;
    private static Integer return24HSCORE;
    private static Integer return7DSCORE;

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

    private static void setGoogleRank(String coinName) {
        String SQL = "SELECT RANK FROM(SELECT (@row_number:=@row_number + 1) AS RANK,COIN FROM ( " +
                "SELECT * FROM GOOGLETABLE ORDER BY VALUE DESC)AS Y,(SELECT @row_number:=0) AS t)AS X " +
                "WHERE COIN = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for google = " + rs.getString("RANK"));
                returnGSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + returnGSCORE;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAllGoogleRank() {
        String SQL = "SELECT (@row_number:=@row_number + 1) AS RANK,COIN FROM ( " +
                "SELECT * FROM GOOGLETABLE ORDER BY VALUE DESC)AS Y,(SELECT @row_number:=0) AS t";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("COIN") + "'s Rank for google = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setFacebookRank(String coinName) {
        String SQL = "SELECT RANK FROM(SELECT (@row_number:=@row_number + 1) AS RANK, COIN FROM (" +
                "SELECT * FROM GOOGLETABLE ORDER BY VALUE DESC)AS Y,(SELECT @row_number:=0) AS t)AS X " +
                "WHERE COIN = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for facebook = " + rs.getString("RANK"));
                returnFSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + returnFSCORE;


            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void setAllFacebookRank() {
        String SQL = "SELECT (@row_number:=@row_number + 1) AS RANK, COINNAME FROM (" +
                "SELECT * FROM FBTABLE ORDER BY FBMEAN DESC)AS Y,(SELECT @row_number:=0) AS t";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("COINNAME") + "'s Rank for facebook = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setTwitterRank(String coinName) {
        String SQL = "SELECT RANK FROM(SELECT (@row_number:=@row_number + 1) AS RANK, COINNAME FROM (" +
                "SELECT * FROM TWITTERTABLE ORDER BY FOLLOWERS DESC)AS Y,(SELECT @row_number:=0) AS t)AS X " +
                "WHERE COINNAME = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for twitter = " + rs.getString("RANK"));
                returnTSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + returnTSCORE;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void setAllTwitterRank() {
        String SQL = "SELECT (@row_number:=@row_number + 1) AS RANK, COINNAME FROM (" +
                "SELECT * FROM TWITTERTABLE ORDER BY FOLLOWERS DESC)AS Y,(SELECT @row_number:=0) AS t";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("COINNAME") + "'s Rank for twitter = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setMarketPriceRank(String coinName) {
        String SQL = "SELECT RANK FROM(SELECT " +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME " +
                "FROM (SELECT * FROM " +
                "(SELECT  A13_NAME, CONVERT(A23_MARKET_CAP, UNSIGNED)AS A23_MARKET_CAP FROM WRAPPER_MAPPER_STORAGE ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t " +
                "GROUP BY A13_NAME)as z " +
                "ORDER BY A23_MARKET_CAP DESC)AS X " +
                "WHERE A13_NAME = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for market cap = " + rs.getString("RANK"));
                returnMCSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + returnMCSCORE;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAllMarketPriceRank() {
        String SQL = "SELECT " +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME " +
                "FROM " +
                "(SELECT * FROM " +
                "(SELECT  A13_NAME, CONVERT(A23_MARKET_CAP, UNSIGNED)AS A23_MARKET_CAP FROM WRAPPER_MAPPER_STORAGE ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t " +
                "GROUP BY A13_NAME)as z " +
                "ORDER BY A23_MARKET_CAP DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for market cap = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void set24VolumeRank(String coinName) {
        String SQL = "SELECT RANK FROM (SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(A17_VOLUME_24H, UNSIGNED)AS A17_VOLUME_24H FROM WRAPPER_MAPPER_STORAGE ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY A17_VOLUME_24H DESC) AS X " +
                "WHERE A13_NAME = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for 24H volume rank = " + rs.getString("RANK"));
                returnVOLSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + returnVOLSCORE;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAll24VolumeRank() {
        String SQL = "SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(A17_VOLUME_24H, UNSIGNED)AS A17_VOLUME_24H FROM WRAPPER_MAPPER_STORAGE ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY A17_VOLUME_24H DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 24H volume rank = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void set1HVolatility(String coinName) {
        String SQL = "SELECT RANK FROM (SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME " +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(a18_pchng1, DECIMAL(15,2))AS a18_pchng1 FROM WRAPPER_MAPPER_STORAGE\n" +
                "ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY a18_pchng1 DESC) AS X " +
                "WHERE A13_NAME = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for 1H % price change = " + rs.getString("RANK"));
                return1HSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + return1HSCORE;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAll1HVolatility() {
        String SQL = "SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME, a18_pchng1\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(a18_pchng1, DECIMAL(15,2))AS a18_pchng1 FROM WRAPPER_MAPPER_STORAGE\n" +
                "ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY a18_pchng1 DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 1H % price change = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void set24HVolatility(String coinName) {
        String SQL = "SELECT RANK FROM (SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(a19_pchng24, DECIMAL(15,2))AS a19_pchng24 FROM WRAPPER_MAPPER_STORAGE\n" +
                "ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY a19_pchng24 DESC) AS X " +
                "WHERE A13_NAME = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for 24H % price change = " + rs.getString("RANK"));
                return24HSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + return24HSCORE;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAll24HVolatility() {
        String SQL = "SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(a19_pchng24, DECIMAL(15,2))AS a19_pchng24 FROM WRAPPER_MAPPER_STORAGE\n" +
                "ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY a19_pchng24 DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 24H % price change = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void set7DVolatility(String coinName) {
        String SQL = "SELECT * FROM (SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(a20_pchng7d, DECIMAL(15,2))AS a20_pchng7d FROM WRAPPER_MAPPER_STORAGE\n" +
                "ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY a20_pchng7d DESC) AS X " +
                "WHERE A13_NAME = ?";


        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            // set the corresponding param
            pstmt.setString(1, coinName);
            // execute the delete statement
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Rank for 7D % price change = " + rs.getString("RANK"));
                return7DSCORE = Integer.parseInt(rs.getString("RANK"));
                rankCounter = rankCounter + return7DSCORE;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setAll7DVolatility() {
        String SQL = "SELECT \n" +
                "(@row_number:=@row_number + 1) AS RANK, A13_NAME\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(a20_pchng7d, DECIMAL(15,2))AS a20_pchng7d FROM WRAPPER_MAPPER_STORAGE\n" +
                "ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY a20_pchng7d DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 7D % price change = " + rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void doWork(String coinName) {

        rankCounter = 0;
        setGoogleRank(coinName);
        YinRepo.storeYinScore(coinName, "GSCORE", returnGSCORE);
//                        YinApplication.setAllGoogleRank();
        setFacebookRank(coinName);
        YinRepo.storeYinScore(coinName, "FSCORE", returnFSCORE);
//                        YinApplication.setAllFacebookRank();
        setTwitterRank(coinName);
        YinRepo.storeYinScore(coinName, "TSCORE", returnTSCORE);
//                        YinApplication.setAllTwitterRank();
        setMarketPriceRank(coinName);
        YinRepo.storeYinScore(coinName, "MCSCORE", returnMCSCORE);
//                        YinApplication.setAllMarketPriceRank();
        set24VolumeRank(coinName);
        YinRepo.storeYinScore(coinName, "VOLSCORE", returnVOLSCORE);
//                        YinApplication.setAll24VolumeRank();
        set1HVolatility(coinName);
        YinRepo.storeYinScore(coinName, "1HSCORE", return1HSCORE);
//                        YinApplication.setAll1HVolatility();
        set24HVolatility(coinName);
        YinRepo.storeYinScore(coinName, "24HSCORE", return24HSCORE);
//                        YinApplication.setAll24HVolatility();
        set7DVolatility(coinName);
        YinRepo.storeYinScore(coinName, "7DSCORE", return7DSCORE);
//                        YinApplication.setAll7DVolatility();
        YinRepo.storeYinScore(coinName, rankCounter);
        System.out.println("The total score for " + coinName + " is " + rankCounter);
    }
}