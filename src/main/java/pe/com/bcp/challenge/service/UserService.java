package pe.com.bcp.challenge.service;

import org.springframework.stereotype.Component;
import pe.com.bcp.challenge.domain.Role;
import pe.com.bcp.challenge.domain.User;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Component
public class UserService {

    private final String userUsername = "user";
    private final User user = new User(userUsername, "cBrlgyL2GI2GINuLUUwgojITuIufFycpLG4490dhGtY=", true, Arrays.asList(Role.ROLE_USER));

    private final String adminUsername = "admin";
    private final User admin = new User(adminUsername, "dQNjUIMorJb8Ubj2+wVGYp6eAeYkdekqAcnYp+aRq5w=", true, Arrays.asList(Role.ROLE_ADMIN));

    public Mono<User> findByUsername(String username) {
        if (username.equals(userUsername)) {
            return Mono.just(user);
        } else if (username.equals(adminUsername)) {
            return Mono.just(admin);
        } else {
            return Mono.empty();
        }
    }
}
