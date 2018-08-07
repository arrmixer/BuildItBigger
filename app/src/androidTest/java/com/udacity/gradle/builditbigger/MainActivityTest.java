package com.udacity.gradle.builditbigger;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void TestTextVisible(){
        ViewInteraction textView  = onView(withId(R.id.instructions_text_view));
        textView.check(matches(isDisplayed()));
        textView.check(matches(allOf(withText("Press the button for a delicious joke!"))));
    }

    @Test
    public void ButtonIsVisible(){
        ViewInteraction button = onView(withId(R.id.instructions_button));
        button.check(matches(isDisplayed()));
        button.check(matches(allOf(withText("Tell Joke"))));
    }


    //shows textView in Android Library androidJokes
    @Test
    public void textViewIsVisible(){
        ViewInteraction button = onView(withId(R.id.instructions_button));
        button.perform(click());

        ViewInteraction textView = onView(withId(R.id.txt_android_jokes));
        textView.check(matches(isDisplayed()));
        textView.check(matches(allOf(withText("Give me some of that Chicken!!!"))));
    }

}
