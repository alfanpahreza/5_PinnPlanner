package com.example.pinnplanner_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.tasklistsql.REPLY";

    private EditText title;
    private EditText description;
    private EditText time;
    private EditText date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        title = findViewById(R.id.taskTitleBox);
        description = findViewById(R.id.taskDescBox);
        time = findViewById(R.id.taskTimeBox);
        date = findViewById(R.id.taskDateBox);

        final Button add = findViewById(R.id.addButton);
        add.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if(TextUtils.isEmpty(title.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            }else{
                Task newTask = new Task(title.getText().toString(),description.getText().toString(),
                        date.getText().toString(),time.getText().toString(),"notStarted");
                replyIntent.putExtra(EXTRA_REPLY,newTask);
                setResult(RESULT_OK,replyIntent);
            }
            finish();
        });
    }
}