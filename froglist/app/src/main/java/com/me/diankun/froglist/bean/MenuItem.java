package com.me.diankun.froglist.bean;

/**
 * Created by Administrator on 2015-8-7.
 */
public class MenuItem {

    private boolean isSelected;
    private String text;
    private int icon;
    private int iconSelected;

    public MenuItem(boolean isSelected, String text, int icon, int iconSelected) {
        this.isSelected = isSelected;
        this.text = text;
        this.icon = icon;
        this.iconSelected = iconSelected;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public String getText() {
        return text;
    }

    public int getIcon() {
        return icon;
    }

    public int getIconSelected() {
        return iconSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setIconSelected(int iconSelected) {
        this.iconSelected = iconSelected;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "isSelected=" + isSelected +
                ", text='" + text + '\'' +
                ", icon=" + icon +
                ", iconSelected=" + iconSelected +
                '}';
    }
}
