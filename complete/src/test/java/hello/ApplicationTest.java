/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http=//www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static hello.WrapperTest.CreateWrapperObject;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
	
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void test() {
		Application.main(new String[]{
				"--spring.main.web-environment=false",
				"--spring.autoconfigure.exclude=blahblahblah",
				// Override any other environment properties according to your needs
		});
	}

	@Test
	public void contextLoads() {
		assertThat(restTemplate).isNotNull();
	}


	@Test
	public void testJsonString() {
		Wrapper wrapper = CreateWrapperObject();
		assertEquals(jsonString, wrapper.toString());
	}

	private String jsonString =
			"{\n" +
					"    \"data\"= {\n" +
					"        \"id\"= 1, \n" +
					"        \"name\"= \"Bitcoin\", \n" +
					"        \"symbol\"= \"BTC\", \n" +
					"        \"website_slug\"= \"bitcoin\", \n" +
					"        \"rank\"= 1, \n" +
					"        \"circulating_supply\"= 17046825.0, \n" +
					"        \"total_supply\"= 17046825.0, \n" +
					"        \"max_supply\"= 21000000.0, \n" +
					"        \"quotes\"= {\n" +
					"            \"USD\"= {\n" +
					"                \"price\"= 8405.39, \n" +
					"                \"volume_24h\"= 5180290000.0, \n" +
					"                \"market_cap\"= 143285212387.0, \n" +
					"                \"percent_change_1h\"= -0.33, \n" +
					"                \"percent_change_24h\"= -1.44, \n" +
					"                \"percent_change_7d\"= -4.0\n" +
					"            }\n" +
					"        }, \n" +
					"        \"last_updated\"= 1526941172\n" +
			"    }, \n" +
					"    \"metadata\"= {\n" +
					"        \"timestamp\"= 1526941080, \n" +
					"        \"error\"= null\n" +
			"    }\n" +
			"}";
}
