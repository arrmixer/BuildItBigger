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
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.builditbigger.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getSimpleName();

    //key for joke
    public static final String EXTRA_JOKE = "com.udacity.gradle.builditbigger.extra.joke";

    //key for index of ArrayList
    public static final String EXTRA_INDEX = "com.udacity.gradle.builditbigger.extra.index";

    //placeholders
    private List<String> jokes = new ArrayList<>();
    private int index;

    private InterstitialAd mInterstitialAd;;

   public  MainFragment(){

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

        if(savedInstanceState != null){
            index = savedInstanceState.getInt(EXTRA_INDEX);
            jokes = savedInstanceState.getStringArrayList(EXTRA_JOKE);
        }else{

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
        FragmentMainBinding mainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mainBinding.setLifecycleOwner(this);

        mainBinding.instructionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MyJokes myJokes = new MyJokes();
//                Toast.makeText(getContext(), myJokes.getJoke(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), MainActivity.class);
                i.putExtra(EXTRA_JOKE, jokes.get(index));
                startActivity(i);

                if(index < jokes.size() - 1){
                    index++;
                }else{
                    index = 0;
                }

                if(!BuildConfig.IS_PAID) {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    } else {
                        Log.d(TAG, "The interstitial wasn't loaded yet.");
                    }
                }

            }
        });



        if(!BuildConfig.IS_PAID) {
            AdView mAdView = mainBinding.adView;
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);

            mInterstitialAd = new InterstitialAd(getContext());
            mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
            mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }


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
