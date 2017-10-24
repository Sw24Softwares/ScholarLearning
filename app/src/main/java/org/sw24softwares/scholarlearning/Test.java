package org.sw24softwares.scholarlearning;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.content.Intent;

import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Button;

import java.util.Random;
import java.util.Vector;

import static android.R.color.black;

public class Test extends AppCompatActivity {
        RelativeLayout mLayout = null;
        LinearLayout mTextsLayout = null;
        Format mFormat = null;
        Vector<EditText> mQuestions = new Vector<EditText>();
        Loader mLesson = null;
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test);

                mLayout = (RelativeLayout) findViewById (R.id.test_relativelayout);
                mTextsLayout = (LinearLayout) findViewById (R.id.test_texts_layout);
                mLesson = (Loader)SharedData.mLoadedLessons.get(getIntent().getExtras().getString("lesson"));

                // Choose verb
                Random rand = new Random();
                final int index = rand.nextInt(mLesson.getNumDatas());

                // Set edittext
                mFormat = mLesson.getData(index).getFormat();
                for(int i = 0; i < mFormat.getCategories().size(); i++)
                        createQuestion(i);

                // Set the given form
                Vector<Integer> availableForm = new Vector<Integer>();
                for(int i = 0; i < mLesson.getData(index).getFormat().getAskingCategories().size(); i++) {
                        if(mLesson.getData(index).getFormat().getAskingCategories().get(i))
                                availableForm.addElement(i);
                }
                final int given = availableForm.get(rand.nextInt(availableForm.size()));
                final int givenQuestion = rand.nextInt(mLesson.getData(index).getInformations().get(given).size());
                mQuestions.get(given).setText(mLesson.getData(index).getInformations().get(given).get(givenQuestion));
                mQuestions.get(given).setEnabled(false);
                mQuestions.get(given).setTextColor(getResources().getColor(black));

                // Set verify button
                Button button = (Button)findViewById(R.id.test_verify);
                button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Test.this.finish();
                                        Intent intent = new Intent(Test.this, Result.class);
                                        intent.putExtra("index", index);
                                        intent.putExtra("given", given);
                                        
                                        intent.putExtra("total", getIntent().getExtras().getInt("total"));
                                        intent.putExtra("marks", getIntent().getExtras().getIntArray("marks"));

                                        intent.putExtra("lesson", getIntent().getExtras().getString("lesson"));

                                        Vector<String> questions = new Vector<String>();
                                        for(int i = 0; i < mQuestions.size(); i++)
                                                questions.addElement(mQuestions.get(i).getText().toString());
                                        intent.putExtra("answers", questions.toArray(new String[0]));
                                        
                                        startActivity(intent);
                                }
                        });
        }
        
        private void createQuestion(int index) {
                if(mFormat == null || mLayout == null)
                        return;
                
                EditText question  = new EditText(this);
                question.setHint(mFormat.getCategories().get(index));
                question.setId(index);
                mTextsLayout.addView(question);
                mQuestions.addElement(question);
        }
}
