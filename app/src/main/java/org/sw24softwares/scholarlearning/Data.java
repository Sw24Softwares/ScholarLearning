package org.sw24softwares.scholarlearning;

import java.util.Vector;

public class Data {
        private Format mFormat;
        private Vector<Vector<String>> mInformations = new Vector<Vector<String>>();

        public Data(String line, Format format) {
                mFormat = format;

                String informations [] = line.split(" ");
                if(informations.length != mFormat.getCategories().size())
                        System.err.println("Trying to format a Data with a non matching String\nline : " + line + "\n");

                for(int i = 0; i < Math.min(informations.length,mFormat.getCategories().size()); i++) {
                        mInformations.addElement(new Vector<String>());
                        String words [] = informations[i].split("/");
                        for(int j = 0; j < words.length; j++)
                                mInformations.lastElement().addElement(words[j]);
                }
        }
}
