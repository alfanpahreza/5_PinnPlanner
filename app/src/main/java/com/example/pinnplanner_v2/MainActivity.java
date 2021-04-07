package com.example.pinnplanner_v2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;
    static TaskListAdapter adapter;
    TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        adapter = new TaskListAdapter(new TaskListAdapter.taskDiff());

        mTaskViewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(TaskViewModel.class);

        mTaskViewModel.getAllTasks().observe(this, tasks -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(tasks);
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> newTask());
    }
    private void newTask(){
        Intent intent = new Intent(this, NewTaskActivity.class);
        startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Task newTask = new Task(data.getParcelableExtra(NewTaskActivity.EXTRA_REPLY));
            mTaskViewModel.insert(newTask);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}