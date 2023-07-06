package cl.sideralti.tutorialEmailSender.resource;

import cl.sideralti.tutorialEmailSender.domain.HttpResponse;
import cl.sideralti.tutorialEmailSender.domain.User;
import cl.sideralti.tutorialEmailSender.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {

    private final UserService userServices;
    @PostMapping
    public ResponseEntity<HttpResponse> createdUser(@RequestBody User user) {
        User newUser = userServices.saveUsers(user);
        return ResponseEntity.created(URI.create("")).body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("user", newUser))
                        .message("User created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }
    @GetMapping
    public ResponseEntity<HttpResponse> confirmIUserAccount(@RequestParam("token") String token) {
        Boolean isSuccess = userServices.verifyToken(token);
        return ResponseEntity.ok().body(
                HttpResponse.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .data(Map.of("Success", isSuccess))
                        .message("Acccount verified")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
