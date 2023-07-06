package cl.sideralti.tutorialEmailSender.services.impl;

import cl.sideralti.tutorialEmailSender.domain.Confirmation;
import cl.sideralti.tutorialEmailSender.domain.User;
import cl.sideralti.tutorialEmailSender.repository.ConfirmationRepository;
import cl.sideralti.tutorialEmailSender.repository.UserRepository;
import cl.sideralti.tutorialEmailSender.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationRepository confirmationRepository;

    @Override
    public User saveUsers(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEnabled(false);
        userRepository.save(user);

        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        /*    TODO Send Email to user with token   */
        //emailService.sendHtmlEmailWithEmbeddedFiles(user.getName(), user.getEmail(), confirmation.getToken());

        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByEmailIgnoraCase(confirmation.getUser().getEmail());
        user.setEnabled(true);
        userRepository.save(user);
        //confirmationRepository.delete(confirmation);
        return Boolean.TRUE;
    }
}
