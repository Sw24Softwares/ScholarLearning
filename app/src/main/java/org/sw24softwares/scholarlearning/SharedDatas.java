package org.sw24softwares.scholarlearning;

import java.util.LinkedHashMap;

class SharedData {
        public static LinkedHashMap<String,Loader> mLoadedLessons = new LinkedHashMap<String,Loader>();
        public static String cleanString(String s) {
                s = s.replace("_", " ");
                return s;
        }
}
