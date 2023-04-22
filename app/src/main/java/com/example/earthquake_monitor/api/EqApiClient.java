package com.example.earthquake_monitor.api;

//Esta clase es un singleton

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class EqApiClient {
    //interfaz para el consumo de servicios rest
    public interface EqService {
        @GET("all_hour.geojson")
                                        //aqui se colocan los parametros que pueda llevar el endpoint
        Call<EarthquakesJSONResponse> getEarthquakes();
    }

    private EqService service;


    //en este se coloca el url base
    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build();
    //el converter permite que llegue en String los datos del request, es importante agregar el moshiConverterFactory
    private static final EqApiClient ourInstance = new EqApiClient();

    public static EqApiClient getInstance() { return ourInstance; }

    private EqApiClient(){

    }

    public EqService getService(){
        if(service == null){
         service = retrofit.create(EqService.class);
        }
        return  service;
    }
}
