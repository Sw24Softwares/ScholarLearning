package org.sw24softwares.scholarlearning;

import java.util.Vector;

class SharedData {
        public static Vector<Loader> mLoadedLessons = new Vector<Loader>();
        public static String cleanString(String s) {
                s = s.replace("_", " ");
                return s;
        }
}
