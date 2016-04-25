package com.lab.joke.view.ui.user;

import android.os.Bundle;
import android.app.Activity;

import com.lab.joke.R;

public class HistoryActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
