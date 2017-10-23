package org.sw24softwares.scholarlearning;

import java.util.Vector;

public class Data {
        private Format mFormat;
        private Vector<Vector<String>> mInformations = new Vector<Vector<String>>();

        public Data(String line, Format format) {
                mFormat = format;

                String informations [] = line.split(" ");
                for(int i = 0; i < informations.length; i++) {
                        if(informations[i].trim().isEmpty())
                                continue;
                        mInformations.addElement(new Vector<String>());
                        String words [] = informations[i].split("/");
                        for(int j = 0; j < words.length; j++) {
                                if(!words[j].trim().isEmpty())
                                        mInformations.lastElement().addElement(SharedData.cleanString(words[j]));
                        }
                }
        }

        public Format getFormat() {
                return mFormat;
        }
        public Vector<Vector<String>> getInformations() {
                return mInformations;
        }
}
