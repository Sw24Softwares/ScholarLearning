package org.sw24softwares.scholarlearning;

import android.util.Log; 
import android.content.res.AssetManager;

import java.util.LinkedHashMap;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class SharedData {
        public static LinkedHashMap<String,Loader> mLoadedLessons = new LinkedHashMap<String,Loader>();
        public static String cleanString(String s) {
                s = s.replace("_", " ");
                return s;
        }
        public static void loadAllFiles(AssetManager assets) {
                BufferedReader reader = null;
                Loader loader;

                try {
                        String[] files = assets.list("Lessons");
                        for(String name : files) {
                                reader = new BufferedReader(new InputStreamReader(assets.open("Lessons/" + name), "UTF-8"));
                                loader = new Loader(reader);
                                SharedData.mLoadedLessons.put(cleanString(name), loader);
                        }
                } catch (IOException e) {
                        System.exit(0);
                }
        }
}
