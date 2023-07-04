package com.example.earthquake_monitor.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.earthquake_monitor.Earthquake;

import java.util.List;

/**
 * Anotacion para que se identifique que es un Data access Object
 */
@Dao
public interface EqDAO {

    /**
     * Anotacion que nos permite almenar datos en la base de datos, en este caso sera una lista
     * completa de temblores y se le especifico mediante el parametro onConflict que hacer si
     * quiere almacenar un terremoto del cual ya tenga su id registrado, y lo que se le dice es que
     * si encuentra un terremoto que ya este registrado, lo reemplace por el nuevo
     * @param eqList
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Earthquake> eqList);

    /**
     * con esta anotacion se puede hacer una query con leguaje SQL, tambien si el objeto que se devuelve
     * se le ha agregado la anotacion entity el resultado de la query se transforma automaticamente
     * al objeto
     * @return
     */
    @Query("SELECT * FROM earthquakes")
    LiveData<List <Earthquake> >getEarthquakes();

    /**
     * Ejemplo de como crear una query con parametros que vienen en el metodo
     * @return
     */
    @Query("SELECT * FROM earthquakes WHERE magnitud > :myMagnitude ")
    List<Earthquake> getEarthquakesWhitMagnitudeAbove(double myMagnitude);

    /**
     * Ejemplo de anotacion para la eliminacion de datos
     */
    @Delete
    void deleteEarthquake(Earthquake earthquake);

    /**
     * Ejemplo de actualizacion de un elemento de la tabla
     * @param earthquake
     */
    @Update
    void  updateEarthquake(Earthquake earthquake);

}
