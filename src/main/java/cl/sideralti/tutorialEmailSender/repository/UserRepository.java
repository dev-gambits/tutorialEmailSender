package cl.sideralti.tutorialEmailSender.repository;

import cl.sideralti.tutorialEmailSender.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailIgnoraCase(String email);
    Boolean existsByEmail(String email);
}
