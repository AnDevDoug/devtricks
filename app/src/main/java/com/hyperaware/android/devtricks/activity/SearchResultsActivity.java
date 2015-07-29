package com.hyperaware.android.devtricks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hyperaware.android.devtricks.R;
import com.hyperaware.android.devtricks.logging.Logging;
import com.hyperaware.android.devtricks.model.Item;
import com.hyperaware.android.devtricks.util.BundleSerializer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class SearchResultsActivity extends AppCompatActivity {

    private static final Logger LOGGER = Logging.getLogger(SearchResultsActivity.class);

    public static final String EXTRA_QUERY = SearchResultsActivity.class.getName() + ".EXTRA_QUERY";
    public static final String EXTRA_LIMIT = SearchResultsActivity.class.getName() + ".EXTRA_LIMIT";

    private RecyclerView rv;
    private SearchResultsAdapter rvAdapter;
    private List<Item> searchResults;

    private static class State implements Serializable {
        public enum SortOrder { Ascending, Descending }
        public SortOrder sortOrder = SortOrder.Ascending;
    }

    private State state;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        restoreState(savedInstanceState);

        Intent intent = getIntent();
        String query = intent.getStringExtra(EXTRA_QUERY);
        setTitle("Results for " + query);

        loadSearchResults();
    }

    @Override
    protected void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState(outState);
    }

    private void restoreState(final Bundle bundle) {
        if (bundle != null) {
            BundleSerializer<State> serializer = new BundleSerializer<State>();
            state = serializer.deserialize(bundle);
        }
        else {
            state = new State();
        }
    }

    private void saveState(final Bundle bundle) {
        BundleSerializer<State> serializer = new BundleSerializer<State>();
        serializer.serialize(state, bundle);
    }

    private void initViews() {
        setContentView(R.layout.activity_search_results);
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));

        findViewById(R.id.button_sort_ascending).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                sortAscending();
            }
        });

        findViewById(R.id.button_sort_descending).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                sortDescending();
            }
        });
    }


    private void loadSearchResults() {
        // In a real app, load would happen in another thread
        searchResults = Arrays.asList(TEST_ITEMS.clone());

        switch (state.sortOrder) {
        case Ascending:
            sortAscending();
            break;
        case Descending:
            sortDescending();
            break;
        }
    }

    private static class AlphabeticByName implements Comparator<Item> {
        @Override
        public int compare(final Item lhs, final Item rhs) {
            return lhs.getName().compareTo(rhs.getName());
        }
    }

    private void sortAscending() {
        Collections.sort(searchResults, new AlphabeticByName());
        rvAdapter = new SearchResultsAdapter(searchResults);
        rv.setAdapter(rvAdapter);
        state.sortOrder = State.SortOrder.Ascending;
    }

    private void sortDescending() {
        Collections.sort(searchResults, new AlphabeticByName());
        Collections.reverse(searchResults);
        rvAdapter = new SearchResultsAdapter(searchResults);
        rv.setAdapter(rvAdapter);
        state.sortOrder = State.SortOrder.Descending;
    }


    private static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tv;
        public ViewHolder(TextView tv) {
            super(tv);
            this.tv = tv;
        }
    }

    private class SearchResultsAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final List<Item> items;

        public SearchResultsAdapter(List<Item> items) {
            this.items = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            TextView tv = (TextView) getLayoutInflater().inflate(R.layout.search_results_item, parent, false);
            return new ViewHolder(tv);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tv.setText(items.get(position).getName());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }


    private static Item[] TEST_ITEMS = new Item[] {
        new Item("Test Item 01"),
        new Item("Test Item 02"),
        new Item("Test Item 03"),
        new Item("Test Item 04"),
        new Item("Test Item 05"),
        new Item("Test Item 06"),
        new Item("Test Item 07"),
        new Item("Test Item 08"),
        new Item("Test Item 09"),
        new Item("Test Item 10"),
        new Item("Test Item 11"),
        new Item("Test Item 12"),
        new Item("Test Item 13"),
        new Item("Test Item 14"),
        new Item("Test Item 15"),
        new Item("Test Item 16"),
        new Item("Test Item 17"),
        new Item("Test Item 18"),
        new Item("Test Item 19"),
        new Item("Test Item 20"),
    };

}
