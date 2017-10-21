package org.sw24softwares.scholarlearning;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.EditText;

import java.util.Random;
import java.util.Vector;

import static android.R.color.black;

public class Test extends AppCompatActivity {
        LinearLayout mLayout = null;
        Format mFormat = null;
        Vector<EditText> mQuestions = new Vector<EditText>();
        
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_test);

                mLayout = (LinearLayout) findViewById (R.id.test_linearlayout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                                                                 android.widget.LinearLayout.LayoutParams.MATCH_PARENT,
                                                                                 android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);                
                
                Random rand = new Random();
                int index = rand.nextInt(Loader.getSingleton().getNumDatas());

                mFormat = Loader.getSingleton().getData(index).getFormat();
                for(int i = 0; i < mFormat.getCategories().size(); i++)
                        createQuestion(i);

                int given = rand.nextInt(Loader.getSingleton().getData(index).getInformations().size());
                int givenQuestion = rand.nextInt(Loader.getSingleton().getData(index).getInformations().get(given).size());
                mQuestions.get(given).setText(Loader.getSingleton().getData(index).getInformations().get(given).get(givenQuestion));
                mQuestions.get(given).setEnabled(false);
                mQuestions.get(given).setTextColor(getResources().getColor(black));
        }
        
        private void createQuestion(int index) {
                if(mFormat == null || mLayout == null)
                        return;
                EditText question  = new EditText(this);
                question.setHint(mFormat.getCategories().get(index));
                //question.setLayoutParams(params);
                mLayout.addView(question);
                mQuestions.addElement(question);
        }
}
