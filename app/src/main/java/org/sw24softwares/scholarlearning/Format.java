package org.sw24softwares.scholarlearning;

import java.util.Vector;

public class Format {
        private Vector<String> mCategories = new Vector<String>();
        private Vector<Boolean> mAskingCategories = new Vector<Boolean>();

        public Format(String line) {
                String categories [] = line.split("\\|");
                for(int i = 0; i < categories.length; i++) {
                        if(categories[i].trim().isEmpty())
                                continue;
                        if(categories[i].contains("^")) {
                                mAskingCategories.addElement(false);
                                categories[i] = categories[i].replace("^","");
                        }
                        else
                                mAskingCategories.addElement(true);
                        mCategories.addElement(SharedData.cleanString(categories[i].trim()));
                }
        }

        public Vector<String> getCategories() {
                return mCategories;
        }
        public Vector<Boolean> getAskingCategories() {
                return mAskingCategories;
        }
}
