package com.hyperaware.android.devtricks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hyperaware.android.devtricks.R;

public class DevLaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_dev);

        // Activities

        findViewById(R.id.button_launch_app_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(DevLaunchActivity.this, LaunchActivity.class));
            }
        });

        findViewById(R.id.button_launch_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                startActivity(new Intent(DevLaunchActivity.this, SearchActivity.class));
            }
        });

        // Searches

        findViewById(R.id.search_really_long).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent search = new Intent(DevLaunchActivity.this, SearchResultsActivity.class);
                search.putExtra(SearchResultsActivity.EXTRA_QUERY, "a really long search term");
                startActivity(search);
            }
        });

        findViewById(R.id.search_something_difficult).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent search = new Intent(DevLaunchActivity.this, SearchResultsActivity.class);
                search.putExtra(SearchResultsActivity.EXTRA_QUERY, "sòmêthīng dîffïcúlt");
                startActivity(search);
            }
        });

        findViewById(R.id.search_text).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                EditText et = (EditText) findViewById(R.id.et_search);
                Intent search = new Intent(DevLaunchActivity.this, SearchResultsActivity.class);
                search.putExtra(SearchResultsActivity.EXTRA_QUERY, et.getText().toString());
                startActivity(search);
            }
        });

        findViewById(R.id.search_text_limit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                EditText et = (EditText) findViewById(R.id.et_search);
                Intent search = new Intent(DevLaunchActivity.this, SearchResultsActivity.class);
                search.putExtra(SearchResultsActivity.EXTRA_QUERY, et.getText().toString());
                search.putExtra(SearchResultsActivity.EXTRA_LIMIT, 5);
                startActivity(search);
            }
        });
    }

}
