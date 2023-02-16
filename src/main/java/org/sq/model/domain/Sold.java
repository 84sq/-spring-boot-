package org.sq.model.domain;

import java.util.List;


public class Sold {
    private String sTrainnum;
    private String sdate;
    private String stime;
    private String uId;
    private String seat;
    private List<Users> usersList;

    public String getsTrainnum() {
        return sTrainnum;
    }

    public void setsTrainnum(String sTrainnum) {
        this.sTrainnum = sTrainnum;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }

    @Override
    public String toString() {
        return "Sold{" +
                "sTrainnum='" + sTrainnum + '\'' +
                ", sdate='" + sdate + '\'' +
                ", stime='" + stime + '\'' +
                ", uId='" + uId + '\'' +
                ", seat='" + seat + '\'' +
                ", userList=" + usersList +
                '}';
    }
}
