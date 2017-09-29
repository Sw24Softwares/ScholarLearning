package org.sw24softwares.scholarlearning;

import java.util.Vector;

public class Format {
        private Vector<String> mCategories;
        private Boolean mAskingCategories[];

        public Format(String line) {
                String categories [] = line.split("|");
                mAskingCategories = new Boolean[categories.length];
                for(int i = 0; i < categories.length; i++) {
                        if(categories[i].contains("^")) {
                                mAskingCategories[i] = false;
                                categories[i].replace("^","");
                        }
                        else
                                mAskingCategories[i] = true;
                        mCategories.addElement(categories[i]);
                }
        }

        public Vector<String> getCategories() {
                return mCategories;
        }
}
