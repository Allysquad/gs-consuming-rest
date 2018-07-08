package client.YangApp;

import java.sql.*;

import static client.YangApp.YangRepo.getSUMofTable;

public class YangApplication {

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


    private static void getAllGoogleValues() {
        String SQL = "SELECT VALUE ,COIN FROM ( " +
                "SELECT * FROM GOOGLETABLE ORDER BY VALUE DESC)AS Y";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("COIN") + "'s Rank for google = " + rs.getString("VALUE"));
                YangRepo.updateYangValue(rs.getString("COIN"), "GVALUE", rs.getString("VALUE"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAllFacebookValues() {
        String SQL = "SELECT FBMEAN, COINNAME FROM (" +
                "SELECT * FROM FBTABLE ORDER BY FBMEAN DESC)AS Y";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("COINNAME") + "'s Rank for facebook = " + rs.getString("FBMEAN"));
                YangRepo.updateYangValue(rs.getString("COINNAME"), "FVALUE", rs.getString("FBMEAN"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAllTwitterValues() {
        String SQL = "SELECT FOLLOWERS, COINNAME FROM (" +
                "SELECT * FROM TWITTERTABLE ORDER BY FOLLOWERS DESC)AS Y";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("COINNAME") + "'s Rank for twitter = " + rs.getString("FOLLOWERS"));
                YangRepo.updateYangValue(rs.getString("COINNAME"), "TVALUE", rs.getString("FOLLOWERS"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAllMarketPriceValues() {
        String SQL = "SELECT  A23_MARKET_CAP, A13_NAME FROM \n" +
                " (SELECT * FROM \n" +
                " (SELECT  A13_NAME, CONVERT(A23_MARKET_CAP, UNSIGNED)AS A23_MARKET_CAP FROM WRAPPER_MAPPER_STORAGE ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t \n" +
                "  GROUP BY A13_NAME)as z \n" +
                " ORDER BY A23_MARKET_CAP DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for market cap = " + rs.getString("A23_MARKET_CAP"));
                YangRepo.updateYangValue(rs.getString("A13_NAME"), "MCVALUE", rs.getString("A23_MARKET_CAP"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAll24VolumeValues() {
        String SQL = "SELECT A17_VOLUME_24H, A13_NAME\n" +
                "FROM\n" +
                "(SELECT * FROM\n" +
                "(SELECT  A13_NAME, CONVERT(A17_VOLUME_24H, UNSIGNED)AS A17_VOLUME_24H FROM WRAPPER_MAPPER_STORAGE ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                "GROUP BY A13_NAME)as z\n" +
                "ORDER BY A17_VOLUME_24H DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 24H volume rank = " + rs.getString("A17_VOLUME_24H"));
                YangRepo.updateYangValue(rs.getString("A13_NAME"), "VOLVALUE", rs.getString("A17_VOLUME_24H"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAll1HVolatilityValues() {
        String SQL = "SELECT a18_pchng1, A13_NAME FROM\n" +
                " (SELECT * FROM\n" +
                " (SELECT  A13_NAME, CONVERT(a18_pchng1, DECIMAL(15,2))AS a18_pchng1 FROM WRAPPER_MAPPER_STORAGE\n" +
                " ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                " GROUP BY A13_NAME)as z\n" +
                " ORDER BY a18_pchng1 DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 1H % price change = " + rs.getString("a18_pchng1"));
                YangRepo.updateYangValue(rs.getString("A13_NAME"), "1HVALUE", rs.getFloat("a18_pchng1"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAll24HVolatilityValues() {
        String SQL = "SELECT a19_pchng24, A13_NAME FROM\n" +
                " (SELECT * FROM\n" +
                " (SELECT  A13_NAME, CONVERT(a19_pchng24, DECIMAL(15,2))AS a19_pchng24 FROM WRAPPER_MAPPER_STORAGE\n" +
                " ORDER BY A13_NAME, a14_timestamp  DESC)AS Y,(SELECT @row_number:=0) AS t\n" +
                " GROUP BY A13_NAME)as z\n" +
                " ORDER BY a19_pchng24 DESC";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 24H % price change = " + rs.getString("a19_pchng24"));
                YangRepo.updateYangValue(rs.getString("A13_NAME"), "24HVALUE", rs.getFloat("a19_pchng24"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void getAll7DVolatilityValues() {
        String SQL = "SELECT a20_pchng7d, A13_NAME\n" +
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
                System.out.println(rs.getString("A13_NAME") + "'s Rank for 7D % price change = " + rs.getString("a20_pchng7d"));
                YangRepo.updateYangValue(rs.getString("A13_NAME"), "7DVALUE", rs.getFloat("a20_pchng7d"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//    private static void setTScore() {
//        String SQL = "SELECT COIN, (GSCORE + FSCORE + TSCORE + " +
//                "MCSCORE + VOLSCORE + 1HSCORE + 24HSCORE + 7DSCORE) AS TTOTALSCORE FROM YINTABLE";
//
//        try (Connection conn = connect();
//             Statement stmt = conn.createStatement()) {
//            ResultSet rs = stmt.executeQuery(SQL);
//            while (rs.next()) {
//                System.out.println(rs.getString("COIN") + "'s TOTAL SCORE = " + rs.getString("TTOTALSCORE"));
//                YangRepo.updateYangScore(rs.getString("COIN"), Integer.parseInt(rs.getString("TTOTALSCORE")));
//            }
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    private static void sumPuller() {
        getSUMofTable("GVALUE");
        getSUMofTable("FVALUE");
        getSUMofTable("TVALUE");
        getSUMofTable("MCVALUE");
        getSUMofTable("VOLVALUE");
        getSUMofTable("1HVALUE", 2);
        getSUMofTable("24HVALUE", 2);
        getSUMofTable("7DVALUE", 2);
    }


    public static void doWork() {
        getAllGoogleValues();
        getAllFacebookValues();
        getAllTwitterValues();
        getAllMarketPriceValues();
        getAll24VolumeValues();
        getAll1HVolatilityValues();
        getAll24HVolatilityValues();
        getAll7DVolatilityValues();
        sumPuller();


        //WEIGHT THE VALUES FOR THE 1 HOUR 24 HOUR 7 DAY ATTRIBUTE


        //GET VALUE/SUM FOR EVERY ATTRIBUTE -- 3 new table

        //GET THE RANK FOR EVERY ATTRIBUTE -- 4

    }

}
