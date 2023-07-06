package cl.sideralti.tutorialEmailSender.services;

import cl.sideralti.tutorialEmailSender.domain.User;
import org.springframework.stereotype.Service;


public interface UserService {

    User saveUsers(User user);
    Boolean verifyToken(String token);

}
