package com.example.wifissistor2j;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

/**
 * Activity displaying actionable Wi-Fi improvement checklist.
 */
public class ImprovementChecklistActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improvement_checklist);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        RecyclerView recyclerView = findViewById(R.id.checklist_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> checklistItems = WifiTips.getImprovementChecklist();
        ChecklistAdapter adapter = new ChecklistAdapter(checklistItems);
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        setupNavigation();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
     * RecyclerView adapter for displaying checklist items.
     */
    private static class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.ViewHolder> {

        private final List<String> items;
        private final boolean[] checkedStates;

        ChecklistAdapter(List<String> items) {
            this.items = items;
            this.checkedStates = new boolean[items.size()];
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_checklist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String item = items.get(position);
            holder.textView.setText(item);
            holder.checkBox.setChecked(checkedStates[position]);

            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                checkedStates[holder.getAdapterPosition()] = isChecked;
            });

            holder.itemView.setOnClickListener(v -> {
                holder.checkBox.setChecked(!holder.checkBox.isChecked());
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            CheckBox checkBox;
            TextView textView;

            ViewHolder(View itemView) {
                super(itemView);
                checkBox = itemView.findViewById(R.id.checklist_checkbox);
                textView = itemView.findViewById(R.id.checklist_text);
            }
        }
    }
}
