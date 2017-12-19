package org.sw24softwares.scholarlearning;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.Button;

import android.content.Intent;

public class LessonManager extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_lesson_manager);

                ((Button)findViewById(R.id.lesson_manager_create)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Intent intent = new Intent(LessonManager.this, LessonCreate.class);
                                        startActivity(intent);
                                }
                        });
        }
}
