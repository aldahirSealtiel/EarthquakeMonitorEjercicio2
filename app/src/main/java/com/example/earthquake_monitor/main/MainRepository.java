package com.example.earthquake_monitor.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.earthquake_monitor.Earthquake;
import com.example.earthquake_monitor.api.EarthquakesJSONResponse;
import com.example.earthquake_monitor.api.EqApiClient;
import com.example.earthquake_monitor.database.EqDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {

    private final EqDatabase database;

    public interface  DownloadStatusListener{
         void downloadSuccess();
         void downloadError(String message);
    }
    public MainRepository(EqDatabase database)
    {
        this.database = database;

    }
    public LiveData<List<Earthquake>> getEqList(){
        return database.eqDAO().getEarthquakes();
    }
    public void downloadAndSaveEarthquakes(DownloadStatusListener downloadStatusListener){
        /**
         * se genera el consumo del servicio creado anteriormente
         */
        EqApiClient.EqService  service  = EqApiClient.getInstance().getService();
        /**
         * la siguiente linea agrega el request getEarthquakes y lo agrega a una cola
         */
        service.getEarthquakes().enqueue(new Callback<EarthquakesJSONResponse>() {
            @Override
            public void onResponse(@NonNull Call<EarthquakesJSONResponse> call, @NonNull Response<EarthquakesJSONResponse> response) {
                /**
                 * aqui estara la respuesta
                 */
                //Log.d("MainViewModel", response.body());
                //assert response.body() != null;
                List<Earthquake> earthquakes = parseEarthquakes(response.body());

                EqDatabase.databaseWriteExecutor.execute( ()->{
                    database.eqDAO().insertAll(earthquakes);
                });

                //downloadEqsListener.onEqsDownloaded(earthquakes);
                //eqList.setValue(earthquakes);
                downloadStatusListener.downloadSuccess();
            }

            @Override
            public void onFailure(@NonNull Call<EarthquakesJSONResponse> call, Throwable t) {
                downloadStatusListener.downloadError("There was an error in downloading earthquakes, check internet connection.");
            }
        });
        //this.eqList.setValue(eqList);

    }
    private List<Earthquake> parseEarthquakes(EarthquakesJSONResponse responseString )
    {
        ArrayList<Earthquake> eqList = new ArrayList<>();

        for(int i = 0 ; i < responseString.getFeatures().size(); i++)
        {
            String id = responseString.getFeatures().get(i).getId();

            double magnitud = responseString.getFeatures().get(i).getProperties().getMag(); //jsonProperties.getDouble("mag");
            String place = responseString.getFeatures().get(i).getProperties().getPlace();//jsonProperties.getString("place");
            Long time  = responseString.getFeatures().get(i).getProperties().getTime();//jsonProperties.getLong("time");

            //JSONObject jsonGeometry = jsonFeature.getJSONObject("geometry");
            //JSONArray coordinatesJSONArray = jsonGeometry.getJSONArray("coordinates");
            double longitude = responseString.getFeatures().get(i).getGeometry().getLongitude();//coordinatesJSONArray.getDouble(0);
            double latitude = responseString.getFeatures().get(i).getGeometry().getLatitude();//coordinatesJSONArray.getDouble(1);
            Earthquake earthquake = new Earthquake(id, place, magnitud, longitude,latitude,time);
            eqList.add(earthquake);
        }

        return eqList;
    }

}
