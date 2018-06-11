package client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class Application {

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
        return args -> {
            AtomicInteger counter = new AtomicInteger(1);
            Set<Integer> foo = new HashSet<Integer>();
            foo.add(1);
            foo.add(1027);
            foo.add(52);
            foo.add(1831);
            foo.add(1765);
            foo.add(2);
            foo.add(512);
            foo.add(2010);
            foo.add(1720);
            foo.add(1958);

            while (counter.get() < 3000) {
                if (foo.contains(counter.get())) {
                    Wrapper wrapper = restTemplate.getForObject("https://api.coinmarketcap.com/v2/ticker/" + counter.get() + "/", Wrapper.class);
                    log.info(wrapper.toString());
                    WrapperMapperStorage wMStorage = new WrapperMapperStorage();
                    wMStorage.setId(wrapper.getData().getId());
                    wMStorage.setName(wrapper.getData().getName());
                    wMStorage.setSymbol(wrapper.getData().getSymbol());
                    wMStorage.setWebsite_slug(wrapper.getData().getWebsite_slug());
                    wMStorage.setRank(wrapper.getData().getRank());
                    wMStorage.setCirculating_supply(wrapper.getData().getCirculating_supply());
                    wMStorage.setTotal_supply(wrapper.getData().getTotal_supply());
                    wMStorage.setMax_supply(wrapper.getData().getMax_supply());
                    wMStorage.setLast_updated(wrapper.getData().getLast_updated());
                    wMStorage.setTimestamp(wrapper.getMetadata().getTimestamp());
                    wMStorage.setPrice(wrapper.getData().getQuotes().getUSD().getPrice());
                    wMStorage.setVolume_24h(wrapper.getData().getQuotes().getUSD().getVolume_24h());
                    wMStorage.setMarket_cap(wrapper.getData().getQuotes().getUSD().getMarket_cap());
                    wMStorage.setPercent_change_1h(wrapper.getData().getQuotes().getUSD().getPercent_change_1h());
                    wMStorage.setPercent_change_24h(wrapper.getData().getQuotes().getUSD().getPercent_change_24h());
                    wMStorage.setPercent_change_7d(wrapper.getData().getQuotes().getUSD().getPercent_change_7d());
                    wrapperMapperStorageRepository.save(wMStorage);
                }
                counter.updateAndGet(v -> v + 1);
            }
        };
    }
}

/*
 */