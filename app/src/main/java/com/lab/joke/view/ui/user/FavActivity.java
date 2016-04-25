package com.lab.joke.view.ui.user;

import android.os.Bundle;
import android.app.Activity;

import com.lab.joke.R;

public class FavActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
