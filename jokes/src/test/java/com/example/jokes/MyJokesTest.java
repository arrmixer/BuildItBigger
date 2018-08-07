package com.example.jokes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyJokesTest {




    @Test
    public void getJoke() {
        assertNotNull(new MyJokes().getJoke());
        assertEquals("Give me some of that Chicken!!!", new MyJokes().getJoke());
    }
}