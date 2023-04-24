package com.example.earthquake_monitor.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.earthquake_monitor.Earthquake;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final MutableLiveData< List<Earthquake> > eqList = new MutableLiveData<>();

    public LiveData< List<Earthquake> > getEqList(){
        return eqList;
    }

    private final MainRepository repository = new MainRepository();

    public void getEarthquakes(){
        repository.getEarthquakes(earthquakes -> {
            eqList.setValue(earthquakes);
        });
    }

}
