package com.example.myapplication;

public class NotiMovieModel {
    String date,loc,name,time,tickets,poster;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public NotiMovieModel(String date, String loc, String name, String time, String tickets, String poster) {
        this.date = date;
        this.loc = loc;
        this.name = name;
        this.time = time;
        this.tickets = tickets;
        this.poster = poster;
    }

    public NotiMovieModel() {
    }
}
