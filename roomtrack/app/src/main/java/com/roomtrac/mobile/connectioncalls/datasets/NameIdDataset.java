package com.roomtrac.mobile.connectioncalls.datasets;

/**
 * Created by ramesh.eerla on 4/1/2016.
 */
public class NameIdDataset {
    String name,id,required,display,key,titile;
    int ACTION_ID;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getACTION_ID() { return ACTION_ID; }

    public void setACTION_ID(int ACTION_ID) {this.ACTION_ID = ACTION_ID; }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getTitile() {
        return titile;
    }

    public void setName(String name){this.name=name;}
    public String getName(){return name;}

    public void setId(String id){this.id=id;}
    public String getId(){return id;}

    public void setRequired(String required){this.required=required;}
    public String getRequired(){return required;}

    public void setDisplay(String display){this.display=display;}
    public String getDisplay(){return display;}

}
