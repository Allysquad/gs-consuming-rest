package client.YangApp;

import client.YinApp.YinRepo;

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

    private static void getGValuesDividedBySum() {
        String SQL = "select \"Bitcoin\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"Bitcoin\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Bitcoin Cash\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"Bitcoin Cash\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Cardano\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"Cardano\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"EOS\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"EOS\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Ethereum\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"Ethereum\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Litecoin\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"Litecoin\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL union all\n" +
                "select \"IOTA\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"IOTA\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Ripple\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"Ripple\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Stellar\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"Stellar\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL union all\n" +
                "select \"TRON\" as COIN,((select GVALUE from YANGVALUETABLE WHERE COIN = \"TRON\")/(select GVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                YangRepo.updateYangTable(rs.getString("COIN"), "GSCORE", String.valueOf(Math.round(rs.getFloat("valuedivsum") * 100)));
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

    private static void getFValuesDividedBySum() {
        String SQL = "select \"Bitcoin\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"Bitcoin\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Bitcoin Cash\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"Bitcoin Cash\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Cardano\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"Cardano\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"EOS\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"EOS\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Ethereum\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"Ethereum\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Litecoin\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"Litecoin\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL union all\n" +
                "select \"IOTA\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"IOTA\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Ripple\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"Ripple\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Stellar\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"Stellar\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL union all\n" +
                "select \"TRON\" as COIN,((select FVALUE from YANGVALUETABLE WHERE COIN = \"TRON\")/(select FVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                YangRepo.updateYangTable(rs.getString("COIN"), "FSCORE", String.valueOf(Math.round(rs.getFloat("valuedivsum") * 100)));
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

    private static void getTValuesDividedBySum() {
        String SQL = "select \"Bitcoin\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"Bitcoin\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Bitcoin Cash\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"Bitcoin Cash\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Cardano\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"Cardano\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"EOS\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"EOS\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Ethereum\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"Ethereum\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Litecoin\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"Litecoin\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL union all\n" +
                "select \"IOTA\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"IOTA\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Ripple\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"Ripple\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL \n" +
                "union all\n" +
                "select \"Stellar\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"Stellar\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL union all\n" +
                "select \"TRON\" as COIN,((select TVALUE from YANGVALUETABLE WHERE COIN = \"TRON\")/(select TVALUE from YANGVALUETABLE where coin = \"SUMTOTAL\")) as \"valuedivsum\" from DUAL";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                YangRepo.updateYangTable(rs.getString("COIN"), "TSCORE", String.valueOf(Math.round(rs.getFloat("valuedivsum") * 100)));
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

    private static void getAll1HVolatilityValuesPC() {
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
                YangRepo.updatePCTableValue(rs.getString("A13_NAME"), "1HOURSCORE", String.valueOf(Integer.parseInt(rs.getString("RANK")) * 4));
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

    private static void getAll24HVolatilityValuesPC() {
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
                YangRepo.updatePCTableValue(rs.getString("A13_NAME"), "24HOURSCORE", String.valueOf(Integer.parseInt(rs.getString("RANK")) * 2));
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


    private static void getAll7DVolatilityValuesPC() {
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
                YangRepo.updatePCTableValue(rs.getString("A13_NAME"), "7DSCORE", rs.getString("RANK"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void setTScore() {
        String SQL = "SELECT COIN, (1HOURSCORE + 24HOURSCORE + 7DSCORE) AS TTOTALSCORE FROM PCTABLE";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("COIN") + "'s TOTAL SCORE = " + rs.getString("TTOTALSCORE"));
                YangRepo.updatePCTableValue(rs.getString("COIN"), "TOTALSCORE", rs.getString("TTOTALSCORE"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
        getAll1HVolatilityValuesPC();
        getAll24HVolatilityValuesPC();
        getAll7DVolatilityValuesPC();
        setTScore();
        getGValuesDividedBySum();
        getFValuesDividedBySum();
        getTValuesDividedBySum();
//        getMPValuesDividedBySum();
//        getVolValuesDividedBySum();
//        getPChangeValuesDividedBySum();
//        setTScore2();




        //GET VALUE/SUM FOR EVERY ATTRIBUTE -- 3 new table (Just going to save the scores straight into the yang table)

        //GET THE RANK FOR EVERY ATTRIBUTE -- 4

    }

}
