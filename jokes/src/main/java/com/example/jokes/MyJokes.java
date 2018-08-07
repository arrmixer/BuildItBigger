package com.example.jokes;

import java.util.ArrayList;
import java.util.List;

public class MyJokes {

    private List<String> jokes = new ArrayList<>();

    public MyJokes() {
        jokes.add("What do you call a fish without eyes? Fsh.");
        jokes.add("Why did the scarecrow win an award? Because he was outstanding in his field.");
        jokes.add("What lights up a soccer stadium? A soccer match.");
        jokes.add("Why shouldn’t you write with a broken pencil? Because it’s pointless. ");
        jokes.add("What’s the difference between the bird flu and the swine flu? One requires tweetment and the other an oinkment.");
        jokes.add("When do computers overheat? When they need to vent.");
    }

    public List<String> getJokes(){

        return jokes;
    }
    //What do you call a fish without eyes? Fsh.
    //Give me some of that Chicken!!!

}
