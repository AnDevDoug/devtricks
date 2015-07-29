package com.hyperaware.android.devtricks.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hyperaware.android.devtricks.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
    }

    private void initViews() {
        setContentView(R.layout.activity_search);

        final EditText et = (EditText) findViewById(R.id.et_search);
        findViewById(R.id.button_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(SearchActivity.this, SearchResultsActivity.class);
                intent.putExtra(SearchResultsActivity.EXTRA_QUERY, et.getText().toString());
                startActivity(intent);
            }
        });
    }

}
