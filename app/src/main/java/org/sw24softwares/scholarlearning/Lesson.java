package org.sw24softwares.scholarlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Set;

import android.content.Intent;
import android.content.Context;
import android.app.SearchManager;

import android.widget.ExpandableListView;
import android.widget.SearchView;

public class Lesson extends AppCompatActivity {
        ExpandableListAdapter listAdapter;
        ExpandableListView expListView;
        List<String> listDataHeader;
        HashMap<String, List<String>> listDataChild;
 
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_lesson);

                //                onSearchRequested();
                // Get the SearchView and set the searchable configuration
                SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
                SearchView searchView = (SearchView) findViewById(R.id.lesson_search);
                // Assumes current activity is the searchable activity
                searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
                
                expListView = (ExpandableListView) findViewById(R.id.lesson_list);
                search(new String());
                
                // Get the intent, verify the action and get the query                
                handleIntent(getIntent());
        }
 
        private void prepareListData(String search_word) {
                listDataHeader = new ArrayList<String>();
                listDataChild = new HashMap<String, List<String>>();

                // Adding child data
                for(int i = 0 ; i < Loader.getSingleton().getNumDatas(); i ++) {
                        Boolean contains = false;
                        List<String> details = new ArrayList<String>();
                        Data data = Loader.getSingleton().getData(i);
                        for(int j = 0; j < data.getInformations().size(); j++) {
                                String caseData = data.getFormat().getCategories().get(j) + " : ";
                                for(int k = 0; k < data.getInformations().get(j).size(); k++) {
                                        String info = data.getInformations().get(j).get(k);
                                        if(info.contains(search_word))
                                                contains = true;
                                        caseData += info;
                                        if(k < data.getInformations().get(j).size())
                                                caseData += ", ";
                                }
                        }
                        if(contains || search_word == new String()) {
                                listDataHeader.add(data.getInformations().get(0).get(0));
                                listDataChild.put(listDataHeader.get(listDataHeader.size()-1), details);
                        }

                }
        }
        private void search(String word) {
                prepareListData(word);
                listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
                expListView.setAdapter(listAdapter);
        }

        @Override
        protected void onNewIntent(Intent intent) {
                setIntent(intent);
                handleIntent(intent);
        }
        
        private void handleIntent(Intent intent) {
                if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                        String query = intent.getStringExtra(SearchManager.QUERY);
                        search(query);
                }
        }
}
