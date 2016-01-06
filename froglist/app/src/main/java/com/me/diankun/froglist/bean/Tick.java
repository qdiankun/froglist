package com.me.diankun.froglist.bean;

/**
 * Created by diankun on 2016/1/6.
 */
public class Tick {

    private String title;
    private String task;
    private boolean isFinished;
    private String createDate;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public String getTask() {
        return task;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public String getCreateDate() {
        return createDate;
    }
}
