/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}

	String jsonString = "{\n" +
			"    \"data\": {\n" +
			"        \"1\": {"+
			"        \"2132\": {\n" +
			"            \"id\": 2132, \n" +
			"            \"name\": \"Power Ledger\", \n" +
			"            \"symbol\": \"POWR\", \n" +
			"            \"website_slug\": \"power-ledger\", \n" +
			"            \"rank\": 100, \n" +
			"            \"circulating_supply\": 371325802.0, \n" +
			"            \"total_supply\": 1000000000.0, \n" +
			"            \"max_supply\": null, \n" +
			"            \"quotes\": {\n" +
			"                \"USD\": {\n" +
			"                    \"price\": 0.458254, \n" +
			"                    \"volume_24h\": 60260100.0, \n" +
			"                    \"market_cap\": 170161534.0, \n" +
			"                    \"percent_change_1h\": -0.47, \n" +
			"                    \"percent_change_24h\": -8.08, \n" +
			"                    \"percent_change_7d\": -7.09\n" +
			"                }\n" +
			"            }, \n" +
			"            \"last_updated\": 1526504657\n" +
			"        }\n" +
			"    }, \n" +
			"    \"metadata\": {\n" +
			"        \"timestamp\": 1526504504, \n" +
			"        \"num_cryptocurrencies\": 1592, \n" +
			"        \"error\": null\n" +
			"    }\n" +
			"}";
}
