package com.roomtrac.mobile.connectioncalls.datasets;

public class MenuModel {
    public String menuName;
    public boolean hasChildren, isGroup;
    public int menuid;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren,int menuid) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
        this.menuid=menuid;
    }
}
