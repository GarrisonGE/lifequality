package com.ucr.edu.lifequality.Model;

public class Rank {
    private Location ul;
    private Location lr;
    private float rank;
    private int c_hos;
    private int c_poi;
    private int c_park;
    private float c_airquality;

    public Location getUl() {
        return ul;
    }

    public void setUl(Location ul) {
        this.ul = ul;
    }

    public Location getLr() {
        return lr;
    }

    public void setLr(Location lr) {
        this.lr = lr;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public int getC_hos() {
        return c_hos;
    }

    public void setC_hos(int c_hos) {
        this.c_hos = c_hos;
    }

    public int getC_poi() {
        return c_poi;
    }

    public void setC_poi(int c_poi) {
        this.c_poi = c_poi;
    }

    public float getC_airquality() {
        return c_airquality;
    }

    public void setC_airquality(float c_airquality) {
        this.c_airquality = c_airquality;
    }

    public int getC_park() {
        return c_park;
    }

    public void setC_park(int c_park) {
        this.c_park = c_park;
    }
}
