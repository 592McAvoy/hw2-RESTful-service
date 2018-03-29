package com.spring.service.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordLadderTest {

    @Test
    public void setWord() {
        WordLadder wl = new WordLadder();
        assertTrue(wl.setWord("my","bag").equals("mybag"));
    }

    @Test
    public void getLadder() {
        WordLadder wl = new WordLadder();
        assertTrue(wl.getLadder().equals("No ladder found from  back to \n"));
    }

    @Test
    public void run() {
        WordLadder wl = new WordLadder();
        assertTrue(wl.run().equals("no ladder\n"));
    }
}