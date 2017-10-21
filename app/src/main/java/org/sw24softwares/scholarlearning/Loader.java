package org.sw24softwares.scholarlearning;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.Vector;

class Loader {
        private Vector<Format> mFormats = new Vector<Format>();
        private Vector<Data> mDatas = new Vector<Data>();
        
        public Loader(BufferedReader bufferedReader) {
                try {
                        String line = null;
                        while ((line = bufferedReader.readLine()) != null && !line.contains("-")) {
                                if(line.trim().isEmpty())
                                        continue;
                                mFormats.addElement(new Format(line));
                        }
                        while ((line = bufferedReader.readLine()) != null) {
                                if(line.trim().isEmpty())
                                        continue;
                                String parts[] = line.split(".");
                                if(parts.length == 0 || parts.length == 1)
                                        mDatas.addElement(new Data(line, mFormats.get(0)));
                                else
                                        mDatas.addElement(new Data(parts[1], mFormats.get(Integer.parseInt(parts[0]))));
                        }
                }
                catch (IOException x) {
                        System.err.format("IOException: %s%n", x);
                }
        }

        public Data getData(int index) {
                return mDatas.get(index);
        }
        public int getNumDatas() {
                return mDatas.size();
        }
        public Format getFormat(int index) {
                return mFormats.get(index);
        }
        public int getNumFormats() {
                return mFormats.size();
        }

        private static Loader mSingleton = null;
        public static void setSingleton(Loader singleton) {
                mSingleton = singleton;
        }
        public static  Loader getSingleton() {
                return mSingleton;
        }
}
