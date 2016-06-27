package com.cocotask.core.user.rest;

import com.cocotask.core.user.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class UserRestControllerTests {
    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addUser() throws JsonProcessingException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", "snowMan");

        HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), requestHeaders);

        ResponseEntity<User> responseEntity = testRestTemplate.postForEntity("http://localhost:8081/rest/users", httpEntity, User.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));

        assertThat(responseEntity.getBody().getUid(), greaterThan(0L));
        assertThat(responseEntity.getBody().getUserName(), is("snowMan"));
    }

    @Test
    public void readUsers() throws Exception {
        ResponseEntity<User[]> entity = testRestTemplate.getForEntity("http://localhost:8081/rest/users", User[].class);
        List <User> users = Arrays.asList(entity.getBody());

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(users.get(0).getUserName(), is("antman"));
    }

    /*@Test
    public void readUsersNoData() throws Exception {
        ResponseEntity<User[]> entity = testRestTemplate.getForEntity("http://localhost:8081/rest/users", User[].class);

        assertThat(entity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }*/

    @Test
    public void readUser() {
        ResponseEntity<User> entity = testRestTemplate.getForEntity("http://localhost:8081/rest/users/1", User.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody().getUserName(), is("antman"));
    }

    @Test
    public void readUserByUserEmail() {
        ResponseEntity<User> entity = testRestTemplate.getForEntity("http://localhost:8081/rest/users/email/antman@cocotask.com", User.class);

        assertThat(entity.getStatusCode(), is(HttpStatus.OK));
        assertThat(entity.getBody().getUserName(), is("antman"));
    }

    @Test
    public void readUserNoData() {
        ResponseEntity<User> entity = testRestTemplate.getForEntity("http://localhost:8081/rest/users/99", User.class);

        System.out.println(entity.getStatusCode().getReasonPhrase());
        assertThat(entity.getStatusCode(), is(HttpStatus.NO_CONTENT));
    }

    @Test
    public void modifyUser() throws JsonProcessingException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("userName", "Upgraded antman");

        HttpEntity<String> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(requestBody), requestHeaders);

        // RestTemplate이 Patch를 지원하지 않는다.
        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:8081/rest/users/1", HttpMethod.PATCH, httpEntity, User.class);
        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(responseEntity.getBody().getUid(), is(1L));
        assertThat(responseEntity.getBody().getUserName(), is("Upgraded antman"));
    }

    @Test
    public void deleteUser() {
        ResponseEntity<User> responseEntity = testRestTemplate.exchange("http://localhost:8081/rest/users/1", HttpMethod.DELETE, null, User.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
    }
}
