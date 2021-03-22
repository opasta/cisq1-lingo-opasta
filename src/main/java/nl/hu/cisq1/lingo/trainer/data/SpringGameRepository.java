package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.Game;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringGameRepository extends JpaRepository<Game, Long> {
    Game findbyId(Long id);

    List<Game> findByStatus(GameStatus status);
}
