package com.valjapan.priser.Data;

import io.realm.RealmObject;

public class MotionTime extends RealmObject {
    public Long startTime;
    public Long stopTime;

    public MotionTime(){

    }

    public MotionTime(Long startTime,Long stopTime){
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }

    public Long getStopTime() {
        return stopTime;
    }


}
