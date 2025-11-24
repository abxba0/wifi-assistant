package com.example.wifissistor2j;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

/**
 * Activity displaying the Wi-Fi glossary with plain language explanations.
 */
public class GlossaryActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glossary);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.glossary_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<GlossaryItem> glossaryItems = GlossaryItem.getDefaultGlossary();
        GlossaryAdapter adapter = new GlossaryAdapter(glossaryItems);
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        setupNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Don't highlight any nav item since glossary is accessed from tools
        bottomNavigationView.setSelectedItemId(R.id.nav_tools);
    }

    private void setupNavigation() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            Intent intent = null;
            if (itemId == R.id.nav_home) {
                intent = new Intent(this, HomeActivity.class);
            } else if (itemId == R.id.nav_tools) {
                intent = new Intent(this, ToolsActivity.class);
            } else if (itemId == R.id.nav_map) {
                intent = new Intent(this, MapActivity.class);
            } else if (itemId == R.id.nav_settings) {
                intent = new Intent(this, SettingsActivity.class);
            }
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
            return true;
        });
    }

    /**
     * RecyclerView adapter for displaying glossary items.
     */
    private static class GlossaryAdapter extends RecyclerView.Adapter<GlossaryAdapter.ViewHolder> {

        private final List<GlossaryItem> items;

        GlossaryAdapter(List<GlossaryItem> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_glossary, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            GlossaryItem item = items.get(position);
            holder.termTextView.setText(item.getTerm());
            holder.explanationTextView.setText(item.getExplanation());
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            TextView termTextView;
            TextView explanationTextView;

            ViewHolder(View itemView) {
                super(itemView);
                termTextView = itemView.findViewById(R.id.glossary_term);
                explanationTextView = itemView.findViewById(R.id.glossary_explanation);
            }
        }
    }
}
