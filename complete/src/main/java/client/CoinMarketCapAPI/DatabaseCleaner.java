package client.CoinMarketCapAPI;

import client.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;



public class DatabaseCleaner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private long currentTime;
    private long expiredTime;

    private long getCurrentTime() {
        return currentTime;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public void setCurrentTime() {
        this.currentTime = Instant.now().getEpochSecond();
    }

    public void setExpiredTime() {
        this.expiredTime = getCurrentTime() - 3600;
    }

    private Connection connect() {
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

    public void cleanDatabase() {
        String sql = "DELETE FROM WRAPPER_MAPPER_STORAGE WHERE A14_TIMESTAMP < ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, (int) expiredTime);
            // execute the delete statement
            pstmt.executeUpdate();
            log.info("Cleaned WrapperMapperStorage Table");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
