package com.example.felker.icshop;

/**
 * Created by Felker on 11/11/2015.
 */
public class SearchResultsObject {

        private String Name;
        private int ID;


        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            this.Name = name;
        }

        // Will be used by the ArrayAdapter in the ListView
        @Override
        public String toString() {
            return Name;
        }
    }

