package org.sq.model.domain;

import java.io.Serializable;

public class Train implements Serializable {
    private String trainNumber;
    private String start;
    private String end;
    private String date;
    private String time;
    private int prize;

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNumber='" + trainNumber + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", prize=" + prize +
                '}';
    }
}
