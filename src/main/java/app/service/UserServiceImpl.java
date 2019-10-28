package app.service;

import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8080/api/";

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        ResponseEntity<List<User>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>(){});
        return response.getBody();
    }

    @Override
    public List<Role> getAllRoles() {
        ResponseEntity<List<Role>> response = restTemplate.exchange(
                URL + "/roles",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Role>>() {}
        );

        return response.getBody();
    }

    @Override
    public void addUser(User user) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        Content-Type: application/x-www-form-urlencoded

//        MultiValueMap<String, String> headers = new HttpHeaders();
//        headers.add("Content-Type", "application/x-www-form-urlencoded");
//        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        restTemplate.postForEntity(URL, user, User.class);

//        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(URL, user, Object.class);
//        return responseEntity.getStatusCode() == HttpStatus.CREATED;
//        restTemplate.postForObject(URL, user, User.class);
    }

    @Override
    public void updateUser(User user) {
        restTemplate.put(URL, user, User.class);
    }

    @Override
    public void deleteUser(Long id) {
        restTemplate.delete(URL + "/" + id);
    }

    @Override
    public User getUserById(Long id) {
        return restTemplate.getForObject(URL + "/" + id, User.class );
    }

    public String getURL() {
        return URL;
    }

}
