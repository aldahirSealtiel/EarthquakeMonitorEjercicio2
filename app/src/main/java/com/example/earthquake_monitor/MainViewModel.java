package com.example.earthquake_monitor;

import android.util.JsonReader;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.earthquake_monitor.api.EarthquakesJSONResponse;
import com.example.earthquake_monitor.api.EqApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
