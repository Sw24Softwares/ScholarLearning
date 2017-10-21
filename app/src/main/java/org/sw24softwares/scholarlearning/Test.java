package org.sw24softwares.scholarlearning;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.RelativeLayout;
import android.widget.EditText;
import android.widget.Button;

import java.util.Random;
import java.util.Vector;

import static android.R.color.black;

public class Test extends AppCompatActivity {
        RelativeLayout mLayout = null;
        Format mFormat = null;
        Vector<EditText> mQuestions = new Vector<EditText>();
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test);

                mLayout = (RelativeLayout) findViewById (R.id.test_linearlayout);

                // Choose verb
                Random rand = new Random();
                int index = rand.nextInt(Loader.getSingleton().getNumDatas());

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

                // Set the first edittext above the second
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                                                                     android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,
                                                                                     android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(android.widget.RelativeLayout.ABOVE, mQuestions.get(1).getId());
                mQuestions.get(0).setLayoutParams(params);

                // Set verify button
                params = new RelativeLayout.LayoutParams(
                                                         android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,
                                                         android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(android.widget.RelativeLayout.BELOW, mQuestions.get(mQuestions.size()-1).getId());
                ((Button)findViewById(R.id.test_verify)).setLayoutParams(params);
        }
        
        private void createQuestion(int index) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                                                                                     android.widget.RelativeLayout.LayoutParams.MATCH_PARENT,
                                                                                     android.widget.RelativeLayout.LayoutParams.WRAP_CONTENT);
                
                if(mFormat == null || mLayout == null)
                        return;

                if(index > 0)
                        params.addRule(android.widget.RelativeLayout.BELOW, mQuestions.get(index-1).getId());
                
                EditText question  = new EditText(this);
                question.setHint(mFormat.getCategories().get(index));
                question.setLayoutParams(params);
                question.setId(index);
                mLayout.addView(question);
                mQuestions.addElement(question);
        }
}
