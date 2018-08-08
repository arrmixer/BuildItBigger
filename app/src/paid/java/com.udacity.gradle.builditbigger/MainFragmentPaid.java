package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidjokes.MainActivity;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;
import com.udacity.gradle.builditbigger.databinding.FragmentMainPaidBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragmentPaid extends Fragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    //key for joke
    public static final String EXTRA_JOKE = "com.udacity.gradle.builditbigger.extra.joke";

    //key for index of ArrayList
    public static final String EXTRA_INDEX = "com.udacity.gradle.builditbigger.extra.index";

    //placeholders
    private List<String> jokes = new ArrayList<>();
    private int index;


    public MainFragmentPaid() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA_INDEX, index);
        outState.putStringArrayList(EXTRA_JOKE, (ArrayList<String>) jokes);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (savedInstanceState != null) {
            index = savedInstanceState.getInt(EXTRA_INDEX);
            jokes = savedInstanceState.getStringArrayList(EXTRA_JOKE);
        } else {

            //use callback Interface to get result for AsyncTask
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(new EndpointsAsyncTask.AsyncResponse() {
                @Override
                public void processFinish(List<String> output) {
                    jokes = output;
                }
            });

            endpointsAsyncTask.execute();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FragmentMainPaidBinding mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mainBinding.setLifecycleOwner(this);

        mainBinding.instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyJokes myJokes = new MyJokes();
//                Toast.makeText(getContext(), myJokes.getJoke(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), MainActivity.class);
                i.putExtra(EXTRA_JOKE, jokes.get(index));
                startActivity(i);

                if (index < jokes.size() - 1) {
                    index++;
                } else {
                    index = 0;
                }

            }
        });

        return mainBinding.getRoot();

    }



    /*Menu Configuration*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
