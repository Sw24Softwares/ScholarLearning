package org.sw24softwares.scholarlearning;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import android.content.Intent;
import android.content.ContentValues;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Vector;
import java.util.List;
import java.util.Arrays;

import static android.R.color.black;

public class Result extends AppCompatActivity {
        RelativeLayout mLayout = null;
        LinearLayout mTextsLayout = null;
        Format mFormat = null;
        Vector<TextView> mAnswers = new Vector<TextView>();
        Loader mLesson;
        int mIndex = -1;
        
        int mMarks[] = null;
        int mTotal = 0;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_result);
                mLayout = (RelativeLayout) findViewById (R.id.result_relative_layout);
                mTextsLayout = (LinearLayout) findViewById (R.id.result_texts_layout);
                
                mLesson = (Loader)SharedData.mLoadedLessons.get(getIntent().getExtras().getString("lesson"));
                
                mIndex = getIntent().getExtras().getInt("index");
                final int given = getIntent().getExtras().getInt("given");

                mTotal = getIntent().getExtras().getInt("total");
                mMarks = getIntent().getExtras().getIntArray("marks");
                mMarks = Arrays.copyOf(mMarks, mMarks.length +1);

                List<String> answers = Arrays.asList(getIntent().getExtras().getStringArray("answers"));
                
                // Set edittext
                mFormat = mLesson.getData(mIndex).getFormat();
                for(int i = 0; i < mFormat.getCategories().size(); i++)
                        createAnswer(i,correctAnswer(i,answers.get(i)), i == given);

                ((Button)findViewById(R.id.result_stop)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Result.this.finish();

                                        saveMark();
                                }
                        });
                ((Button)findViewById(R.id.result_next)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Result.this.finish();
                                        Intent intent = new Intent(Result.this, Test.class);

                                        intent.putExtra("total", mTotal + 1);
                                        intent.putExtra("marks", mMarks);
                                        intent.putExtra("lesson", getIntent().getExtras().getString("lesson"));

                                        startActivity(intent);
                                }
                        });
        }
        private void createAnswer(int index, Boolean right, Boolean given) {
                if(mFormat == null || mLayout == null)
                        return;
                
                TextView answer  = new TextView(this);
                answer.setText(mLesson.getData(mIndex).getInformations().get(index).get(0));
                answer.setId(index);
                if(given)
                        answer.setTextColor(getResources().getColor(black));
                else if(right) {
                        answer.setTextColor(ContextCompat.getColor(this, R.color.good));
                        mMarks[mMarks.length-1]++;
                }
                else
                        answer.setTextColor(ContextCompat.getColor(this, R.color.bad));
                mTextsLayout.addView(answer);
                mAnswers.addElement(answer);
        }
        private Boolean correctAnswer(int index, String answer) {
                if(mLesson.getData(mIndex).getInformations().get(index).indexOf(answer) == -1)
                        return false;
                return true;
        }
        private void saveMark() {
                DatabaseHelper databaseHelper = new DatabaseHelper(this, "marks");
                String marks = new String();
		int totalPercent = 0;

		for(int i = 0; i < mMarks.length; i++) {
                        marks += String.valueOf(mMarks[i]) + ' ';
                        totalPercent += mMarks[i];
		}

		totalPercent = Math.round(totalPercent * 100 / (mMarks.length * 5));
		int onTwenty = Math.round(totalPercent * 2 / 10);

		ContentValues contentValues = new ContentValues();
		contentValues.put(DatabaseHelper.COLUMN_1, new SimpleDateFormat("dd/MM/yyyy '" + getString(R.string.at) + "' HH:mm : '" + String.valueOf(totalPercent) + "% - (" + onTwenty + "/20)'").format(new Date()));
		contentValues.put(DatabaseHelper.COLUMN_2, marks);

                databaseHelper.addData(contentValues);
        }
}
