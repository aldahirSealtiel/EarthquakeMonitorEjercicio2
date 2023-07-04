package com.example.earthquake_monitor;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Time;
import java.util.Objects;

/**
 * La anotacion @Entity nos permite establecer a que tabla pertecera el objeto
 */
@Entity(tableName = "earthquakes")
public class Earthquake {

    public Earthquake() {
    }

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

    /**
     * con la anotacion @PrimaryKey se coloca al atributo como llave primaria en la tabla de la
     * base de datos, se puede agregar el atributo (autoGenerate = true) con el que se creara
     * automaticamente el id
     * Con la anotacion @NonNull fuerza a que el atributo no puede contener un valor null
     * Si se requiere que alguna columna se llame distinto al nombre del atributo se puede usar la
     * anotacion @ColumnInfo (name = "nuevo nombre")
     */
    @PrimaryKey
    @NonNull
    private String id;

    public void setPlace(String place) {
        this.place = place;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setTime(long time) {
        this.time = time;
    }

    private String place;
    private double magnitud;
    private double longitude;
    private double latitude;
    private long time; //para usar timestamp

    public  Earthquake(@NonNull String pId, String pPlace, double pMagnitud, double pLongitude, double pLatitude, long pTime)
    {
        this.id = pId;
        this.place = pPlace;
        this.magnitud = pMagnitud;
        this.longitude = pLongitude;
        this.latitude = pLatitude;
        this.time = pTime;
    }
    @NonNull
    public String getId() {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
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
