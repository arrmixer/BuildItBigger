package com.example.androidjokes;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.androidjokes.databinding.ActivityMainAndroidJokesBinding;


public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_JOKE = "com.udacity.gradle.builditbigger.extra.joke";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainAndroidJokesBinding mainAndroidJokesBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main_android_jokes);

//        MyJokes jokes = new MyJokes();
//
//      mainAndroidJokesBinding.txtAndroidJokes.setText(jokes.getJoke());

        if(getIntent().hasExtra(EXTRA_JOKE)){
            String joke = getIntent().getStringExtra(EXTRA_JOKE);
            mainAndroidJokesBinding.txtAndroidJokes.setText(joke);
        }


    }

}
