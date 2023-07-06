package cl.sideralti.tutorialEmailSender.repository;

import cl.sideralti.tutorialEmailSender.domain.Confirmation;
import cl.sideralti.tutorialEmailSender.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationRepository extends JpaRepository<Confirmation, Long> {

    Confirmation findByToken(String token);

}

