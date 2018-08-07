package com.example.jokes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyJokesTest {


    @Test
    public void getJoke() {
        assertNotNull(new MyJokes().getJokes());
        assertEquals("What do you call a fish without eyes? Fsh.", new MyJokes().getJokes().get(0));
    }
}