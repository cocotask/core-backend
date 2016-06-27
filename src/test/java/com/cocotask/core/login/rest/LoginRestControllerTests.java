package com.cocotask.core.login.rest;

import com.cocotask.core.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class LoginRestControllerTests {
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void doLogin() throws JsonProcessingException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userEmail", "antman@cocotask.com");
        requestBody.put("password", "1234");

        HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), requestHeaders);

        ResponseEntity<User> responseEntity = testRestTemplate.postForEntity("http://localhost:8081/rest/login", httpEntity, User.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getUserName(), is("antman"));

        // TODO: HttpSession 테스트 하는방법은?
    }
}
