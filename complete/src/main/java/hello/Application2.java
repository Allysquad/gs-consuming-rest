package hello;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@SpringBootApplication
public class Application2 {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Application2.class);

		getRequest();
//		log.info(getRequest());
//			JsonNode actualObj = mapper.readTree(jsonString);
	}

	public static void getRequest() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject("https://api.coinmarketcap.com/v2/ticker/1/", String.class);
		JsonNode jsonNode = mapper.readTree(response);
		log.info(jsonNode.toString());
		Iterator<Map.Entry<String, JsonNode>> nodes = jsonNode.fields();
		while (nodes.hasNext()) {
			Map.Entry<String, JsonNode> entry = (Map.Entry<String, JsonNode>) nodes.next();

			log.info("key --> " + entry.getKey() + " value-->" + entry.getValue());
		}
	}

//	public static void main(String args[]) {
//		SpringApplication.run(Application.class);
//	}



//	@Bean
//	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
//		return args -> {
//            Wrapper wrapper = restTemplate.getForObject("https://api.coinmarketcap.com/v2/ticker/1/", Wrapper.class);
////            log.info(wrapper.toString());


}

