package nl.hu.cisq1.lingo.trainer.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProgressTest {

    @Test
    @DisplayName("Empty Progress is same as empty Progress")
    void emptyProgress() {
        Progress progressA = new Progress();
        Progress progressB = new Progress(null, null, null, null);
        assertEquals(progressA.toString(), progressB.toString());
    }

    @Test
    @DisplayName("id getter is equal to empty id getter")
    void idGetter() {
        Progress progressA = new Progress();
        Progress progressB = new Progress();
        assertEquals(progressA.getId(), progressB.getId());
    }

    @Test
    @DisplayName("rounds getter is equal to empty rounds getter")
    void hintGetter() {
        Progress progressA = new Progress();
        Progress progressB = new Progress();
        assertEquals(progressA.getRounds(), progressB.getRounds());
    }

    @Test
    @DisplayName("score getter is equal to score hint getter")
    void scoreGetter() {
        Progress progressA = new Progress();
        Progress progressB = new Progress();
        assertEquals(progressA.getScore(), progressB.getScore());
    }

    @Test
    @DisplayName("status getter is equal to status hint getter")
    void statusGetter() {
        Progress progressA = new Progress();
        Progress progressB = new Progress();
        assertEquals(progressA.getStatus(), progressB.getStatus());
    }
}