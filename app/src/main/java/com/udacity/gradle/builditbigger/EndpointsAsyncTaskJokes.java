package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.example.jokes.MyJokes;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EndpointsAsyncTaskJokes extends AsyncTask<Void, Void, List<String>> {

    public static final String TAG = EndpointsAsyncTaskJokes.class.getSimpleName();

    private static MyApi myApiService = null;
    private MyJokes jokes = new MyJokes();

    public interface AsyncResponse{
        void processFinish(List<String> output);
    }

    public AsyncResponse delegate = null;

    public EndpointsAsyncTaskJokes(AsyncResponse delegate) {
        this.delegate = delegate;
    }

    @Override
    protected List<String> doInBackground(Void... Void) {
        List<String> jokesList = new ArrayList<>();
        if (myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            jokesList = myApiService.gotJokes().execute().getData();
            return jokesList;
        } catch (IOException ie) {

            Log.d(TAG, ie.getMessage() );
            //add error message to jokesList to show to user
            jokesList.add(ie.getMessage());
            return jokesList;
        }
    }

    @Override
    protected void onPostExecute(List<String> s) {
        delegate.processFinish(s);

    }
}
