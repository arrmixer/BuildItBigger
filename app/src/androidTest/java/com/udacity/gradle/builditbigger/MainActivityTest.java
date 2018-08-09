package com.udacity.gradle.builditbigger;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();

        List<String> jokes = new ArrayList<>();
        jokes.add("What do you call a fish without eyes? Fsh.");
        jokes.add("Why did the scarecrow win an award? Because he was outstanding in his field.");
        jokes.add("What lights up a soccer stadium? A soccer match.");
        jokes.add("Why shouldn’t you write with a broken pencil? Because it’s pointless. ");
        jokes.add("What’s the difference between the bird flu and the swine flu? One requires tweetment and the other an oinkment.");
        jokes.add("When do computers overheat? When they need to vent.");
    }

    //make sure the textView is visible and with the correct text shown
    @Test
    public void TestTextVisible() {
        ViewInteraction textView = onView(withId(R.id.instructions_text_view));
        textView.check(matches(isDisplayed()));
        textView.check(matches(allOf(withText("Press the button for a delicious joke!"))));
    }

    //make sure the button is visible and with the correct text shown
    @Test
    public void ButtonIsVisible() {
        ViewInteraction button = onView(withId(R.id.instructions_button));
        button.check(matches(isDisplayed()));
        button.check(matches(allOf(withText("Tell Joke"))));
    }


    //shows textView in Android Library androidJokes
    @Test
    public void textViewIsVisible() {
        ViewInteraction button = onView(withId(R.id.instructions_button));
        button.perform(click());

        ViewInteraction textView = onView(withId(R.id.txt_android_jokes));
        textView.check(matches(isDisplayed()));
        //doesn't work with randomly selected jokes
//        textView.check(matches(allOf(withText("What do you call a fish without eyes? Fsh."))));
        textView.check(matches(not(withText(" "))));
    }




}
