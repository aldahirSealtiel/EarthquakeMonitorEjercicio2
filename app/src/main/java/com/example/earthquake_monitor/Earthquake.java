package com.example.earthquake_monitor;

import java.sql.Time;
import java.util.Objects;

public class Earthquake {

    //se usa para poder usar la funcion de comparaci[on
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Earthquake that = (Earthquake) o;
        return  Double.compare(that.magnitud, magnitud) == 0 &&
                Double.compare(that.longitude, longitude) == 0 &&
                Double.compare(that.latitude, latitude) == 0 &&
                time == that.time &&
                id.equals(that.id) &&
                place.equals(that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, place, magnitud, longitude, latitude, time);
    }

    private String id;
    private String place;
    private double magnitud;
    private double longitude;
    private double latitude;
    private long time; //para usar timestamp

    public  Earthquake(String pId, String pPlace, double pMagnitud, double pLongitude, double pLatitude, long pTime)
    {
        this.id = pId;
        this.place = pPlace;
        this.magnitud = pMagnitud;
        this.longitude = pLongitude;
        this.latitude = pLatitude;
        this.time = pTime;
    }
    public String getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public long getTime() {
        return time;
    }

}
