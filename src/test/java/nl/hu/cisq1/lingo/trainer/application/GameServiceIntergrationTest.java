package nl.hu.cisq1.lingo.trainer.application;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import nl.hu.cisq1.lingo.trainer.domain.GameStatus;
import nl.hu.cisq1.lingo.trainer.domain.Progress;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@Import(CiTestConfiguration.class)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
class GameServiceIntergrationTest {

    @Autowired
    private GameService gameService;

    @Test
    @DisplayName("starting a new game, so status is playing")
    void
    startGame() {
        Progress progress = gameService.startNewGame();
        assertEquals(progress.getStatus(), GameStatus.PLAYING);
        System.out.println(progress);
    }
}
