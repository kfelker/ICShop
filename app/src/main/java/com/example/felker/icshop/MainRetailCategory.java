package com.example.felker.icshop;

import java.util.ArrayList;

/**
 * Created by Felker on 10/11/2015.
 */
public class MainRetailCategory {
    private String Desc;
    private int ID;
    private ArrayList<SubRetailCategory> subCatList = new ArrayList<SubRetailCategory>();


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        this.Desc = desc;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return Desc;
    }

    public ArrayList<SubRetailCategory> getSubCatList() {
        return subCatList;
    }
    public void setSubCatList (ArrayList<SubRetailCategory> subList) {
        this.subCatList = subList;
    }

}
