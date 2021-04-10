package nl.hu.cisq1.lingo.trainer.presentation;

import nl.hu.cisq1.lingo.CiTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Import(CiTestConfiguration.class)
@AutoConfigureMockMvc
class GameControllerIntergrationTest {

//    @Autowired
//    private MockMvc mockMvc;

//    @Test
//    @DisplayName("Start game")
//    void startGame() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders
//                .post("/trainer/game");
//
//
//        mockMvc.perform(request)
//                .andExpect(status().is(400));
//    }
//
//    @Test
//    @DisplayName("New Round")
//    void newRound() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders
//                .post("/trainer/game/1/newRound");
//
//        mockMvc.perform(request)
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ActionNotAllowedException));
//    }

//    @Test
//    @DisplayName("guess")
//    void guess() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders
//                .post("/trainer/game/1/guess")
//                .content("aapje");


//        mockMvc.perform(request)
//                .andExpect(status().is(400));
//    }
//    @Test
//    @DisplayName("get game 1")
//    void getCurrent() throws Exception {
//        RequestBuilder request = MockMvcRequestBuilders
//                .post("/trainer/game/1");


//        mockMvc.perform(request)
//                .andExpect(status().is(400));
//    }






}