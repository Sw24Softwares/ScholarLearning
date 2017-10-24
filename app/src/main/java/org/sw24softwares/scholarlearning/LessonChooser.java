package org.sw24softwares.scholarlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.view.View;
import android.widget.AdapterView;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;

public class LessonChooser extends ListActivity {

        @Override
        protected void onCreate(Bundle savedInstance) {
                super.onCreate(savedInstance);
                
                // set the list adapter
                Object[] entities = SharedData.mLoadedLessons.keySet().toArray();
                ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, entities);
                setListAdapter(adapter);

                getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id)  {
                                        try {
                                                Intent intent = new Intent(LessonChooser.this, Class.forName(getIntent().getExtras().getString("toLaunchClass")));
                                                intent.putExtra("lesson", ((TextView)view).getText().toString());
                                                intent.putExtras(getIntent().getExtras());
                                                startActivity(intent);
                                        } catch (ClassNotFoundException x) {
                                                System.err.format("ClassNotFoundException: %s%n", x);
                                        }
                                }
                        });
        }
}
