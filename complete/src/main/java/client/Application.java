package client;

import client.CoinMarketCapAPI.DatabaseCleaner;
import client.CoinMarketCapAPI.Wrapper;
import client.CoinMarketCapAPI.WrapperMapperStorage;
import client.CoinMarketCapAPI.WrapperMapperStorageRepository;
import client.GoogleAPI.GoogleTableClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class Application {

    private Boolean myBool = true;

    @Autowired
    private WrapperMapperStorageRepository wrapperMapperStorageRepository;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) {

        return args -> reRunner(restTemplate);
    }

    private void reRunner(RestTemplate restTemplate) throws IOException, SQLException {

        while (myBool) {
            try {
                cleaner();
                AtomicInteger counter = new AtomicInteger(1);
                Set<Integer> coinSet = new HashSet<>();
                coinSet.add(1);
                coinSet.add(1027);
                coinSet.add(52);
                coinSet.add(1831);
                coinSet.add(1765);
                coinSet.add(2);
                coinSet.add(512);
                coinSet.add(2010);
                coinSet.add(1720);
                coinSet.add(1958);

                while (counter.get() < 3000) {
                    if (coinSet.contains(counter.get())) {
                        WrapperMapperStorage wMStorage = map(restTemplate, counter);
                        wrapperMapperStorageRepository.save(wMStorage);
                    }
                    counter.updateAndGet(v -> v + 1);
                }
                Thread.sleep(1000 * 60 * 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void cleaner() {
        DatabaseCleaner dbCleaner = new DatabaseCleaner();
        dbCleaner.setCurrentTime();
        dbCleaner.setExpiredTime();
        dbCleaner.cleanDatabase();
    }

    private Float stringComputeStringConverter(String price, String supply) {
        Float priceFloat = Float.parseFloat(price);
        Float supplyFloat = Float.parseFloat(supply);
        return priceFloat / supplyFloat * 1000000;
    }

    private WrapperMapperStorage map(RestTemplate restTemplate, AtomicInteger counter) throws IOException, SQLException {
        Wrapper wrapper = restTemplate.getForObject("https://api.coinmarketcap.com/v2/ticker/" + counter.get() + "/", Wrapper.class);
        log.info(wrapper.toString());
        WrapperMapperStorage wMStorage = new WrapperMapperStorage();
        wMStorage.setA11_id(wrapper.getData().getId());
        wMStorage.setA13_name(wrapper.getData().getName());
        wMStorage.setA12_symbol(wrapper.getData().getSymbol());
        wMStorage.setWebsite_slug(wrapper.getData().getWebsite_slug());
        wMStorage.setRank(wrapper.getData().getRank());
        wMStorage.setA16_circ_supply(wrapper.getData().getCirculating_supply());
        wMStorage.setTotal_supply(wrapper.getData().getTotal_supply());
        wMStorage.setMax_supply(wrapper.getData().getMax_supply());
        wMStorage.setLast_updated(wrapper.getData().getLast_updated());
        wMStorage.setA14_timestamp(wrapper.getMetadata().getTimestamp());
        wMStorage.setA15_price(wrapper.getData().getQuotes().getUSD().getPrice());
        wMStorage.setA17_volume_24h(wrapper.getData().getQuotes().getUSD().getVolume_24h());
        wMStorage.setMarket_cap(wrapper.getData().getQuotes().getUSD().getMarket_cap());
        wMStorage.setP_Change_1h(wrapper.getData().getQuotes().getUSD().getPercent_change_1h());
        wMStorage.setP_Change_24h(wrapper.getData().getQuotes().getUSD().getPercent_change_24h());
        wMStorage.setP_Change_7d(wrapper.getData().getQuotes().getUSD().getPercent_change_7d());
        wMStorage.setA21_pricePCoin(stringComputeStringConverter(wrapper.getData().getQuotes().getUSD().getPrice(), wrapper.getData().getCirculating_supply()));
        GoogleTableClient.checkTable();
        wMStorage.setA22_googResult(GoogleTableClient.getValue(wrapper.getData().getName()));
        return wMStorage;
    }
}


/*
 */