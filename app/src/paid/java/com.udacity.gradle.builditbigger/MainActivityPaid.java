package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;


public class MainActivityPaid extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment() {

        return new MainFragmentPaid();
    }

    @Override
    protected int getLayoutResId() {
            return R.layout.activity_masterdetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());


    }


}
