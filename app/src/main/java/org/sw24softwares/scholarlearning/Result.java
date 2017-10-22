package org.sw24softwares.scholarlearning;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;

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
        int mIndex = -1;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_result);
                mLayout = (RelativeLayout) findViewById (R.id.result_relative_layout);
                mTextsLayout = (LinearLayout) findViewById (R.id.result_texts_layout);
                
                mIndex = getIntent().getExtras().getInt("index");
                final int given = getIntent().getExtras().getInt("given");

                List<String> answers = Arrays.asList(getIntent().getExtras().getStringArray("answers"));
                
                // Set edittext
                mFormat = Loader.getSingleton().getData(mIndex).getFormat();
                for(int i = 0; i < mFormat.getCategories().size(); i++)
                        createAnswer(i,correctAnswer(i,answers.get(i)), i == given);

                ((Button)findViewById(R.id.result_stop)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Result.this.finish();
                                }
                        });
                ((Button)findViewById(R.id.result_next)).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Result.this.finish();
                                        Intent intent = new Intent(Result.this, Test.class);
                                        startActivity(intent);
                                }
                        });
        }
        private void createAnswer(int index, Boolean right, Boolean given) {
                if(mFormat == null || mLayout == null)
                        return;
                
                TextView answer  = new TextView(this);
                answer.setText(Loader.getSingleton().getData(mIndex).getInformations().get(index).get(0));
                answer.setId(index);
                if(given)
                        answer.setTextColor(getResources().getColor(black));
                else if(right)
                        answer.setTextColor(ContextCompat.getColor(this, R.color.good));
                else
                        answer.setTextColor(ContextCompat.getColor(this, R.color.bad));
                mTextsLayout.addView(answer);
                mAnswers.addElement(answer);
        }
        private Boolean correctAnswer(int index, String answer) {
                if(Loader.getSingleton().getData(mIndex).getInformations().get(index).indexOf(answer) == -1)
                        return false;
                return true;
        }
}
