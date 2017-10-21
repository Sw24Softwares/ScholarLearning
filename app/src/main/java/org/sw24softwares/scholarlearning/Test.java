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
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test);

                mLayout = (RelativeLayout) findViewById (R.id.test_relativelayout);
                mTextsLayout = (LinearLayout) findViewById (R.id.test_texts_layout); 

                // Choose verb
                Random rand = new Random();
                final int index = rand.nextInt(Loader.getSingleton().getNumDatas());

                // Set edittext
                mFormat = Loader.getSingleton().getData(index).getFormat();
                for(int i = 0; i < mFormat.getCategories().size(); i++)
                        createQuestion(i);

                // Set the given form
                int given = rand.nextInt(Loader.getSingleton().getData(index).getInformations().size());
                int givenQuestion = rand.nextInt(Loader.getSingleton().getData(index).getInformations().get(given).size());
                mQuestions.get(given).setText(Loader.getSingleton().getData(index).getInformations().get(given).get(givenQuestion));
                mQuestions.get(given).setEnabled(false);
                mQuestions.get(given).setTextColor(getResources().getColor(black));

                // Set verify button
                Button button = (Button)findViewById(R.id.test_verify);
                button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                        Intent intent = new Intent(Test.this, Result.class);
                                        intent.putExtra("index", index);
                                        
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
